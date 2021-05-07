package com.Admin;

import com.ObjectResponse.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

    AdminService adminService;

    @Autowired
    public void AdminController (AdminService adminService){
        this.adminService = adminService;
    }

    @RequestMapping(
            value = "sign-up",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse signUp(@RequestBody AdminModel adminModel){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            Boolean response = adminService.save(adminModel);
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




}
