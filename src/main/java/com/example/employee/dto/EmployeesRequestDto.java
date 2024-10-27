package com.example.employee.dto;

import com.example.employee.entity.Employees;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeesRequestDto {
    private String name;
    private String department;
    private String designation;
    private String salary;
    private Date joiningDate;

    public Employees getEmployees(){
        Employees employees=new Employees();
        employees.setName(this.name);
        employees.setDepartment(this.department);
        employees.setDesignation(this.designation);
        employees.setSalary(this.salary);
        employees.setJoiningDate(new Date());
        return employees;
    }
}
