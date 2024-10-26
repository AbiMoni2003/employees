package com.example.employee.service;

import com.example.employee.dto.EmployeesRequestDto;
import com.example.employee.dto.EmployeesResponseDto;
import com.example.employee.entity.Employees;
import com.example.employee.facade.EmployeeFacade;
import com.example.employee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    EmployeeFacade employeeFacade;

    @Override
    public EmployeesResponseDto createUser(EmployeesRequestDto reqDto) {
    return employeeFacade.createUser(reqDto);
    }
    @Override
    public List<Employees> getAllEmployees() {
    return employeesRepository.findAll();
    }
    @Override
    public ResponseEntity<Object> getEmployeeById(Long id) {
        Optional<Employees> employee = employeesRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Employee ID " + id + " not found");
            return ResponseEntity.ok(response);
        }
    }

    @Override
//    public ResponseEntity<Object> deleteEmployeeById(Long id) {
//        if (employeesRepository.existsById(id)) {
//            employeesRepository.deleteById(id);
//            return ResponseEntity.ok("Employee ID " + id + " deleted successfully.");
//        } else {
//            return ResponseEntity.ok("Employee ID " + id + " not found.");
//        }
//    }
    public EmployeesResponseDto deleteEmployeeById(Long id) {
        Optional<Employees> product = employeesRepository.findById(id);
        if (product.isPresent()) {
            employeesRepository.deleteById(id);
            return new EmployeesResponseDto("Product deleted successfully");
        } else {
            return new EmployeesResponseDto("Product not found with ID: " + id);
        }
    }
    @Override
    public String updateEmployeeById(Long id, Employees updatedEmploees) {
        return employeeFacade.updateEmployeeById(id,updatedEmploees);
    }
}
