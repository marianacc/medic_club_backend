package com.AppUser;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AppUserService {

    @Value("${medic-club.security.salt}")
    private String salt;

    @Autowired
    private AppUserDao appUserDao;

    public void save(AppUser appUser){
        appUser.setPassword(hashPassword(appUser.getPassword()));
        appUserDao.save(appUser);
    }

    public String hashPassword(String password){
        // Se aplica la función hash 256+salt a la contraseña
        String sha256hex = Hashing.sha256()
                .hashString(password+salt, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
}
