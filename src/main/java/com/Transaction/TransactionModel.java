package com.Transaction;

public class TransactionModel {

    private Double amount;
    private String date;
    private int id_doctor;
    private int id_patient;
    private int id_interval;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_interval() {
        return id_interval;
    }

    public void setId_interval(int id_interval) {
        this.id_interval = id_interval;
    }
}
