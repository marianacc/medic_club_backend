package com.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Integer> {
}
