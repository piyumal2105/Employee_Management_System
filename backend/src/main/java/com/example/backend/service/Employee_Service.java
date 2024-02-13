package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    // Create Update Employee Service
    public String updateEmployee(Employee_DTO empployeeDto) {
        if (employeeRepo.existsById(empployeeDto.getEmpID())) {
            employeeRepo.save(modelMapper.map(empployeeDto, Employee_Entity.class));
            return Var_List.RSP_SUCCESS;
        } else {
            return Var_List.RSP_NO_DATA_FOUND;
        }
    }

    // Create Get All Employee Service
    public List<Employee_DTO> getAllEmployee() {
        List<Employee_Entity> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<Employee_DTO>>() {
        }.getType());
    }

    // Create Search Employee Service
    public Employee_DTO searchEmployee(int empID) {
        if (employeeRepo.existsById(empID)) {
            Employee_Entity employee = employeeRepo.findById(empID).orElse(null);
            return modelMapper.map(employee, Employee_DTO.class);
        } else {
            return null;
        }
    }

    // Create Delete Employee Service
    public String deleteEmployee(int empID) {
        if (employeeRepo.existsById(empID)) {
            employeeRepo.deleteById(empID);
            return Var_List.RSP_SUCCESS;
        } else {
            return Var_List.RSP_NO_DATA_FOUND;
        }
    }

}