package com.Patient;

import com.AppUser.AppUser;
import com.Appointment.Appointment;
import com.Transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String weight;
    private String height;
    private int totalAppointmentsCreated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUser appUser;

    @OneToMany(mappedBy="patient")
    @JsonIgnore
    private Set<Appointment> appointments;

    @OneToMany(mappedBy="patient")
    @JsonIgnore
    private Set<Transaction> transactions;

    // Constructores
    public Patient() {
    }

    public int getTotalAppointmentsCreated() {
        return totalAppointmentsCreated;
    }

    public void setTotalAppointmentsCreated(int totalAppointmentsCreated) {
        this.totalAppointmentsCreated = totalAppointmentsCreated;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Patient(Integer id) {
        this.id = id;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
