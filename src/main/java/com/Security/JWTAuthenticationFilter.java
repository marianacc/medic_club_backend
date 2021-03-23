package com.Security;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.Jwt.Jwt;
import com.Jwt.JwtDao;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    AppUserDao appUserDao;

    @Autowired
    JwtDao jwtDao;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.appUserDao = ctx.getBean(AppUserDao.class);
        this.jwtDao = ctx.getBean(JwtDao.class);
        setFilterProcessesUrl("/app-user/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AppUser creds = new ObjectMapper().readValue(req.getInputStream(), AppUser.class);
           	System.out.println("Trying to log in with email: "+creds.getEmail() +" password: "+creds.getPassword());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        AppUser loggedUser = appUserDao.findByEmail(((User) auth.getPrincipal()).getUsername());
        String token = JWT.create()
                .withClaim("appUserId",loggedUser.getId())
                .withClaim("status", loggedUser.getStatus())
                .withClaim("role", loggedUser.getRole())
                .withClaim("name", loggedUser.getFirst_name() + " " +loggedUser.getLast_name())
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        Jwt newToken= new Jwt();
        newToken.setToken(token);
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }
}