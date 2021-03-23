package com.Patient;

import com.AppUser.AppUser;
import com.AppUser.AppUserService;
import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
            Boolean response = patientService.save(patientModel);
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
            value = "appUser/{app_user_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse getPatientByAppUserId(@PathVariable("app_user_id") int app_user_id){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            Patient patient = patientService.findPatientByAppUserId(app_user_id);
            if (patient != null){
                objectResponse.setData(patient);
            } else {
                objectResponse.setSuccess(false);
                objectResponse.setStatusMessage("No se encontro paciente con appUserId = "+app_user_id);
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }
}
