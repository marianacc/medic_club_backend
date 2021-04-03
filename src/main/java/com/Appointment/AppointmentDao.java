package com.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
    ArrayList<Appointment> findAllByPatientId(int patient_id);
}
