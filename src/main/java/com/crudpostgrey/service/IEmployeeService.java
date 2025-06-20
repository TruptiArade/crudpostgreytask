package com.crudpostgrey.service;

import com.crudpostgrey.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> fetchEmployeeList();
    EmployeeDTO fetchEmployeeById(Long empId);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long empId);
    EmployeeDTO updatepatchEmployee(EmployeeDTO employeeDTO, Long empId);
    void deleteEmployeeById(Long empId);

    // Method to fetch employees with the second highest salary


    Double getSecondHighestSalary();
}
