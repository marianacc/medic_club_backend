package com.Interval;

import com.AppUser.AppUser;
import com.Appointment.Appointment;
import com.ConsultingRoom.ConsultingRoom;
import com.Doctor.Doctor;
import com.IntervalTaken.IntervalTaken;
import com.Schedule.Schedule;
import com.Specialty.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ScheduleInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String initial_hour, final_hour;

    @ManyToOne
    @JoinColumn(name="schedule_id", nullable=false)
    private Schedule schedule;

    @OneToMany(mappedBy="scheduleInterval")
    @JsonIgnore
    private Set<IntervalTaken> intervalTakens;

    public ScheduleInterval() {
    }

    public ScheduleInterval(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Set<IntervalTaken> getIntervalTakens() {
        return intervalTakens;
    }

    public void setIntervalTakens(Set<IntervalTaken> intervalTakens) {
        this.intervalTakens = intervalTakens;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
