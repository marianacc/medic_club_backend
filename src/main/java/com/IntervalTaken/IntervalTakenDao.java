package com.IntervalTaken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervalTakenDao extends JpaRepository<IntervalTaken, Integer> {
    IntervalTaken findByScheduleIntervalIdAndDate(int id, String day);
    IntervalTaken findByAppointmentId(int id);
}
