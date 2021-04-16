package com.Interval;

import com.ConsultingRoom.ConsultingRoom;
import com.ConsultingRoom.ConsultingRoomDao;
import com.Schedule.Schedule;
import com.Schedule.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ScheduleIntervalService {

    private ScheduleIntervalDao scheduleIntervalDao;
    private ScheduleDao scheduleDao;
    private ConsultingRoomDao consultingRoomDao;

    @Autowired
    public void ScheduleIntervalService (ScheduleIntervalDao scheduleIntervalDao, ScheduleDao scheduleDao, ConsultingRoomDao consultingRoomDao) {this.scheduleIntervalDao = scheduleIntervalDao; this.scheduleDao = scheduleDao; this.consultingRoomDao = consultingRoomDao;}

    public ArrayList<ScheduleInterval> findByScheduleId(int schedule_id) {
        return scheduleIntervalDao.findAllByScheduleIdOrderByIdAsc(schedule_id);
    }

    public ArrayList<ScheduleInterval> findAllByScheduleDayAndDoctorId(String day, int doctor_id) {
        ConsultingRoom consultingRoom = consultingRoomDao.findByDoctorsId(doctor_id);
        Schedule schedule = scheduleDao.findByDayAndConsultingRoomId(day, consultingRoom.getId());
        if (schedule != null){
            return scheduleIntervalDao.findAllByScheduleIdOrderByIdAsc(schedule.getId());
        }
        return null;
    }

    public String getDayOfWeek(String date) {
        if (LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy")).getDayOfWeek().name().equals("MONDAY"))
            return "Lunes";
        else if (LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy")).getDayOfWeek().name().equals("TUESDAY"))
            return "Martes";
        else if (LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy")).getDayOfWeek().name().equals("WEDNESDAY"))
            return "Miercoles";
        else if (LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy")).getDayOfWeek().name().equals("THURSDAY"))
            return "Jueves";
        else if (LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy")).getDayOfWeek().name().equals("FRIDAY"))
            return "Viernes";
        else if (LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy")).getDayOfWeek().name().equals("SATURDAY"))
            return "Sabado";
        else if (LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy")).getDayOfWeek().name().equals("SUNDAY"))
            return "Domingo";
        return null;
    }

}
