package com.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public void save(Schedule schedule){
        scheduleDao.save(schedule);
    }
}
