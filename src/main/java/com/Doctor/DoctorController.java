package com.Doctor;

import com.ConsultingRoom.ConsultingRoom;
import com.ObjectResponse.ObjectResponse;
import com.Schedule.Day;
import com.Schedule.Schedule;
import com.Schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "doctors")
public class DoctorController {

    private DoctorService doctorService;
    private ScheduleService scheduleService;

    @Autowired
    public void DoctorController(DoctorService doctorService, ScheduleService scheduleService){
        this.doctorService = doctorService;
        this.scheduleService = scheduleService;
    }

    @RequestMapping(
            value = "sign-up",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse signUp(@RequestBody DoctorModel doctorModel){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            Boolean response = doctorService.save(doctorModel);
            if(!response){
                objectResponse.setSuccess(false);
                objectResponse.setStatusMessage("El correo ya existe");
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "update/{doctor_id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse updateDoctorInformation(@PathVariable("doctor_id") int doctor_id, @RequestBody DoctorModel doctorModel){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            doctorService.update(doctor_id, doctorModel);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "most-rated",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse getMostRated(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            objectResponse.setData(doctorService.listMostRated());
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "specialty/{specialty_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse getDoctorsBySpecialty(@PathVariable("specialty_id") int specialty_id){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            objectResponse.setData(doctorService.listDoctorsBySpecialty(specialty_id));
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "app-user/{app_user_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse getDoctorByAppUserId(@PathVariable("app_user_id") int app_user_id){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            Doctor doctor = doctorService.findDoctorByAppUserId(app_user_id);
            if (doctor != null){
                objectResponse.setData(doctor);
            } else {
                objectResponse.setSuccess(false);
                objectResponse.setStatusMessage("No se encontro doctor con appUserId = "+app_user_id);
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "loadDoctorsFromJson",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse loadDoctorsFromJson(@RequestBody ArrayList<DoctorModel> doctorModels){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            doctorService.loadDoctorsFromJson(doctorModels);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "doctor/{doctor_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse getDoctorByDoctorId(@PathVariable("doctor_id") int doctor_id){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            Doctor doctor = doctorService.findDoctorByDoctorId(doctor_id);
            if (doctor != null){
                objectResponse.setData(doctor);
            } else {
                objectResponse.setSuccess(false);
                objectResponse.setStatusMessage("No se encontro doctor con doctor_id = "+doctor_id);
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "schedule/days/{doctor_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse getScheduleByDoctorId(@PathVariable("doctor_id") int doctor_id){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ConsultingRoom consultingRoom = doctorService.findConsultingRoomByDoctorId(doctor_id);
            Day days = scheduleService.findDaysByConsultingRoom(consultingRoom);
            objectResponse.setData(days);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }
}