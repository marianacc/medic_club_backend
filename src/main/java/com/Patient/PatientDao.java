package com.Patient;

import com.AppUser.AppUser;
import com.Doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PatientDao extends JpaRepository<Patient, Integer> {
    Patient findByAppUser(AppUser appUser);
    Patient findById(int id);
    ArrayList<Patient> findTop5ByAppUserStatusOrderByTotalAppointmentsCreatedDesc(int status);

}
