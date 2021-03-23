package com.Jwt;


import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtDao extends JpaRepository<Jwt, Long> {
}
