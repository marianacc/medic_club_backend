package com.Patient;

import com.Doctor.Doctor;
import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public void PatientController(PatientService patientService){
        this.patientService = patientService;
    }

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
            value = "app-user/{app_user_id}",
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

    @RequestMapping(
            value = "loadPatientsFromJson",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse loadPatientsFromJson(@RequestBody ArrayList<PatientModel> patientModels){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            patientService.loadPatientsFromJson(patientModels);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "patient/{patient_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse getPatientByDoctorId(@PathVariable("patient_id") int patient_id){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            Patient patient = patientService.findPatientByPatientId(patient_id);
            if (patient != null){
                objectResponse.setData(patient);
            } else {
                objectResponse.setSuccess(false);
                objectResponse.setStatusMessage("No se encontro paciente con patient_id = "+patient_id);
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

}
