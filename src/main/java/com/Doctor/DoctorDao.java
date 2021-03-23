package com.Doctor;

import com.AppUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {
    ArrayList<Doctor> findTop10ByOrderByScoreDesc();
    ArrayList<Doctor> findDoctorsBySpecialtyId(int id);
    Doctor findById(int id);
    Doctor findByAppUser(AppUser appUser);
}
