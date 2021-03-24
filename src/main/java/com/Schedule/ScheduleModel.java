package com.Schedule;

public class ScheduleModel {

    private String day;
    private String initial_hour;
    private String final_hour;
    private Integer consulting_room_id;

    public Integer getConsulting_room_id() {
        return consulting_room_id;
    }

    public void setConsulting_room_id(Integer consulting_room_id) {
        this.consulting_room_id = consulting_room_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) { this.day = day; }

    public String getInitial_hour() {
        return initial_hour;
    }

    public void setInitial_hour(String initial_hour) {
        this.initial_hour = initial_hour;
    }

    public String getFinal_hour() {
        return final_hour;
    }

    public void setFinal_hour(String final_hour) {
        this.final_hour = final_hour;
    }
}
