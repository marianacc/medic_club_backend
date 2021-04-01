package com.Interval;

public class ScheduleIntervalModel {
    private Integer id;
    private String initial_hour, final_hour;
    private int status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
