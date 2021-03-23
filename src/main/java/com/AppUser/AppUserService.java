package com.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private AppUserDao appUserDao;

    @Autowired
    public void AppUserService (AppUserDao appUserDao){
        this.appUserDao = appUserDao;
    }

    public void save(AppUser appUser){
        appUserDao.save(appUser);
    }
}