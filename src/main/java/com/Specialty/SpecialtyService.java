package com.Specialty;

import com.AppUser.AppUser;
import com.Doctor.Doctor;
import com.Doctor.DoctorDao;
import com.Doctor.DoctorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyDao specialtyDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SpecialtyService(DoctorDao doctorDao,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(SpecialtyModel specialtyModel){
        Specialty specialty = new Specialty();
        specialty.setBg_url(specialtyModel.getBg_url());
        specialty.setName(specialtyModel.getName());
        specialtyDao.save(specialty);
    }

    public ArrayList<Specialty> listAllSpecialties(){
        return (ArrayList<Specialty>) specialtyDao.findAll();
    }
}
