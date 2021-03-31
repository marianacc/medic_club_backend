package com.Interval;

import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "intervals")
public class IntervalController {

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
}
