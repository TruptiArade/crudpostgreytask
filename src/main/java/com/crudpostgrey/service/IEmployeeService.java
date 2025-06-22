package com.crudpostgrey.service;

import com.crudpostgrey.dto.EmployeeDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> fetchEmployeeList();
    EmployeeDTO fetchEmployeeById(Long empId);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long empId);
    EmployeeDTO updatepatchEmployee(EmployeeDTO employeeDTO, Long empId);
    //void deleteEmployeeById(Long empId);

    // Method to fetch employees with the second highest salary


    Double getSecondHighestSalary();

    List<EmployeeDTO> searchBySalaryRange(@Param("minSalary") double minSalary, @Param("maxSalary") double maxSalary);
}
