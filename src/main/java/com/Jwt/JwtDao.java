package com.Jwt;


import com.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtDao extends JpaRepository<Jwt, Long> {
}
