package com.Interval;

import com.Schedule.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleIntervalService {

    private ScheduleIntervalDao scheduleIntervalDao;

    @Autowired
    public void ScheduleIntervalService (ScheduleIntervalDao scheduleIntervalDao) {this.scheduleIntervalDao = scheduleIntervalDao;}

    public void createIntervals(Schedule schedule) {
        String[] partsInitialHour = schedule.getInitial_hour().split(":");
        String[] partsFinalHour = schedule.getFinal_hour().split(":");

        int initialHour = Integer.parseInt(partsInitialHour[0]);
        int initialMinute = Integer.parseInt(partsInitialHour[1]);
        int finalHour = Integer.parseInt(partsFinalHour[0]);
        int finalMinute = Integer.parseInt(partsFinalHour[1]);

        double dateAmount = (((finalHour * 60) + finalMinute) - ((initialHour * 60) + initialMinute)) / schedule.getConsultingRoom().getTime_interval();

        int minutes = (initialHour * 60) + initialMinute;
        for(int i=0; i<dateAmount; i++){
            ScheduleInterval scheduleInterval = new ScheduleInterval();
            scheduleInterval.setInitial_hour(minutes/60+":"+minutes%60);
            minutes+=schedule.getConsultingRoom().getTime_interval();
            scheduleInterval.setFinal_hour(minutes/60+":"+minutes%60);
            scheduleInterval.setSchedule(schedule);
            scheduleIntervalDao.save(scheduleInterval);
        }
    }

    public ArrayList<ScheduleInterval> findByScheduleId(int schedule_id) {
        //return intervalDao.findAllByScheduleId(schedule_id);
        return null;
    }
}
