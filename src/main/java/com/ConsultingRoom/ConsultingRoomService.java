package com.ConsultingRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultingRoomService {

    @Autowired
    private ConsultingRoomDao consultingRoomDao;

    public void save(ConsultingRoom consultingRoom){
        consultingRoomDao.save(consultingRoom);
    }
}
