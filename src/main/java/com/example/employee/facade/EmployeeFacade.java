package com.example.employee.facade;

import com.example.employee.dto.EmployeesRequestDto;
import com.example.employee.dto.EmployeesResponseDto;
import com.example.employee.entity.Employees;
import com.example.employee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class EmployeeFacade {
    @Autowired
    EmployeesRepository employeesRepository;

    public EmployeesResponseDto createUser(EmployeesRequestDto reqDto) {
        Employees employees = reqDto.getEmployees();
        if (employees.getName().matches("\\d+")||employees.getName().isEmpty()) {
            return new EmployeesResponseDto("Give a proper name");
        }
        if (employees.getDepartment().matches("\\d+")||employees.getDepartment().isEmpty()) {
            return new EmployeesResponseDto("Give a proper value to Department.");
        }
        if (employees.getDesignation().isEmpty()||employees.getDesignation().matches("\\d+")) {
            return new EmployeesResponseDto("Give a proper value to Designation");
        }
        if (employees.getSalary().isEmpty()) {
            return new EmployeesResponseDto("Employee Salary is empty");
        }
        try {
            Integer.parseInt(employees.getSalary());
        } catch (NumberFormatException e) {
            return new EmployeesResponseDto("Employee Salary should be integer");
        }
        if (employees.getJoiningDate() == null) {
            return new EmployeesResponseDto("Employee Joining Date is empty");
        }
        try {
            employeesRepository.save(employees);
            return new EmployeesResponseDto("Employee Details Added Successfully");
        } catch (Exception e) {
            return new EmployeesResponseDto("Error: " + e.getMessage());
        }
    }
    public String updateEmployeeById(Long id, Employees updatedEmploees) {
        Optional<Employees> employeeOptional = employeesRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employees existingEmployee = employeeOptional.get();
            if(updatedEmploees.getName().length()<3) {
                throw new RuntimeException(" Error: Write Your Full Name");
            }
            if ( updatedEmploees.getName().isEmpty()||updatedEmploees.getName().matches("\\d+")) {
                return "Give a proper name";
            }
            if ( updatedEmploees.getDepartment().isEmpty()||updatedEmploees.getDepartment().matches("\\d+")) {
                return "Give a proper value to department";
            }
            if (updatedEmploees.getDesignation().isEmpty()||updatedEmploees.getDesignation().matches("\\d+")) {
                return "Give a proper value to designation";
            }
            if (updatedEmploees.getSalary().isEmpty()) {
                return "Employee Salary is empty";
            }
            try {
                Integer.parseInt(updatedEmploees.getSalary());
            } catch (NumberFormatException e) {
                return "Employee Salary should be integer";
            }
            if (updatedEmploees.getJoiningDate() == null) {
                return "Employee Joining Date is empty";
            }
            existingEmployee.setName(updatedEmploees.getName());
            existingEmployee.setDepartment(updatedEmploees.getDepartment());
            existingEmployee.setDesignation(updatedEmploees.getDesignation());
            existingEmployee.setSalary(updatedEmploees.getSalary());
            existingEmployee.setJoiningDate(updatedEmploees.getJoiningDate());
            employeesRepository.save(existingEmployee);
            return "Employee details updated successfully.";
        } else {
            throw new RuntimeException("Employee with ID " + id + " is not found.");
        }
    }
}
