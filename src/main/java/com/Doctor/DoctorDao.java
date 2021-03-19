package com.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {
    ArrayList<Doctor> findTop10ByOrderByScoreAsc();
}
