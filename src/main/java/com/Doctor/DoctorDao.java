package com.Doctor;

import com.AppUser.AppUser;
import com.Appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {
    ArrayList<Doctor> findTop10ByAppUserStatusOrderByScoreDesc(int status);
    ArrayList<Doctor> findDoctorsBySpecialtyIdAndAppUserStatus(int id, int status);
    Doctor findById(int id);
    Doctor findByAppUser(AppUser appUser);
    ArrayList<Doctor> findTop5ByAppUserStatusOrderByScoreDesc(int status);

    ArrayList<Doctor> findAllBySpecialtyIdOrderByScoreDesc(int id);

    @Query("SELECT count(a.id) FROM Appointment a, Doctor b WHERE a.doctor.id = b.id AND b.id = ?1")
    Double getTotalAppointments(int id);
}
