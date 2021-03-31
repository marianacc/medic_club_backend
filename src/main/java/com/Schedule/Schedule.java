package com.Schedule;

import com.ConsultingRoom.ConsultingRoom;
import com.Interval.ScheduleInterval;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String day;
    private String initial_hour;
    private String final_hour;

    @ManyToOne
    @JoinColumn(name="consulting_room_id", nullable=false)
    @JsonIgnore
    private ConsultingRoom consultingRoom;

    @OneToMany(mappedBy="schedule")
    @JsonIgnore
    private Set<ScheduleInterval> scheduleIntervals;

    // Constructores
    public Schedule() {
    }

    public Set<ScheduleInterval> getIntervals() {
        return scheduleIntervals;
    }

    public void setIntervals(Set<ScheduleInterval> scheduleIntervals) {
        this.scheduleIntervals = scheduleIntervals;
    }

    public Schedule(Integer id) {
        this.id = id;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public ConsultingRoom getConsultingRoom() {
        return consultingRoom;
    }

    public void setConsultingRoom(ConsultingRoom consultingRoom) {
        this.consultingRoom = consultingRoom;
    }

    public Set<ScheduleInterval> getScheduleIntervals() {
        return scheduleIntervals;
    }

    public void setScheduleIntervals(Set<ScheduleInterval> scheduleIntervals) {
        this.scheduleIntervals = scheduleIntervals;
    }
}
