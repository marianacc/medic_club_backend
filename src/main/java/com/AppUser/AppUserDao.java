package com.AppUser;

import com.Dashboard.AttendanceByGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface AppUserDao extends JpaRepository<AppUser, Integer> {
    AppUser findByEmail(String email);
    AppUser findByDoctorId(int id);
    AppUser findByPatientId(int id);
}
