package com.Doctor;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.ConsultingRoom.ConsultingRoom;
import com.ConsultingRoom.ConsultingRoomDao;
import com.Rating.RatingDao;
import com.Schedule.Schedule;
import com.Schedule.ScheduleDao;
import com.Specialty.SpecialtyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Set;

import static com.GlobalVariables.*;

@Transactional
@Service
public class DoctorService {

    private DoctorDao doctorDao;
    private SpecialtyDao specialtyDao;
    private AppUserDao appUserDao;
    private ConsultingRoomDao consultingRoomDao;
    private ScheduleDao scheduleDao;
    private RatingDao ratingDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void DoctorService(DoctorDao doctorDao, SpecialtyDao specialtyDao, AppUserDao appUserDao,
                              ConsultingRoomDao consultingRoomDao, ScheduleDao scheduleDao, RatingDao ratingDao,
                              BCryptPasswordEncoder bCryptPasswordEncoder){
        this.doctorDao = doctorDao;
        this.specialtyDao = specialtyDao;
        this.appUserDao = appUserDao;
        this.consultingRoomDao = consultingRoomDao;
        this.scheduleDao = scheduleDao;
        this.ratingDao = ratingDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean save(DoctorModel doctorModel){
        if(appUserDao.findByEmail(doctorModel.getEmail()) == null){
            AppUser appUser = new AppUser();
            appUser.setEmail(doctorModel.getEmail());
            appUser.setPassword(bCryptPasswordEncoder.encode(doctorModel.getPassword()));
            appUser.setFirst_name(doctorModel.getFirst_name());
            appUser.setLast_name(doctorModel.getLast_name());
            appUser.setStatus(APP_USER_CREATED);
            appUser.setRole(DOCTOR);
            Doctor doctor = new Doctor();
            doctor.setSpecialty(specialtyDao.findById(doctorModel.getId_specialty()));
            doctor.setScore(doctorModel.getScore());
            doctor.setAppUser(appUser);
            // Se crea un consultorio vacio y se inserta al doctor en este
            ConsultingRoom consultingRoom = new ConsultingRoom();
            consultingRoomDao.save(consultingRoom);
            doctor.setConsultingRoom(consultingRoom);
            doctorDao.save(doctor);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Doctor> listMostRated() {
        return (ArrayList<Doctor>) doctorDao.findTop10ByAppUserStatusOrderByScoreDesc(1);
    }

    public ArrayList<Doctor> listDoctorsBySpecialty(int specialty_id) {
        return (ArrayList<Doctor>) doctorDao.findDoctorsBySpecialtyIdAndAppUserStatus(specialty_id, 1);
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

        appUser.setStatus(APP_USER_ACTIVE);
        appUserDao.save(appUser);
    }

    private void clearSchedulesFromConsultingRoom(ConsultingRoom consultingRoom) {
        scheduleDao.deleteAllByConsultingRoomId(consultingRoom.getId());
        System.out.println("Horarios borrados con exito");
    }

    public Doctor findDoctorByAppUserId(int app_user_id) {
        AppUser appUser = new AppUser(app_user_id);
        return doctorDao.findByAppUser(appUser);
    }

    public Boolean saveMoreData(DoctorModel doctorModel){
        if(appUserDao.findByEmail(doctorModel.getEmail()) == null){
            AppUser appUser = new AppUser();
            appUser.setEmail(doctorModel.getEmail());
            appUser.setPassword(bCryptPasswordEncoder.encode(doctorModel.getPassword()));
            appUser.setFirst_name(doctorModel.getFirst_name());
            appUser.setLast_name(doctorModel.getLast_name());
            appUser.setStatus(doctorModel.getStatus());
            appUser.setRole(DOCTOR);
            Doctor doctor = new Doctor();
            doctor.setSpecialty(specialtyDao.findById(doctorModel.getId_specialty()));
            doctor.setScore(doctorModel.getScore());
            doctor.setAppUser(appUser);
            // Se crea un consultorio vacio y se inserta al doctor en este
            ConsultingRoom consultingRoom = new ConsultingRoom();
            if (doctorModel.getStatus() == 1){
                doctor.setBio(doctorModel.getBio());
                doctor.setPricing(doctorModel.getPricing());
                doctor.setPhone_number(doctorModel.getPhone_number());
                consultingRoom.setLatitude(doctorModel.getLatitude());
                consultingRoom.setLongitude(doctorModel.getLongitude());
                consultingRoom.setTime_interval(doctorModel.getTime_interval());
            }
            consultingRoomDao.save(consultingRoom);
            doctor.setConsultingRoom(consultingRoom);
            doctorDao.save(doctor);
            return true;
        } else {
            return false;
        }
    }

    public void loadDoctorsFromJson(ArrayList<DoctorModel> doctorModels){
        for (DoctorModel doctorModel : doctorModels
             ) {
            if (saveMoreData(doctorModel)){
                System.out.println("Registro exitoso: " + doctorModel.getFirst_name() + " " + doctorModel.getLast_name() + ".");
            }

        }
    }

}