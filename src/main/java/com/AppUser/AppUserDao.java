package com.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDao extends JpaRepository<AppUser, Integer> {
    AppUser findByEmail(String email);
    AppUser findByDoctorId(int id);
    AppUser findByPatientId(int id);
}
