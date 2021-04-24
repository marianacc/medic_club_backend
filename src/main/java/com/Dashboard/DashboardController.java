package com.Dashboard;

import com.Doctor.DoctorModel;
import com.Doctor.DoctorService;
import com.ObjectResponse.ObjectResponse;
import com.Schedule.ScheduleService;
import com.Specialty.SpecialtyService;
import com.Transaction.Transaction;
import com.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "dashboard")
public class DashboardController {

    private TransactionService transactionService;
    private DashboardService dashboardService;

    @Autowired
    public void DashboardController(TransactionService transactionService, DashboardService dashboardService){
        this.transactionService = transactionService;
        this.dashboardService = dashboardService;
    }

    @RequestMapping(
            value = "/income_category",
            method = RequestMethod.GET)
    public ObjectResponse incomeByCategory(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ArrayList<IncomeByCategory> incomeByCategories = dashboardService.getIncomeByCategories();
            objectResponse.setData(incomeByCategories);
            objectResponse.setSuccess(true);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

}
