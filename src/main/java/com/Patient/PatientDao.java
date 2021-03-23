package com.Patient;

import com.AppUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Integer> {
    Patient findByAppUser(AppUser appUser);
}
