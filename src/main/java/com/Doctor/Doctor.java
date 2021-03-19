package com.Doctor;

import com.AppUser.AppUser;
import com.ConsultingRoom.ConsultingRoom;
import com.Specialty.Specialty;

import javax.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialty_id", referencedColumnName = "id")
    private Specialty specialty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consulting_room_id", referencedColumnName = "id")
    private ConsultingRoom consultingRoom;

    private String bio;
    private Double pricing;
    private Double score;

    public Integer getId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public ConsultingRoom getConsultingRoom() {
        return consultingRoom;
    }

    public void setConsultingRoom(ConsultingRoom consultingRoom) {
        this.consultingRoom = consultingRoom;
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
}
