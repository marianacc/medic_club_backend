package com.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private ScheduleDao scheduleDao;

    @Autowired
    public void ScheduleService(ScheduleDao scheduleDao){
        this.scheduleDao = scheduleDao;
    }

    public void save(Schedule schedule){
        scheduleDao.save(schedule);
    }
}
