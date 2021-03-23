package com.Patient;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.ObjectResponse.ObjectResponse;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.StandardCharsets;

import static com.GlobalVariables.APP_USER_ACTIVE;
import static com.GlobalVariables.PATIENT;

@Service
public class PatientService {

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private PatientDao patientDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientService(PatientDao patientDao,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean save(PatientModel patientModel){
        AppUser appUser = new AppUser();
        if(appUserDao.findByEmail(patientModel.getEmail()) == null){
            appUser.setEmail(patientModel.getEmail());
            appUser.setPassword(bCryptPasswordEncoder.encode(patientModel.getPassword()));
            appUser.setFirst_name(patientModel.getFirst_name());
            appUser.setLast_name(patientModel.getLast_name());
            appUser.setStatus(APP_USER_ACTIVE);
            appUser.setRole(PATIENT);
            appUserDao.save(appUser);
            Patient patient = new Patient();
            patient.setAppUser(appUser);
            patientDao.save(patient);
            return true;
        } else {
            return false;
        }
    }

    public Patient findPatientByAppUserId(int app_user_id) {
        AppUser appUser = new AppUser(app_user_id);
        return patientDao.findByAppUser(appUser);
    }
}
