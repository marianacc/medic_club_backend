package com.Specialty;

import com.Doctor.DoctorModel;
import com.Doctor.DoctorService;
import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "specialties")
public class SpecialtyController {

    @Autowired
    SpecialtyService specialtyService;

    @RequestMapping(
            value = "create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse CreateSpecialty(@RequestBody SpecialtyModel specialtyModel){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            specialtyService.save(specialtyModel);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ObjectResponse CreateSpecialty(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            objectResponse.setData(specialtyService.listAllSpecialties());
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }
}