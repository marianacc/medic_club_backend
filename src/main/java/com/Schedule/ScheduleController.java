package com.Schedule;

import com.Doctor.DoctorService;
import com.ObjectResponse.ObjectResponse;
import com.Specialty.SpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "schedules")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public void ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @RequestMapping(
            value = "loadSchedulesFromJson",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse loadSchedulesFromJson(@RequestBody ArrayList<ScheduleModel> scheduleModels){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            scheduleService.loadSchedulesFromJson(scheduleModels);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

}
