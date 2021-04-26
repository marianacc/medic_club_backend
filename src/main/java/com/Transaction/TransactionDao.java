package com.Transaction;

import com.Dashboard.IncomeByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    Transaction findByAppointmentId(int appointment_id);

    @Query("SELECT sum(a.amount) " +
            "FROM Transaction a, Appointment b, Doctor c, Specialty d " +
            "WHERE a.appointment.id = b.id " +
            "AND b.doctor.id = c.id " +
            "AND c.specialty.id = d.id " +
            "AND d.id = ?1 ")
    Double getTotalAmountByCategory(Integer id);

    @Query("SELECT sum(a.amount) " +
            "FROM Transaction a, Appointment b, Doctor c, Specialty d " +
            "WHERE a.appointment.id = b.id " +
            "AND b.doctor.id = c.id " +
            "AND c.specialty.id = d.id " +
            "AND d.id = ?1 " +
            "AND a.month = ?2 " +
            "GROUP BY a.month")
    Double getTotalAmountByCategoryAndMonth(Integer id, int month);

    @Query("SELECT sum(a.amount) " +
            "FROM Transaction a")
    double getTotalAmount();
}
