package com.Doctor;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.ConsultingRoom.ConsultingRoom;
import com.ConsultingRoom.ConsultingRoomDao;
import com.Patient.Patient;
import com.Patient.PatientDao;
import com.Patient.PatientModel;
import com.Rating.RatingDao;
import com.Schedule.Schedule;
import com.Schedule.ScheduleDao;
import com.Specialty.Specialty;
import com.Specialty.SpecialtyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Transactional
@Service
public class DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private SpecialtyDao specialtyDao;

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private ConsultingRoomDao consultingRoomDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private RatingDao ratingDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public DoctorService(DoctorDao doctorDao,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(DoctorModel doctorModel){
        AppUser appUser = new AppUser();
        appUser.setEmail(doctorModel.getEmail());
        appUser.setPassword(bCryptPasswordEncoder.encode(doctorModel.getPassword()));
        appUser.setFirst_name(doctorModel.getFirst_name());
        appUser.setLast_name(doctorModel.getLast_name());
        Doctor doctor = new Doctor();
        doctor.setSpecialty(specialtyDao.findById(doctorModel.getId_specialty()));
        doctor.setScore(doctorModel.getScore());
        doctor.setAppUser(appUser);
        // Se crea un consultorio vacio y se inserta al doctor en este
        ConsultingRoom consultingRoom = new ConsultingRoom();
        consultingRoomDao.save(consultingRoom);
        doctor.setConsultingRoom(consultingRoom);
        doctorDao.save(doctor);
    }

    public ArrayList<Doctor> listMostRated() {
        return (ArrayList<Doctor>) doctorDao.findTop10ByOrderByScoreDesc();
    }

    public ArrayList<Doctor> listDoctorsBySpecialty(int specialty_id) {
        return (ArrayList<Doctor>) doctorDao.findDoctorsBySpecialtyId(specialty_id);
    }

    public void update(int id, DoctorModel doctorModel) {
        AppUser appUser = appUserDao.findByDoctorId(id);
        appUser.setPassword(bCryptPasswordEncoder.encode(doctorModel.getPassword()));
        appUserDao.save(appUser);
        Doctor doctor = doctorDao.findById(id);
        doctor.setPhone_number(doctorModel.getPhone_number());
        doctor.setPricing(doctorModel.getPricing());
        doctor.setBio(doctorModel.getBio());
        doctorDao.save(doctor);
        ConsultingRoom consultingRoom = consultingRoomDao.findByDoctorsId(doctor.getId());
        consultingRoom.setLatitude(doctorModel.getLatitude());
        consultingRoom.setLongitude(doctorModel.getLongitude());
        consultingRoom.setTime_interval(doctorModel.getTime_interval());
        consultingRoomDao.save(consultingRoom);

        // Se vacia la lista de horarios existentes en la base de datos con el id del consultorio a actualizar
        Set<Schedule> schedulesConsultingRoom = consultingRoom.getSchedules();
        schedulesConsultingRoom.clear();
        clearSchedulesFromConsultingRoom(consultingRoom);

        // Se agregan los nuevos horarios recibidos en el doctor model a la bd
        for (Schedule schedule: doctorModel.getSchedule()
             ) {
            Schedule schedule1 = new Schedule();
            schedule1.setConsultingRoom(consultingRoom);
            schedule1.setDay(schedule.getDay());
            schedule1.setInitial_hour(schedule.getInitial_hour());
            schedule1.setFinal_hour(schedule.getFinal_hour());
            scheduleDao.save(schedule1);
            schedulesConsultingRoom.add(schedule1);
        }
        consultingRoom.setSchedules(schedulesConsultingRoom);
        consultingRoomDao.save(consultingRoom);
    }

    private void clearSchedulesFromConsultingRoom(ConsultingRoom consultingRoom) {
        scheduleDao.deleteAllByConsultingRoomId(consultingRoom.getId());
        System.out.println("Horarios borrados con exito");
    }
}
