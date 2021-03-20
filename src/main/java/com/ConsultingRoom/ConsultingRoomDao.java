package com.ConsultingRoom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultingRoomDao extends JpaRepository<ConsultingRoom, Integer> {
    ConsultingRoom findByDoctorsId(int id);
}
