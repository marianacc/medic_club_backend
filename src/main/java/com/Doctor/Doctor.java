package com.Doctor;

import com.AppUser.AppUser;
import com.Appointment.Appointment;
import com.ConsultingRoom.ConsultingRoom;
import com.Specialty.Specialty;
import com.Transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bio;
    private Double pricing;
    private Double score;
    private String phone_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name="consulting_room_id", nullable=true)
    @JsonBackReference
    private ConsultingRoom consultingRoom;

    @ManyToOne
    @JoinColumn(name="specialty_id", nullable=false)
    private Specialty specialty;

    @OneToMany(mappedBy="doctor")
    @JsonBackReference
    private Set<Appointment> appointments;

    @OneToMany(mappedBy="doctor")
    @JsonBackReference
    private Set<Transaction> transactions;

    // Constructores
    public Doctor() {
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Doctor(Integer id) {
        this.id = id;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Double getPricing() {
        return pricing;
    }

    public void setPricing(Double pricing) {
        this.pricing = pricing;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public ConsultingRoom getConsultingRoom() {
        return consultingRoom;
    }

    public void setConsultingRoom(ConsultingRoom consultingRoom) {
        this.consultingRoom = consultingRoom;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
