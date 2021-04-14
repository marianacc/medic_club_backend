package com.Appointment;

import com.Doctor.Doctor;
import com.Doctor.DoctorDao;
import com.Interval.ScheduleInterval;
import com.Interval.ScheduleIntervalDao;
import com.IntervalTaken.IntervalTaken;
import com.IntervalTaken.IntervalTakenDao;
import com.Patient.Patient;
import com.Patient.PatientDao;
import com.Transaction.Transaction;
import com.Transaction.TransactionDao;
import com.Transaction.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.GlobalVariables.*;

@Service
public class AppointmentService {

    private DoctorDao doctorDao;
    private PatientDao patientDao;
    private AppointmentDao appointmentDao;
    private TransactionDao transactionDao;
    private ScheduleIntervalDao scheduleIntervalDao;
    private IntervalTakenDao intervalTakenDao;

    @Autowired
    public void AppointmentService (DoctorDao doctorDao, PatientDao patientDao, AppointmentDao appointmentDao,
                                    TransactionDao transactionDao, ScheduleIntervalDao scheduleIntervalDao,
                                    IntervalTakenDao intervalTakenDao){
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
        this.appointmentDao = appointmentDao;
        this.transactionDao = transactionDao;
        this.scheduleIntervalDao = scheduleIntervalDao;
        this.intervalTakenDao = intervalTakenDao;
    }

    public Boolean createAppointment(TransactionModel transactionModel) {
        Appointment appointment = new Appointment();
        Doctor doctor = doctorDao.findById(transactionModel.getId_doctor());
        Patient patient = patientDao.findById(transactionModel.getId_patient());
        // Crea una cita con el doctor y el paciente
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus(APPOINTMENT_ON_HOLD);
        appointment.setScore(0);
        appointmentDao.save(appointment);
        // Guarda la transacción
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionModel.getAmount());
        transaction.setDate(transactionModel.getDate());
        transaction.setPatient(patient);
        transaction.setDoctor(doctor);
        transactionDao.save(transaction);
        // Encuentra el intervalo que devuelve el modelo
        ScheduleInterval scheduleInterval = scheduleIntervalDao.findById(transactionModel.getId_interval());
        // Guarda el intervalo tomado
        IntervalTaken intervalTaken = new IntervalTaken();
        intervalTaken.setAppointment(appointment);
        intervalTaken.setInterval(scheduleInterval);
        intervalTaken.setDate(transactionModel.getDate());
        intervalTakenDao.save(intervalTaken);
        return true;
    }

    public ArrayList<AppointmentModel> findAllByPatientIdAndStatus(int patient_id, int status) {
        ArrayList<Appointment> appointments = appointmentDao.findAllByPatientId(patient_id);
        return getAppointmentsModels(appointments, status);
    }

    public ArrayList<AppointmentModel> findAllByDoctorIdAndStatus(int doctor_id, int status) {
        ArrayList<Appointment> appointments = appointmentDao.findAllByDoctorId(doctor_id);
        return getAppointmentsModels(appointments, status);
    }

    public ArrayList<AppointmentModel> getAppointmentsModels(ArrayList<Appointment> appointments, int status){
        ArrayList<AppointmentModel> appointmentModelList = new ArrayList<>();
        for (Appointment appointment : appointments
        ) {
            AppointmentModel appointmentModel = new AppointmentModel();
            if (status == appointment.getStatus()){
                appointmentModel.setPatient_id(appointment.getPatient().getId());
                appointmentModel.setDoctor_id(appointment.getDoctor().getId());
                appointmentModel.setIntervalTakens(appointment.getIntervalTakens());
                appointmentModel.setId(appointment.getId());
                appointmentModel.setStatus(appointment.getStatus());
                appointmentModel.setScore(appointment.getScore());
                appointmentModelList.add(appointmentModel);
            }
        }
        return appointmentModelList;
    }

    public String acceptAppointment(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        appointment.setStatus(APPOINTMENT_ACCEPTED);
        appointmentDao.save(appointment);
        return "Cita aceptada";
    }

    public String declineAppointment(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        appointment.setStatus(APPOINTMENT_DECLINED);
        appointmentDao.save(appointment);
        return "Cita rechazada";
    }

    public String endAppointment(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        appointment.setStatus(APPOINTMENT_FINISHED);
        appointmentDao.save(appointment);
        return "Cita finalizada";
    }
}
