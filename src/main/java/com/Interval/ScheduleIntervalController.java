package com.Interval;

import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping(value = "intervals")
public class ScheduleIntervalController {

    private ScheduleIntervalService scheduleIntervalService;

    @Autowired
    public void IntervalController(ScheduleIntervalService scheduleIntervalService){
        this.scheduleIntervalService = scheduleIntervalService;
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
    public ObjectResponse loadSchedulesFromJson(@RequestParam(name = "date") String date, @RequestParam(name = "doctor_id") int doctor_id){
        ObjectResponse objectResponse = new ObjectResponse();
        String dayOfWeek = scheduleIntervalService.getDayOfWeek(date);
        System.out.println(dayOfWeek);
        try{
            ArrayList<ScheduleInterval> scheduleIntervals = scheduleIntervalService.findAllByScheduleDayAndDoctorId(dayOfWeek, doctor_id);
            ArrayList<ScheduleIntervalModel> scheduleIntervalModels = new ArrayList<>();
            for (ScheduleInterval scheduleInterval : scheduleIntervals
                 ) {
                ScheduleIntervalModel scheduleIntervalModel = new ScheduleIntervalModel();
                scheduleIntervalModel.setId(scheduleInterval.getId());
                scheduleIntervalModel.setFinal_hour(scheduleInterval.getFinal_hour());
                scheduleIntervalModel.setInitial_hour(scheduleInterval.getInitial_hour());
                //TODO: Logica para ver si este horario ya esta tomado, si este cumple el caso cambiar el status
                scheduleIntervalModel.setStatus(1);
                scheduleIntervalModels.add(scheduleIntervalModel);
            }
            if (scheduleIntervals != null){
                objectResponse.setData(scheduleIntervalModels);
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }
}
