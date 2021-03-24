package com.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
    void deleteAllByConsultingRoomId(int id);
}
