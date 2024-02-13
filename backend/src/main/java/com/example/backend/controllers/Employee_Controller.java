package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.Employee_DTO;
import com.example.backend.dto.Response_DTO;
import com.example.backend.service.Employee_Service;
import com.example.backend.utill.Var_List;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/employee")

public class Employee_Controller {
    @Autowired
    private Employee_Service employeeService;
    @Autowired
    private Response_DTO responseDto;

    // Create Add Employee Controller
    @PostMapping(value = "/addEmployee")
    public ResponseEntity addEmployee(@RequestBody Employee_DTO employeeDto) {
        try {
            String res = employeeService.addEmployee(employeeDto);
            if (res.equals("00")) {
                responseDto.setCode(Var_List.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDto.setCode(Var_List.RSP_DUPLICATED);
                responseDto.setMessage("Employee Aready Added");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            } else {
                responseDto.setCode(Var_List.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDto.setCode(Var_List.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Update Employee Controller
    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody Employee_DTO employeeDto) {
        try {
            String res = employeeService.updateEmployee(employeeDto);
            if (res.equals("00")) {
                responseDto.setCode(Var_List.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDto.setCode(Var_List.RSP_DUPLICATED);
                responseDto.setMessage("Not A registered Employee");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            } else {
                responseDto.setCode(Var_List.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDto.setCode(Var_List.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Get All Employee Controller
    @GetMapping(value = "/getallEmployee")
    public ResponseEntity getAllEmployee() {
        try {
            List<Employee_DTO> employeeDtoList = employeeService.getAllEmployee();
            responseDto.setCode(Var_List.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(employeeDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDto.setCode(Var_List.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Search Employee Controller
    @GetMapping(value = "/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID) {
        try {
            Employee_DTO employeeDto = employeeService.searchEmployee(empID);
            if (employeeDto != null) {
                responseDto.setCode(Var_List.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else {
                responseDto.setCode(Var_List.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No Employee Available For This EmpID");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDto.setCode(Var_List.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Delete Employee Controller
    @DeleteMapping(value = "/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID) {
        try {
            String res = employeeService.deleteEmployee(empID);
            if (res.equals("00")) {
                responseDto.setCode(Var_List.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else {
                responseDto.setCode(Var_List.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No Employee Available For This EmpID");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDto.setCode(Var_List.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
