package com.Specialty;

import com.Doctor.Doctor;
import com.Patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String name, bg_url;

    @OneToOne(mappedBy = "specialty")
    @JsonIgnore
    private Doctor doctor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBg_url() {
        return bg_url;
    }

    public void setBg_url(String bg_url) {
        this.bg_url = bg_url;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
