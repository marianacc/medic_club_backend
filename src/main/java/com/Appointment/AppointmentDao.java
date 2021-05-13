package com.Appointment;

import com.Doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
    ArrayList<Appointment> findAllByPatientId(int patient_id);
    ArrayList<Appointment> findAllByDoctorId(int doctor_id);
    Appointment findById(int id);
    @Query("SELECT avg(a.score) FROM Appointment a WHERE a.doctor.id = ?1 AND a.status = ?2")
    double getRatingDoctor(int doctor_id, int status);

    @Query("SELECT au.genre, count( DISTINCT  au.id) " +
            "FROM AppUser au, Patient p, Appointment ap " +
            "WHERE au.id = p.appUser.id " +
            "AND p.id = ap.patient.id " +
            "GROUP BY au.genre")
    ArrayList<String> countPerGenre();

    @Query("SELECT count( DISTINCT  au.id) " +
            "FROM AppUser au, Patient p, Appointment ap " +
            "WHERE au.id = p.appUser.id " +
            "AND p.id = ap.patient.id ")
    int countAll();
}
