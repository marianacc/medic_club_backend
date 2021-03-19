package com.Doctor;

import com.ObjectResponse.ObjectResponse;
import com.Patient.PatientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping(
            value = "sign-up",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signUp(@RequestBody DoctorModel doctorModel){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            doctorService.save(doctorModel);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
    }

}