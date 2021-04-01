package com.Appointment;

import com.Doctor.DoctorModel;
import com.ObjectResponse.ObjectResponse;
import com.Transaction.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
}
