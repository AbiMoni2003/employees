package com.example.employee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "employee_details")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "department",nullable = false)
    private String department;
    @Column(name = "designation",nullable = false)
    private String designation;
    @Column(name = "salary",nullable = false)
    private String Salary;

    @Column(name = "joining_data",nullable = false,updatable = false)
    private Date joiningDate;
}
