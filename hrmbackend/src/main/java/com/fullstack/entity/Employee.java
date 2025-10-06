package com.fullstack.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    private String empName;

    private String empAddress;

    private double empSalary;

    @Column(unique = true)
    private long empContactNumber;

    private String empGender;

    private long empUID;

    @Column(unique = true)
    private String empPanCard;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date empDOB;

    @Column(unique = true)
    private String empEmailId;

    private String empPassword;
}
