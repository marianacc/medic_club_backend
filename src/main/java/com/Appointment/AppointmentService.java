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
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.GlobalVariables.*;

@Transactional
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

    public Boolean createAppointment(TransactionModel transactionModel) throws ParseException {
        Appointment appointment = new Appointment();
        Doctor doctor = doctorDao.findById(transactionModel.getId_doctor());
        Patient patient = patientDao.findById(transactionModel.getId_patient());
        // Crea una cita con el doctor y el paciente
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus(APPOINTMENT_ON_HOLD);
        appointment.setQualified(false);
        appointment.setScore(0);
        appointmentDao.save(appointment);
        // Guarda la transacción
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionModel.getAmount());
        String [] date = transactionModel.getDate().split("/");
        transaction.setDay(Integer.parseInt(date[0]));
        transaction.setMonth(Integer.parseInt(date[1]));
        transaction.setYear(Integer.parseInt(date[2]));
        transaction.setPatient(patient);
        transaction.setDoctor(doctor);
        transaction.setAppointment(appointment);
        transactionDao.save(transaction);
        // Encuentra el intervalo que devuelve el modelo
        ScheduleInterval scheduleInterval = scheduleIntervalDao.findById(transactionModel.getId_interval());
        // Guarda el intervalo tomado
        IntervalTaken intervalTaken = new IntervalTaken();
        intervalTaken.setAppointment(appointment);
        intervalTaken.setInterval(scheduleInterval);
        intervalTaken.setDate(transactionModel.getDate());
        intervalTakenDao.save(intervalTaken);
        patient.setTotalAppointmentsCreated(patient.getTotalAppointmentsCreated() + 1);
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
                appointmentModel.setQualified(appointment.isQualified());
                appointmentModelList.add(appointmentModel);
            }
        }
        return appointmentModelList;
    }

    public String acceptAppointment(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        appointment.setStatus(APPOINTMENT_ACCEPTED);

        IntervalTaken intervalTaken = intervalTakenDao.findByAppointmentId(appointment_id);

        ArrayList<IntervalTaken> intervalTakens = intervalTakenDao.findByScheduleIntervalIdAndDate(intervalTaken.getInterval().getId(), intervalTaken.getDate());
        for (IntervalTaken it : intervalTakens
             ) {
            if (it.getAppointment().getStatus() != 1){
                it.getAppointment().setStatus(APPOINTMENT_DECLINED);
                deleteAppointmentInfoBD(it.getAppointment());
            }
        }
        appointmentDao.save(appointment);
        return "Cita aceptada";
    }

    public String declineAppointment(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        appointment.setStatus(APPOINTMENT_DECLINED);
        appointmentDao.save(appointment);
        deleteAppointmentInfoBD(appointment);
        return "Cita rechazada";
    }

    public String endAppointment(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        appointment.setStatus(APPOINTMENT_FINISHED);
        appointmentDao.save(appointment);
        int doctor_id = appointment.getDoctor().getId();
        Doctor doctor = doctorDao.findById(doctor_id);
        doctor.setTotalPatientsAttended(doctor.getTotalPatientsAttended()+1);
        doctorDao.save(doctor);
        //IntervalTaken intervalTaken = intervalTakenDao.findByAppointmentId(appointment.getId());
        //intervalTakenDao.delete(intervalTaken);
        return "Cita finalizada";
    }

    public String cancelAppointment(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        deleteAppointmentInfoBD(appointment);
        appointmentDao.delete(appointment);
        return "Cita cancelada con exito";
    }

    public void deleteAppointmentInfoBD(Appointment appointment){
        IntervalTaken intervalTaken = intervalTakenDao.findByAppointmentId(appointment.getId());
        intervalTakenDao.delete(intervalTaken);

        Transaction transaction = transactionDao.findByAppointmentId(appointment.getId());
        transactionDao.delete(transaction);
    }

    public String rateAppointment(int appointment_id, double score) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        appointment.setScore(score);
        appointment.setQualified(true);
        appointmentDao.save(appointment);
        getDoctorRating(appointment_id);
        System.out.println(appointment_id);
        return "Cita calificada con exito.";
    }

    private void getDoctorRating(int appointment_id) {
        Appointment appointment = appointmentDao.findById(appointment_id);
        int doctor_id = appointment.getDoctor().getId();
        Doctor doctor = doctorDao.findById(doctor_id);
        doctor.setScore((double) (Math.round(appointmentDao.getRatingDoctor(doctor_id, 3)*100)/100));
        doctorDao.save(doctor);
        System.out.println("Doctor calificado correctamente\nNuevo score: " + appointmentDao.getRatingDoctor(doctor_id,3));
    }
}
