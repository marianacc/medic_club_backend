package com.Dashboard;

import java.util.ArrayList;

public class IncomeByCategoryAndMonth {

    String category;
    ArrayList<IncomeByMonth> incomeByMonths;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<IncomeByMonth> getIncomeByMonths() {
        return incomeByMonths;
    }

    public void setIncomeByMonths(ArrayList<IncomeByMonth> incomeByMonths) {
        this.incomeByMonths = incomeByMonths;
    }
}
