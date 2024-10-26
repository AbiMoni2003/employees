package com.example.employee.controller;

import com.example.employee.dto.EmployeesRequestDto;
import com.example.employee.dto.EmployeesResponseDto;
import com.example.employee.entity.Employees;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("create")
    public ResponseEntity<EmployeesResponseDto> createEmployee(@RequestBody EmployeesRequestDto reqDto) {
        EmployeesResponseDto response = employeeService.createUser(reqDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getall")
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
       return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<EmployeesResponseDto> deleteEmployeeById(@PathVariable Long id) {
        EmployeesResponseDto responseDto=employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable Long id, @RequestBody Employees updatedEmployee) {
        try {
            String updated = employeeService.updateEmployeeById(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
