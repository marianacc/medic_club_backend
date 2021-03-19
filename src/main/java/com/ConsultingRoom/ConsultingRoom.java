package com.ConsultingRoom;

import com.Doctor.Doctor;
import com.Schedule.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ConsultingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String latitude, longitude, time_interval, phone_number;

    @OneToOne(mappedBy = "consultingRoom")
    @JsonIgnore
    private Doctor doctor;

    @OneToOne(mappedBy = "consultingRoom")
    @JsonIgnore
    private Schedule schedule;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(String time_interval) {
        this.time_interval = time_interval;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
