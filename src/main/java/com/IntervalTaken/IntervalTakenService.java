package com.IntervalTaken;

import com.Doctor.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntervalTakenService {

    private IntervalTakenDao intervalTakenDao;

    @Autowired
    public void IntervalTakenService (IntervalTakenDao intervalTakenDao){this.intervalTakenDao = intervalTakenDao;}

    public boolean findByScheduleIntervalAndDate(Integer id, String date,int status) {
        IntervalTaken intervalTaken = intervalTakenDao.findByScheduleIntervalIdAndDateAndAppointmentStatus(id, date,status);
        System.out.println(intervalTaken);
        if (intervalTaken != null){
            return true;
        }
        return false;
    }
}
//comentario de prueba
