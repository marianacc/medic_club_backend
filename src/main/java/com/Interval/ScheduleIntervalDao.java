package com.Interval;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ScheduleIntervalDao extends JpaRepository<ScheduleInterval, Integer> {
    ArrayList<ScheduleInterval> findAllByScheduleId(int schedule_id);
}
