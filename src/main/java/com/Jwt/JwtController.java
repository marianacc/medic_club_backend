package com.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/AppUser")
public class JwtController {
    @Autowired
    JwtDao jwtDao;
}
