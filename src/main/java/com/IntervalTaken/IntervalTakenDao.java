package com.IntervalTaken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervalTakenDao extends JpaRepository<IntervalTaken, Integer> {
    IntervalTaken findByScheduleIntervalIdAndDateAndAppointmentStatus(int id, String day,int status);
    IntervalTaken findByAppointmentId(int id);
}
