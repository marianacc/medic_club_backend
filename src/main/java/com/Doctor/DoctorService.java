package com.Doctor;

import com.AppUser.AppUser;
import com.AppUser.AppUserDao;
import com.ConsultingRoom.ConsultingRoom;
import com.ConsultingRoom.ConsultingRoomDao;
import com.Interval.ScheduleInterval;
import com.Interval.ScheduleIntervalDao;
import com.Interval.ScheduleIntervalService;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ScheduleIntervalDao scheduleIntervalDao;

    @Autowired
    public void DoctorService(DoctorDao doctorDao, SpecialtyDao specialtyDao, AppUserDao appUserDao,
                              ConsultingRoomDao consultingRoomDao, ScheduleDao scheduleDao,
                              BCryptPasswordEncoder bCryptPasswordEncoder, ScheduleIntervalDao scheduleIntervalDao){
        this.doctorDao = doctorDao;
        this.specialtyDao = specialtyDao;
        this.appUserDao = appUserDao;
        this.consultingRoomDao = consultingRoomDao;
        this.scheduleDao = scheduleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.scheduleIntervalDao = scheduleIntervalDao;
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
            doctor.setTotalPatientsAttended(0);
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

    public Doctor update(int id, DoctorModel doctorModel) {
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

        ScheduleIntervalService scheduleIntervalService = new ScheduleIntervalService();
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
            createIntervals(schedule1);
        }
        consultingRoom.setSchedules(schedulesConsultingRoom);
        consultingRoomDao.save(consultingRoom);

        appUser.setStatus(APP_USER_ACTIVE);
        appUserDao.save(appUser);
        return doctorDao.findByAppUser(appUser);
    }

    private void clearSchedulesFromConsultingRoom(ConsultingRoom consultingRoom) {
        ArrayList<Schedule> schedulesList = scheduleDao.findAllByConsultingRoomId(consultingRoom.getId());
        if(schedulesList != null){
            for (Schedule schedule: schedulesList
            ) {
                scheduleIntervalDao.deleteAllByScheduleId(schedule.getId());
            }
        }
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

    public Doctor findDoctorByDoctorId(int doctor_id) {
        return doctorDao.findById(doctor_id);
    }

    public ConsultingRoom findConsultingRoomByDoctorId(int doctor_id) {
        return consultingRoomDao.findByDoctorsId(doctor_id);
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

    public Doctor updateProfessionalProfile(int doctor_id, DoctorModel doctorModel) {
        Doctor doctor = doctorDao.findById(doctor_id);
        doctor.setPhone_number(doctorModel.getPhone_number());
        doctor.setBio(doctorModel.getBio());
        doctor.setPricing(doctorModel.getPricing());
        doctorDao.save(doctor);
        return doctor;
    }

    public void updateSchedule(int doctor_id, DoctorModel doctorModel){
        Doctor doctor = doctorDao.findById(doctor_id);
        ConsultingRoom consultingRoom = consultingRoomDao.findByDoctorsId(doctor.getId());
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
            createIntervals(schedule1);
        }
        consultingRoom.setSchedules(schedulesConsultingRoom);
        consultingRoomDao.save(consultingRoom);
    }

    public ArrayList<Doctor> filterList(int specialty_id, FilterModel filterModel) {
        ArrayList<Doctor> filteredDoctorsList = new ArrayList<>();
        if (filterModel.orderBy == 1){
            filteredDoctorsList = orderListByScore(specialty_id);
        } else if (filterModel.orderBy == 2){
            filteredDoctorsList = orderListByMaxPrice(specialty_id);
        } else if (filterModel.orderBy == 3){
            filteredDoctorsList = orderListByMinPrice(specialty_id);
        }

        return filteredDoctorsList;
    }

    public ArrayList<Doctor> orderListByScore(int specialty_id) {
        return (ArrayList<Doctor>) doctorDao.findAllByAppUserStatusAndSpecialtyIdOrderByScoreDesc(1, specialty_id);
    }

    private ArrayList<Doctor> orderListByMaxPrice(int specialty_id) {
        return (ArrayList<Doctor>) doctorDao.findAllByAppUserStatusAndSpecialtyIdOrderByPricingDesc(1, specialty_id);
    }

    private ArrayList<Doctor> orderListByMinPrice(int specialty_id) {
        return (ArrayList<Doctor>) doctorDao.findAllByAppUserStatusAndSpecialtyIdOrderByPricingAsc(1, specialty_id);
    }

}