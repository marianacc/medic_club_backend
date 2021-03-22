package com.AppUser;

import com.ObjectResponse.ObjectResponse;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/app-user")
public class AppUserController {

    @Autowired
    AppUserService appUserService;


}
