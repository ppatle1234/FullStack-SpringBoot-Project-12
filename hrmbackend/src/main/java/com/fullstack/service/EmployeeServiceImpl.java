package com.fullstack.service;

import com.fullstack.entity.Employee;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean status = false;

        Employee employee = employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);

        if (employee != null){
            status = true;
        }
        return status;
    }

    @Override
    public List<Employee> saveAll(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList);
    }

    @Override
    public Optional<Employee> findById(long empId) {
        return Optional.ofNullable(employeeRepository.findById(empId).orElseThrow(() -> new RecordNotFoundException("EMPLOYEE #ID Does not Exist.")));
    }

    @Override
    public List<Employee> findByEmpName(String empName) {
        return employeeRepository.findByEmpName(empName);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(long empId, Employee employee) {

        Employee employee1 = findById(empId).get();

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpGender(employee.getEmpGender());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpUID(employee.getEmpUID());
        employee1.setEmpPanCard(employee.getEmpPanCard());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpGender(employee.getEmpGender());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        return employeeRepository.save(employee1);
    }

    @Override
    public Employee changeAddress(long empId, String empAddress) {
        Employee employee1 = findById(empId).get();
        employee1.setEmpAddress(empAddress);
        return employeeRepository.save(employee1);
    }

    @Override
    public Employee changeContactNumber(long empId, long empContactNumber) {
        Employee employee1 = findById(empId).get();
        employee1.setEmpContactNumber(empContactNumber);
        return employeeRepository.save(employee1);
    }

    @Override
    public void deleteById(long empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
