package com.Doctor;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.Patient.Patient;
import com.Patient.PatientDao;
import com.Patient.PatientModel;
import com.Rating.RatingDao;
import com.Specialty.Specialty;
import com.Specialty.SpecialtyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private SpecialtyDao specialtyDao;

    @Autowired
    private RatingDao ratingDao;

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
        doctor.setScore(doctorModel.getScore());
        doctor.setAppUser(appUser);
        doctorDao.save(doctor);
    }

    public ArrayList<Doctor> listMostRated() {
        return (ArrayList<Doctor>) doctorDao.findTop10ByOrderByScoreDesc();
    }
}
