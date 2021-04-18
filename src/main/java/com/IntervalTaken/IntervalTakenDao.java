package com.IntervalTaken;

import com.Appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface IntervalTakenDao extends JpaRepository<IntervalTaken, Integer> {
    IntervalTaken findByScheduleIntervalIdAndDateAndAppointmentStatus(int id, String day,int status);
    IntervalTaken findByAppointmentId(int id);
    ArrayList<IntervalTaken> findByScheduleIntervalIdAndDate(int id, String date);
}
