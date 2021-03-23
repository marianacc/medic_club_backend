package com.ConsultingRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultingRoomService {

    private ConsultingRoomDao consultingRoomDao;

    @Autowired
    public void ConsultingRoomService (ConsultingRoomDao consultingRoomDao){
        this.consultingRoomDao = consultingRoomDao;
    }

    public void save(ConsultingRoom consultingRoom){
        consultingRoomDao.save(consultingRoom);
    }
}