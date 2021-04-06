package com.Schedule;

import com.ConsultingRoom.ConsultingRoom;
import com.ConsultingRoom.ConsultingRoomDao;
import com.Interval.ScheduleInterval;
import com.Interval.ScheduleIntervalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleService {

    private ScheduleDao scheduleDao;
    private ConsultingRoomDao consultingRoomDao;
    private ScheduleIntervalDao scheduleIntervalDao;

    @Autowired
    public void ScheduleService(ScheduleDao scheduleDao, ConsultingRoomDao consultingRoomDao, ScheduleIntervalDao scheduleIntervalDao){
        this.scheduleDao = scheduleDao;
        this.consultingRoomDao = consultingRoomDao;
        this.scheduleIntervalDao = scheduleIntervalDao;
    }

    public void save(Schedule schedule){
        scheduleDao.save(schedule);
    }

    public void saveSchedule(ScheduleModel scheduleModel){
        Schedule schedule = new Schedule();
        schedule.setFinal_hour(scheduleModel.getFinal_hour());
        schedule.setInitial_hour(scheduleModel.getInitial_hour());
        schedule.setDay(scheduleModel.getDay());
        int consulting_room_id = scheduleModel.getConsulting_room_id();
        ConsultingRoom consultingRoom = consultingRoomDao.findById(consulting_room_id);
        schedule.setConsultingRoom(consultingRoom);
        scheduleDao.save(schedule);
        createIntervals(schedule);
    }

    public void loadSchedulesFromJson(ArrayList<ScheduleModel> scheduleModels) {
        for (ScheduleModel scheduleModel : scheduleModels
             ) {
            saveSchedule(scheduleModel);
            System.out.println("Horario creado para el consultorio con id: " + scheduleModel.getConsulting_room_id());
        }
    }

    public Day findDaysByConsultingRoom(ConsultingRoom consultingRoom){
        ArrayList<Schedule> schedules = scheduleDao.findAllByConsultingRoomId(consultingRoom.getId());
        Day day = new Day();
        for (Schedule schedule: schedules
             ) {
            if(schedule.getDay().equals("Lunes"))
                day.setLunes(true);

            if(schedule.getDay().equals("Martes"))
                day.setMartes(true);

            if(schedule.getDay().equals("Miercoles"))
                day.setMiercoles(true);

            if(schedule.getDay().equals("Jueves"))
                day.setJueves(true);

            if(schedule.getDay().equals("Viernes"))
                day.setViernes(true);

            if(schedule.getDay().equals("Sabado"))
                day.setSabado(true);

            if(schedule.getDay().equals("Domingo"))
                day.setDomingo(true);
        }
        return day;
    }

    public void createIntervals(Schedule schedule) {
        String[] partsInitialHour = schedule.getInitial_hour().split(":");
        String[] partsFinalHour = schedule.getFinal_hour().split(":");

        int initialHour = Integer.parseInt(partsInitialHour[0]);
        int initialMinute = Integer.parseInt(partsInitialHour[1]);
        int finalHour = Integer.parseInt(partsFinalHour[0]);
        int finalMinute = Integer.parseInt(partsFinalHour[1]);

        double dateAmount = (((finalHour * 60) + finalMinute) - ((initialHour * 60) + initialMinute)) / schedule.getConsultingRoom().getTime_interval();

        int minutes = (initialHour * 60) + initialMinute;
        for(int i=0; i<dateAmount; i++){
            ScheduleInterval scheduleInterval = new ScheduleInterval();
            scheduleInterval.setInitial_hour(minutes/60+":"+minutes%60);
            minutes+=schedule.getConsultingRoom().getTime_interval();
            scheduleInterval.setFinal_hour(minutes/60+":"+minutes%60);
            scheduleInterval.setSchedule(schedule);
            scheduleIntervalDao.save(scheduleInterval);
        }
    }
}
