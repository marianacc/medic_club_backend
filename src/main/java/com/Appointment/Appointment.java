package com.Appointment;

import com.ConsultingRoom.ConsultingRoom;
import com.Doctor.Doctor;
import com.IntervalTaken.IntervalTaken;
import com.Patient.Patient;
import com.Specialty.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int score, status;

    @ManyToOne
    @JoinColumn(name="doctor_id", nullable=true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;

    @OneToMany(mappedBy="appointment")
    @JsonIgnore
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
}
