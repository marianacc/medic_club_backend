package com.IntervalTaken;

import com.Appointment.Appointment;
import com.Interval.ScheduleInterval;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class IntervalTaken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;

    @ManyToOne
    @JoinColumn(name="schedule_interval_id", nullable=false)
    @JsonBackReference
    private ScheduleInterval scheduleInterval;

    @ManyToOne
    @JoinColumn(name="appointment_id", nullable=false)
    @JsonBackReference
    private Appointment appointment;

    public IntervalTaken(Integer id) {
        this.id = id;
    }

    public IntervalTaken() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ScheduleInterval getInterval() {
        return scheduleInterval;
    }

    public void setInterval(ScheduleInterval scheduleInterval) {
        this.scheduleInterval = scheduleInterval;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
