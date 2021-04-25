package com.Transaction;

import com.Appointment.Appointment;
import com.Doctor.Doctor;
import com.Patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private int day;
    private int month;
    private int year;

    @ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    @JsonIgnore
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="appointment_id", nullable=false)
    @JsonIgnore
    private Appointment appointment;

    public Transaction() {
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
