package com.example.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Employee_Entity;

public interface Employee_Repo extends JpaRepository<Employee_Entity, Integer> {
    
}
