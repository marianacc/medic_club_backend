package com.Specialty;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyDao extends JpaRepository<Specialty, Integer> {
    Specialty findById(int id);

}
