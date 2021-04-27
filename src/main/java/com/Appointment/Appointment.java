package com.Appointment;

import com.Doctor.Doctor;
import com.IntervalTaken.IntervalTaken;
import com.Patient.Patient;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int status;
    private boolean qualified;
    private double score;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "appointment")
    private Set<IntervalTaken> intervalTakens;


    public Appointment() {
    }

    public Appointment(Integer id) {
        this.id = id;
    }

    public Set<IntervalTaken> getIntervalTakens() {
        return intervalTakens;
    }

    public void setIntervalTakens(Set<IntervalTaken> intervalTakens) {
        this.intervalTakens = intervalTakens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }
}
