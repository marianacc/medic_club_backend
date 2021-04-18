package com.Doctor;

import com.AppUser.AppUser;
import com.Appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {
    ArrayList<Doctor> findTop10ByAppUserStatusOrderByScoreDesc(int status);
    ArrayList<Doctor> findDoctorsBySpecialtyIdAndAppUserStatus(int id, int status);
    Doctor findById(int id);
    Doctor findByAppUser(AppUser appUser);
}
