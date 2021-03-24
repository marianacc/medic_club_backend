package com.Schedule;

import com.ConsultingRoom.ConsultingRoom;
import com.ConsultingRoom.ConsultingRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleService {

    private ScheduleDao scheduleDao;
    private ConsultingRoomDao consultingRoomDao;

    @Autowired
    public void ScheduleService(ScheduleDao scheduleDao, ConsultingRoomDao consultingRoomDao){
        this.scheduleDao = scheduleDao;
        this.consultingRoomDao = consultingRoomDao;
    }

    public void save(Schedule schedule){
        scheduleDao.save(schedule);
    }

    public void saveSchedule(ScheduleModel scheduleModel){
        Schedule schedule = new Schedule();
        schedule.setFinal_hour(scheduleModel.getFinal_hour());
        schedule.setInitial_hour(scheduleModel.getInitial_hour());
        schedule.setDay(scheduleModel.getDay());
        ConsultingRoom consultingRoom = new ConsultingRoom(scheduleModel.getConsulting_room_id());
        schedule.setConsultingRoom(consultingRoom);
        scheduleDao.save(schedule);
    }

    public void loadSchedulesFromJson(ArrayList<ScheduleModel> scheduleModels) {
        for (ScheduleModel scheduleModel : scheduleModels
             ) {
            saveSchedule(scheduleModel);
            System.out.println("Horario creado para el consultorio con id: " + scheduleModel.getConsulting_room_id());
        }
    }
}
