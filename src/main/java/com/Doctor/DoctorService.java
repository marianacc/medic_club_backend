package com.Doctor;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.Patient.Patient;
import com.Patient.PatientDao;
import com.Patient.PatientModel;
import com.Specialty.Specialty;
import com.Specialty.SpecialtyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private SpecialtyDao specialtyDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public DoctorService(DoctorDao doctorDao,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(DoctorModel doctorModel){
        AppUser appUser = new AppUser();
        appUser.setEmail(doctorModel.getEmail());
        appUser.setPassword(bCryptPasswordEncoder.encode(doctorModel.getPassword()));
        appUser.setFirst_name(doctorModel.getFirst_name());
        appUser.setLast_name(doctorModel.getLast_name());
        Doctor doctor = new Doctor();
        doctor.setSpecialty(specialtyDao.findById(doctorModel.getId_specialty()));
        doctor.setAppUser(appUser);
        doctorDao.save(doctor);
    }
}
