package com.AppUser;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "app-user")
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public void AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @ApiOperation(value = "login", notes = "Login with the given credentials.")
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    void login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }
}
