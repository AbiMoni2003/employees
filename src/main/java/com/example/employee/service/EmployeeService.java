package com.example.employee.service;

import com.example.employee.dto.EmployeesRequestDto;
import com.example.employee.dto.EmployeesResponseDto;
import com.example.employee.entity.Employees;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    EmployeesResponseDto createUser(EmployeesRequestDto reqDto);
    List<Employees> getAllEmployees();
    ResponseEntity<Object> getEmployeeById(Long id);
    EmployeesResponseDto deleteEmployeeById(Long id);
    String updateEmployeeById(Long id, Employees updatedEmploees);
}
