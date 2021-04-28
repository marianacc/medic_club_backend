package com.AppUser;

import com.Patient.Patient;
import com.Patient.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.GlobalVariables.APP_USER_ACTIVE;
import static com.GlobalVariables.PATIENT;

@Service
public class AppUserService {

    private AppUserDao appUserDao;
    private PatientDao patientDao;

    @Autowired
    public void AppUserService (AppUserDao appUserDao, PatientDao patientDao){
        this.appUserDao = appUserDao;
        this.patientDao = patientDao;
    }

    public void save(AppUser appUser){
        appUserDao.save(appUser);
    }

    public void saveGoogleAppUser(AppUserGoogleModel appUserGoogleModel) {
        AppUser appUser = new AppUser();
        appUser.setEmail(appUserGoogleModel.getEmail());
        appUser.setFirst_name(appUserGoogleModel.getFirst_name());
        appUser.setLast_name(appUserGoogleModel.getLast_name());
        appUser.setStatus(APP_USER_ACTIVE);
        appUser.setRole(PATIENT);
        appUserDao.save(appUser);
        Patient patient = new Patient();
        patient.setAppUser(appUser);
        patientDao.save(patient);
    }
}