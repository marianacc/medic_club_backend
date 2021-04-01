package com.Interval;

import com.IntervalTaken.IntervalTakenService;
import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import com.GlobalVariables.*;
import java.util.Date;

import static com.GlobalVariables.SCHEDULE_FREE;
import static com.GlobalVariables.SCHEDULE_OCCUPIED;

@RestController
@RequestMapping(value = "intervals")
public class ScheduleIntervalController {

    private ScheduleIntervalService scheduleIntervalService;
    private IntervalTakenService intervalTakenService;

    @Autowired
    public void IntervalController(ScheduleIntervalService scheduleIntervalService, IntervalTakenService intervalTakenService){
        this.scheduleIntervalService = scheduleIntervalService;
        this.intervalTakenService = intervalTakenService;
    }

    @RequestMapping(
            value = "{schedule_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse findIntervalsByScheduleId(@PathVariable("schedule_id") int schedule_id){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ArrayList<ScheduleInterval> intervals = scheduleIntervalService.findByScheduleId(schedule_id);
            objectResponse.setData(intervals);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse findIntervalByDateAndDoctorId(@RequestParam(name = "date") String date, @RequestParam(name = "doctor_id") int doctor_id){
        ObjectResponse objectResponse = new ObjectResponse();
        String dayOfWeek = scheduleIntervalService.getDayOfWeek(date);
        try{
            ArrayList<ScheduleInterval> scheduleIntervals = scheduleIntervalService.findAllByScheduleDayAndDoctorId(dayOfWeek, doctor_id);
            ArrayList<ScheduleIntervalModel> scheduleIntervalModels = new ArrayList<>();
            for (ScheduleInterval scheduleInterval : scheduleIntervals
                 ) {
                ScheduleIntervalModel scheduleIntervalModel = new ScheduleIntervalModel();
                scheduleIntervalModel.setId(scheduleInterval.getId());
                scheduleIntervalModel.setFinal_hour(scheduleInterval.getFinal_hour());
                scheduleIntervalModel.setInitial_hour(scheduleInterval.getInitial_hour());
                if(intervalTakenService.findByScheduleIntervalAndDate(scheduleInterval.getId(), date)){
                    scheduleIntervalModel.setStatus(SCHEDULE_OCCUPIED);
                }else{
                    scheduleIntervalModel.setStatus(SCHEDULE_FREE);
                }
                scheduleIntervalModels.add(scheduleIntervalModel);
            }
            if (scheduleIntervals != null){
                objectResponse.setData(scheduleIntervalModels);
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage("No existen horarios en este dia.");
        }
        return objectResponse;
    }
}
