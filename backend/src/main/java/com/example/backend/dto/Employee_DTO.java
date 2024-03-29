package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee_DTO {
    private int empID;
    private String empName;
    private String empAddress;
    private String empContactNumber;
}
