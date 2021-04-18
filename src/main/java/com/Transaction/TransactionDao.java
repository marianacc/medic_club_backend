package com.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    Transaction findByAppointmentId(int appointment_id);
}
