package com.AppUser;

import com.ObjectResponse.ObjectResponse;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "app-user")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @RequestMapping(
            value = "sign-up",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signUp(@RequestBody AppUser appUser){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            appUserService.save(appUser);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
    }
}
