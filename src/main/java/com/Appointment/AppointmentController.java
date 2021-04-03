package com.Appointment;

import com.Doctor.DoctorModel;
import com.ObjectResponse.ObjectResponse;
import com.Transaction.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public void AppointmentController (AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @RequestMapping(
            value = "create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse createAppointment(@RequestBody TransactionModel transactionModel){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            if(appointmentService.createAppointment(transactionModel)){
                objectResponse.setSuccess(true);
                objectResponse.setStatusMessage("Cita creada");
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "patient",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse findAllByPatientIdAndStatus(@RequestParam(name = "patient_id") int patient_id, @RequestParam(name = "status") int status){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            //ArrayList<AppointmentModel> appointments = appointmentService.findAllByPatientIdAndStatus(patient_id, status);
            ArrayList<Appointment> appointments = appointmentService.findAllByPatientIdAndStatusNew(patient_id,status);
            if (appointments != null){
                objectResponse.setSuccess(true);
                objectResponse.setData(appointments);
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }


}
