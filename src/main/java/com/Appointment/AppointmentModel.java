package com.Appointment;

import com.Doctor.Doctor;
import com.Interval.ScheduleInterval;
import com.IntervalTaken.IntervalTaken;
import com.Patient.Patient;

import javax.print.Doc;
import java.util.Set;

public class AppointmentModel {

    private Integer id;
    private int score, status;

    private int doctor_id;
    private int patient_id;

    private Set<IntervalTaken> intervalTakens;

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

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Set<IntervalTaken> getIntervalTakens() {
        return intervalTakens;
    }

    public void setIntervalTakens(Set<IntervalTaken> intervalTakens) {
        this.intervalTakens = intervalTakens;
    }
}
