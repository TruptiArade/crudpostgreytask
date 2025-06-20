package com.crudpostgrey.controller;

import com.crudpostgrey.dto.EmployeeDTO;
import com.crudpostgrey.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> fetchEmployeeList() {
        List<EmployeeDTO> employeeList = employeeService.fetchEmployeeList();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> fetchEmployeeById(@PathVariable("id") Long empId) {
        EmployeeDTO employee = employeeService.fetchEmployeeById(empId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable("id") Long empId) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeDTO, empId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

   /* @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updatePatchEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable("id") Long empId) {
        EmployeeDTO patchedEmployee = employeeService.updatePatchEmployee(employeeDTO, empId);
        return new ResponseEntity<>(patchedEmployee, HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long empId) {
        employeeService.deleteEmployeeById(empId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/second-highest-salary")
    public ResponseEntity<Double> getSecondHighestSalary() {
        Double secondHighestSalary = employeeService.getSecondHighestSalary();
        if (secondHighestSalary == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(secondHighestSalary);
    }

    @GetMapping("/search/salary")
    public ResponseEntity<List<EmployeeDTO>> searchBySalaryRange(
            @RequestParam double minSalary,
            @RequestParam double maxSalary) {
        List<EmployeeDTO> employees = employeeService.searchBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }

}

