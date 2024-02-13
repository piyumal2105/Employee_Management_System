package com.example.backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.Employee_DTO;
import com.example.backend.entity.Employee_Entity;
import com.example.backend.repo.Employee_Repo;
import com.example.backend.utill.Var_List;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Employee_Service {
    @Autowired
    private Employee_Repo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Create Add Employee Service
    public String addEmployee(Employee_DTO employeeDto) {
        if (employeeRepo.existsById(employeeDto.getEmpID())) {
            return Var_List.RSP_DUPLICATED;
        } else {
            employeeRepo.save(modelMapper.map(employeeDto, Employee_Entity.class));
            return Var_List.RSP_SUCCESS;
        }
    }
}