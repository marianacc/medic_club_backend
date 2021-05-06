package com.Doctor;

import com.Schedule.Day;
import org.hibernate.criterion.Order;

public class FilterModel {

    int orderBy;
    double[] price;
    String[] days;

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public double[] getPrice() {
        return price;
    }

    public void setPrice(double[] price) {
        this.price = price;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }
}

