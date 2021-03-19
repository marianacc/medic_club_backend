package com.Schedule;

import com.ConsultingRoom.ConsultingRoom;

import javax.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consulting_room_id", referencedColumnName = "id")
    private ConsultingRoom consultingRoom;

    private String day, initial_hour, final_hour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConsultingRoom getConsultingRoom() {
        return consultingRoom;
    }

    public void setConsultingRoom(ConsultingRoom consultingRoom) {
        this.consultingRoom = consultingRoom;
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
}
