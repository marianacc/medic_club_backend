package com.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
    Long deleteAllByConsultingRoomId(int id);
}
