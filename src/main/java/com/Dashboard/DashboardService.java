package com.Dashboard;

import com.ConsultingRoom.ConsultingRoomDao;
import com.Specialty.Specialty;
import com.Specialty.SpecialtyDao;
import com.Transaction.Transaction;
import com.Transaction.TransactionDao;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DashboardService {

    TransactionDao transactionDao;
    SpecialtyDao specialtyDao;

    @Autowired
    public void DashboardService (TransactionDao transactionDao, SpecialtyDao specialtyDao){
        this.transactionDao = transactionDao;
        this.specialtyDao = specialtyDao;
    }

    public ArrayList<IncomeByCategory> getIncomeByCategories() {
        ArrayList<IncomeByCategory> incomeByCategories = new ArrayList<>();
        ArrayList<Specialty> specialties = (ArrayList<Specialty>) specialtyDao.findAll();

        for (Specialty specialty : specialties
             ) {
            IncomeByCategory incomeByCategory = new IncomeByCategory();
            incomeByCategory.setCategory(specialty.getName());
            if (transactionDao.getTotalAmountByCategory(specialty.getId()) != null){
                double total = transactionDao.getTotalAmountByCategory(specialty.getId());
                incomeByCategory.setAmount(total);
            }
            incomeByCategories.add(incomeByCategory);
        }
        return incomeByCategories;
    }

}
