package com.AppUser;

import com.Patient.PatientService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AppUserService {

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private PatientService patientService;

    public void save(AppUser appUser){
        appUserDao.save(appUser);
    }


}
