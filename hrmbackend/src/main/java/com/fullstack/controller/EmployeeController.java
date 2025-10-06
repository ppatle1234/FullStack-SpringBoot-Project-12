package com.fullstack.controller;

import com.fullstack.entity.Employee;
import com.fullstack.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final IEmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee){
        log.info("****** Trying to signUp for employee : " + employee.getEmpName());
        return new ResponseEntity<>(employeeService.signUp(employee), HttpStatus.CREATED);
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<Employee>> saveAll(@RequestBody List<Employee> employeeList){
        return new ResponseEntity<>(employeeService.saveAll(employeeList), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){
        return new ResponseEntity<>(employeeService.signIn(empEmailId, empPassword), HttpStatus.OK);
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable long empId){
        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }

    @GetMapping("/findbyname/{empName}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName){
        return new ResponseEntity<>(employeeService.findByEmpName(empName), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findbycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> findByContactNumber(@PathVariable long empContactNumber){
        return new ResponseEntity<>(employeeService.findAll().stream().filter(emp-> emp.getEmpContactNumber() == empContactNumber).toList().get(0), HttpStatus.OK);
    }

    @GetMapping("/findbyemailid/{empEmailId}")
    public ResponseEntity<Employee> findByEmailId(@PathVariable String empEmailId){
        return new ResponseEntity<>(employeeService.findAll().stream().filter(emp -> emp.getEmpEmailId().equals(empEmailId)).toList().get(0), HttpStatus.OK);
    }

    @GetMapping("/findbyuid/{empUID}")
    public ResponseEntity<Employee> findByUID(@PathVariable long empUID){
        return new ResponseEntity<>(employeeService.findAll().stream().filter(emp-> emp.getEmpUID() == empUID).toList().get(0), HttpStatus.OK);
    }

    @GetMapping("/findbypancard/{empPanCard}")
    public ResponseEntity<Employee> findByPanCard(@PathVariable String empPanCard){
        return new ResponseEntity<>(employeeService.findAll().stream().filter(emp -> emp.getEmpPanCard().equals(empPanCard)).toList().get(0), HttpStatus.OK);
    }

    @GetMapping("/findbydob/{empDOB}")
    public ResponseEntity<Employee> findByDOB(@PathVariable String empDOB){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return new ResponseEntity<>(employeeService.findAll().stream().filter(emp -> simpleDateFormat.format(emp.getEmpDOB()).equals(empDOB)).toList().get(0), HttpStatus.OK);
    }

    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<Employee> findByAnyInput(@PathVariable String input){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return new ResponseEntity<>(employeeService.findAll().stream().filter(emp -> simpleDateFormat.format(emp.getEmpDOB()).equals(input)
        || emp.getEmpName().equals(input)
        || String.valueOf(emp.getEmpId()).equals(input)
        || String.valueOf(emp.getEmpContactNumber()).equals(input)
        || String.valueOf(emp.getEmpUID()).equals(input)
        || emp.getEmpPanCard().equals(input)
        || emp.getEmpEmailId().equals(input)).toList().get(0), HttpStatus.OK);
    }

    @GetMapping("/sortbyiddesc")
    public ResponseEntity<List<Employee>> sortByIdDesc(){
        return new ResponseEntity<>(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpId).reversed()).toList(), HttpStatus.OK);
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName(){
        return new ResponseEntity<>(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList(), HttpStatus.OK);
    }


    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary(){
        return new ResponseEntity<>(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpSalary)).toList(), HttpStatus.OK);
    }

    @GetMapping("/sortbyDOB")
    public ResponseEntity<List<Employee>> sortByDOB(){
        return new ResponseEntity<>(employeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).toList(), HttpStatus.OK);
    }

    // Check Loan Eligiblity API
    // Input Employee ID
    // We are fetching data on the basis of #EmpID
    // Check salary >= 50000 if yes then return response as "Eligible For Loan" else return response as "Not Eligible For Loan"

    @GetMapping("/checkloaneligibility/{empId}")
    public ResponseEntity<String> checkLoanEligibility(@PathVariable long empId){

        Employee employee = findById(empId).getBody().get();
        String msg = "";
       /* if (employee.getEmpSalary() >= 50000){
            msg = "Eligible For Loan";
        } else {
            msg = "Not Eligible For Loan";
        }*/
        return new ResponseEntity<>(employee.getEmpSalary() >= 50000.0 ? "Eligible For Loan" :"Not Eligible For Loan", HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable long empId, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.update(empId, employee), HttpStatus.CREATED);
    }

    @PatchMapping("/changeaddress/{empId}/{empAddress}")
    public ResponseEntity<Employee> changeAddress(@PathVariable long empId, @PathVariable String empAddress){
        return new ResponseEntity<>(employeeService.changeAddress(empId, empAddress), HttpStatus.CREATED);
    }

    @PatchMapping("/changecontactnumber/{empId}/{empContactNumber}")
    public ResponseEntity<Employee> changeContactNumber(@PathVariable long empId, @PathVariable long empContactNumber){
        return new ResponseEntity<>(employeeService.changeContactNumber(empId, empContactNumber), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable long empId){
        employeeService.deleteById(empId);
        return new ResponseEntity<>("Data Deleted Successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeService.deleteAll();
        return new ResponseEntity<>("All Data Deleted Successfully.", HttpStatus.OK);
    }
}
