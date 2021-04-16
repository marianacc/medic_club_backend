package com.Schedule;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
    void deleteAllByConsultingRoomId(int id);
    ArrayList<Schedule> findAllByConsultingRoomId(int id);
    Schedule findByDayAndConsultingRoomId(String day, int id);
}
