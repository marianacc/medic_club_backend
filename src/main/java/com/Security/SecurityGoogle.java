package com.Security;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.Jwt.Jwt;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityGoogle {

    private AppUserDao appUserDao;

    @Autowired
    public void SecurityGoogle (AppUserDao appUserDao){
        this.appUserDao = appUserDao;
    }

    public String authenticate (String email){
        AppUser appUser = appUserDao.findByEmail(email);
        if(appUser != null){
            return generateJwt(appUser);
        }else{
            return null;
        }
    }

    private String generateJwt (AppUser appUser){
        String token = JWT.create()
                .withClaim("appUserId",appUser.getId())
                .withClaim("status", appUser.getStatus())
                .withClaim("role", appUser.getRole())
                .withClaim("name", appUser.getFirst_name() + " " +appUser.getLast_name())
                .withSubject(appUser.getEmail())
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        return token;
    }
}
