package com.Patient;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.GlobalVariables.APP_USER_ACTIVE;
import static com.GlobalVariables.PATIENT;

@Service
public class PatientService {

    private AppUserDao appUserDao;
    private PatientDao patientDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void PatientService(AppUserDao appUserDao, PatientDao patientDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.appUserDao = appUserDao;
        this.patientDao = patientDao;
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

    public void loadPatientsFromJson(ArrayList<PatientModel> patientModels){
        for (PatientModel patientModel : patientModels
             ) {
            if (save(patientModel)){
                System.out.println("Registro exitoso: " + patientModel.getFirst_name() + " " + patientModel.getLast_name() + ".");
            }
        }
    }

    public Patient findPatientByPatientId(int patient_id) {
        return patientDao.findById(patient_id);
    }

    public void update(int patient_id, PatientModel patientModel) {
        AppUser appUser = appUserDao.findByPatientId(patient_id);
        appUser.setFirst_name(patientModel.getFirst_name());
        appUser.setLast_name(patientModel.getLast_name());
        appUser.setBirth_date(patientModel.getBirth_date());
        appUser.setGenre(patientModel.getGenre());
        appUserDao.save(appUser);
        Patient patient = patientDao.findById(patient_id);
        patient.setWeight(patientModel.getWeight());
        patient.setHeight(patientModel.getHeight());
        patientDao.save(patient);
    }
}