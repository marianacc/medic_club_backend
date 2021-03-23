package com.AppUser;

import com.Jwt.Jwt;
import com.ObjectResponse.ObjectResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiKey;

@RestController
@RequestMapping(value = "app-user")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @ApiOperation(value = "login", notes = "Login with the given credentials.")
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    void login(
            @RequestParam("email") String username,
            @RequestParam("password") String password
    ) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

}
