package com.Transaction;

import com.Dashboard.IncomeByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    Transaction findByAppointmentId(int appointment_id);

    @Query("SELECT sum(a.amount) FROM Transaction a, Appointment b, Doctor c, Specialty d WHERE a.appointment.id = b.id AND b.doctor.id = c.id AND c.specialty.id = d.id AND d.id = ?1 GROUP BY a.date")
    Double getTotalAmountByCategory(Integer id);
}
