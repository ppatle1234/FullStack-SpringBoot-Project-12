package com.fullstack.service;

import com.fullstack.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Employee signUp(Employee employee);

    boolean signIn(String empEmailId, String empPassword);

    List<Employee> saveAll(List<Employee> employeeList);

    Optional<Employee> findById(long empId);

    List<Employee> findByEmpName(String empName);

    List<Employee> findAll();

    Employee update(long empId, Employee employee);

    Employee changeAddress(long empId, String empAddress);

    Employee changeContactNumber(long empId, long empContactNumber);

    void deleteById(long empId);

    void deleteAll();

}
