package com.Doctor;

import com.Schedule.Schedule;
import com.Schedule.ScheduleModel;

import java.sql.Date;
import java.util.ArrayList;

public class DoctorModel {

    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String genre;
    private Date birth_date;
    private String photo_url;

    private String bio;
    private Double pricing;
    private int id_specialty;
    private int id_consulting_room;
    private String phone_number;

    private String latitude, longitude;
    private int time_interval;

    private ArrayList<Schedule> schedule;

    public ArrayList<Schedule> getSchedules() {
        return schedule;
    }

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    public int getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(int time_interval) {
        this.time_interval = time_interval;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getId_consulting_room() {
        return id_consulting_room;
    }

    public void setId_consulting_room(int id_consulting_room) {
        this.id_consulting_room = id_consulting_room;
    }

    private Double Score;

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }

    public int getId_specialty() {
        return id_specialty;
    }

    public void setId_specialty(int id_specialty) {
        this.id_specialty = id_specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
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
