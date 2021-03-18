package com.Patient;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class PatientService {

    /*@Value("${medic-club.security.salt}")
    private String salt;*/

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private PatientDao patientDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientService(PatientDao patientDao,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(PatientModel patientModel){

        AppUser appUser = new AppUser();
        appUser.setEmail(patientModel.getEmail());
        appUser.setPassword(bCryptPasswordEncoder.encode(patientModel.getPassword()));
        appUser.setFirst_name(patientModel.getFirst_name());
        appUser.setLast_name(patientModel.getLast_name());
        appUserDao.save(appUser);
        Patient patient = new Patient();
        patient.setAppUser(appUser);
        patientDao.save(patient);
    }

    /*public String hashPassword(String password){
        // Se aplica la función hash 256+salt a la contraseña
        String sha256hex = Hashing.sha256()
                .hashString(password+salt, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }*/
}
