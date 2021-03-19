package com.Patient;

import com.AppUser.AppUser;
import com.AppUser.AppUserService;
import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping(
            value = "sign-up",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse signUp(@RequestBody PatientModel patientModel){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            patientService.save(patientModel);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

}
