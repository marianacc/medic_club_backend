package com.Dashboard;

import com.AppUser.AppUserDao;
import com.Appointment.AppointmentDao;
import com.ConsultingRoom.ConsultingRoomDao;
import com.Doctor.Doctor;
import com.Doctor.DoctorDao;
import com.Patient.Patient;
import com.Patient.PatientDao;
import com.Specialty.Specialty;
import com.Specialty.SpecialtyDao;
import com.Transaction.Transaction;
import com.Transaction.TransactionDao;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    TransactionDao transactionDao;
    SpecialtyDao specialtyDao;
    DoctorDao doctorDao;
    AppointmentDao appointmentDao;
    PatientDao patientDao;

    @Autowired
    public void DashboardService (TransactionDao transactionDao, SpecialtyDao specialtyDao, DoctorDao doctorDao, AppointmentDao appointmentDao, PatientDao patientDao){
        this.transactionDao = transactionDao;
        this.specialtyDao = specialtyDao;
        this.doctorDao = doctorDao;
        this.appointmentDao = appointmentDao;
        this.patientDao = patientDao;
    }

    public ArrayList<IncomeByCategory> getIncomeByCategories() {
        ArrayList<IncomeByCategory> incomeByCategories = new ArrayList<>();
        ArrayList<Specialty> specialties = (ArrayList<Specialty>) specialtyDao.findAll();

        for (Specialty specialty : specialties
             ) {
            IncomeByCategory incomeByCategory = new IncomeByCategory();
            incomeByCategory.setCategory(specialty.getName());
            if (transactionDao.getTotalAmountByCategory(specialty.getId()) != null){
                double total = transactionDao.getTotalAmountByCategory(specialty.getId());
                incomeByCategory.setAmount(total);
            }
            incomeByCategories.add(incomeByCategory);
        }
        return incomeByCategories;
    }

    public ArrayList<IncomeByCategoryAndMonth> getIncomeByCategoriesAndMonths() {
        ArrayList<IncomeByCategoryAndMonth> incomeByCategoryAndMonths = new ArrayList<>();
        ArrayList<Specialty> specialties = (ArrayList<Specialty>) specialtyDao.findAll();

        for (Specialty specialty : specialties
        ) {
            IncomeByCategoryAndMonth incomeByCategoryAndMonth = new IncomeByCategoryAndMonth();
            incomeByCategoryAndMonth.setCategory(specialty.getName());
            ArrayList<IncomeByMonth> incomeByMonths = new ArrayList<>();
            for(int i = 1; i<=12; i++){
                IncomeByMonth incomeByMonth = new IncomeByMonth();
                if (i == 1){
                    incomeByMonth.setMonth("Enero");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 2){
                    incomeByMonth.setMonth("Febrero");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 3){
                    incomeByMonth.setMonth("Marzo");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 4){
                    incomeByMonth.setMonth("Abril");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 5){
                    incomeByMonth.setMonth("Mayo");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 6){
                    incomeByMonth.setMonth("Junio");
                    //incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 7){
                    incomeByMonth.setMonth("Julio");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 8){
                    incomeByMonth.setMonth("Agosto");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 9){
                    incomeByMonth.setMonth("Septiembre");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 10){
                    incomeByMonth.setMonth("Octubre");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 11){
                    incomeByMonth.setMonth("Noviembre");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if (i == 12){
                    incomeByMonth.setMonth("Diciembre");
                    incomeByMonth.setAmount(transactionDao.getTotalAmountByCategoryAndMonth(specialty.getId(), i));
                }

                if(incomeByMonth.getAmount() != null)
                    incomeByMonths.add(incomeByMonth);

            }
            incomeByCategoryAndMonth.setIncomeByMonths(incomeByMonths);
            incomeByCategoryAndMonths.add(incomeByCategoryAndMonth);
        }
        return incomeByCategoryAndMonths;
    }

    public ArrayList<MostImportantCategories> getMostImportantCategories() {
        ArrayList<MostImportantCategories> mostImportantCategories = new ArrayList<>();
        ArrayList<Specialty> specialties = (ArrayList<Specialty>) specialtyDao.findAll();

        for (Specialty specialty: specialties
             ) {
            MostImportantCategories mostImportantCategory = new MostImportantCategories();
            mostImportantCategory.setCategory(specialty.getName());
            if (transactionDao.getTotalAmountByCategory(specialty.getId()) != null){
                double totalCategory = transactionDao.getTotalAmountByCategory(specialty.getId());
                mostImportantCategory.setAmount(totalCategory);
                mostImportantCategory.setPercentage((totalCategory*100)/transactionDao.getTotalAmount());
                mostImportantCategories.add(mostImportantCategory);
            }
        }

        return mostImportantCategories;
    }

    public ArrayList<MostImportantDoctors> getMostImportantDoctors() {
        ArrayList<Doctor> doctors = doctorDao.findTop5ByAppUserStatusOrderByScoreDesc(1);
        ArrayList<MostImportantDoctors> mostImportantDoctors = new ArrayList<>();

        for (Doctor doctor: doctors
             ) {
            MostImportantDoctors mostImportantDoctor = new MostImportantDoctors();
            mostImportantDoctor.setName(doctor.getAppUser().getFirst_name() + " " + doctor.getAppUser().getLast_name());
            mostImportantDoctor.setCategory(doctor.getSpecialty().getName());
            if(doctor.getScore() != null){
                mostImportantDoctor.setScore(doctor.getScore());
            }
            mostImportantDoctor.setTotalPatients(doctor.getTotalPatientsAttended());
            mostImportantDoctor.setPercentageAppointments((doctor.getTotalPatientsAttended() * 100) / doctorDao.getTotalAppointments(doctor.getId()));
            System.out.println(doctorDao.getTotalAppointments(doctor.getId()));
            mostImportantDoctors.add(mostImportantDoctor);
        }
        return mostImportantDoctors;
    }

    public ArrayList<AttendanceByGender> getAttendanceByGender() {
        ArrayList<String> countPerGenre = appointmentDao.countPerGenre();
        ArrayList<AttendanceByGender> attendanceByGenders = new ArrayList<>();
        int total = appointmentDao.countAll();
        for (int i = 0; i<countPerGenre.size(); i++){
            String [] parts = countPerGenre.get(i).split(",");
            String part1 = parts[0];
            int part2 = Integer.parseInt(parts[1]);
            AttendanceByGender attendanceByGender = new AttendanceByGender();
            attendanceByGender.setGender(part1);
            attendanceByGender.setPercentage((part2*100)/total);
            attendanceByGenders.add(attendanceByGender);
        }

        return attendanceByGenders;
    }

    public ArrayList<TopMostAttendedPatients> getMostAttendedPatients() {
        ArrayList<Patient> patients = patientDao.findTop5ByAppUserStatusOrderByTotalAppointmentsCreatedDesc(1);
        ArrayList<TopMostAttendedPatients> mostAttendedPatients = new ArrayList<>();
        for (Patient patient : patients
             ) {
            TopMostAttendedPatients topMostAttendedPatients = new TopMostAttendedPatients();
            topMostAttendedPatients.setName(patient.getAppUser().getFirst_name() + " " + patient.getAppUser().getLast_name());
            topMostAttendedPatients.setTotalAppointmentsCreated(patient.getTotalAppointmentsCreated());
            mostAttendedPatients.add(topMostAttendedPatients);
        }
        return mostAttendedPatients;
    }
}
