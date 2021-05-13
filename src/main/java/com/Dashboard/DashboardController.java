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

    @RequestMapping(
            value = "/income_category_month",
            method = RequestMethod.GET)
    public ObjectResponse incomeByCategoryAndMonths(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ArrayList<IncomeByCategoryAndMonth> incomeByCategoryAndMonths = dashboardService.getIncomeByCategoriesAndMonths();
            objectResponse.setData(incomeByCategoryAndMonths);
            objectResponse.setSuccess(true);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "/most_important_categories",
            method = RequestMethod.GET)
    public ObjectResponse mostImportantCategories(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ArrayList<MostImportantCategories> mostImportantCategories = dashboardService.getMostImportantCategories();
            objectResponse.setData(mostImportantCategories);
            objectResponse.setSuccess(true);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "/most_important_doctors",
            method = RequestMethod.GET)
    public ObjectResponse mostImportantDoctors(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ArrayList<MostImportantDoctors> mostImportantDoctors = dashboardService.getMostImportantDoctors();
            objectResponse.setData(mostImportantDoctors);
            objectResponse.setSuccess(true);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "/attendance_by_gender",
            method = RequestMethod.GET)
    public ObjectResponse attendanceByGender(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ArrayList<AttendanceByGender> attendance = dashboardService.getAttendanceByGender();
            objectResponse.setData(attendance);
            objectResponse.setSuccess(true);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }

    @RequestMapping(
            value = "/top_most_attended_patients",
            method = RequestMethod.GET)
    public ObjectResponse mostAttendedPatients(){
        ObjectResponse objectResponse = new ObjectResponse();
        try{
            ArrayList<TopMostAttendedPatients> mostAttendedPatients = dashboardService.getMostAttendedPatients();
            objectResponse.setData(mostAttendedPatients);
            objectResponse.setSuccess(true);
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }
        return objectResponse;
    }
}
