package com.crudpostgrey.service.Impl;

import com.crudpostgrey.customexception.ResourceNotFoundException;
import com.crudpostgrey.customexception.ValidationException;
import com.crudpostgrey.dto.EmployeeDTO;
import com.crudpostgrey.entity.EmployeeEntity;
import com.crudpostgrey.repository.EmployeeRepository;
import com.crudpostgrey.service.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl  implements IEmployeeService
{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String emailreg= "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    private static final String mobileno="[6-9][0-9]{9}$";
    private static final String name="^[A-Za-z]{2,50}$";

    private  static  boolean validateEname(String empName)
    {
        if(empName == null || !Pattern.matches(name,empName))
        {
            throw new ValidationException("Enter only characters with range 2 to 15");
        }
        return  true;
    }

    private  static  boolean validatedesignation(String designation)
    {
        if(designation == null || !Pattern.matches(name,designation))
        {
            throw new ValidationException("Enter only characters with range 2 to 15");
        }
        return  true;
    }

    private static boolean validateEmail(String mail)
    {
        if(mail == null || !Pattern.matches(emailreg,mail))
        {
            throw  new ValidationException("Enter valid email for eg truptiarade45@gmail.com");
        }
        return  true;
    }

    private static boolean validateMobile(String mob)
    {
        if(mob == null || !Pattern.matches(mobileno,mob))
        {
            throw new ValidationException("Enter 10 digit only & starting with 6/7/8/9");
        }
        return true;
    }


    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO)
    {
        validateEname(employeeDTO.getEmpName());
        validatedesignation(employeeDTO.getDesignation());
        validateEmail(employeeDTO.getEmail());
        validateMobile(employeeDTO.getMobile());


        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> fetchEmployeeList()
    {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(entity -> modelMapper.map(entity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO fetchEmployeeById(Long empId)
    {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long empId)
    {
        EmployeeEntity existingEntity = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));

        //modelMapper.map(employeeDTO, existingEntity);

        if(employeeDTO.getEmpName()!=null) {
            validateEname(employeeDTO.getEmpName());
            existingEntity.setEmpName(employeeDTO.getEmpName());
        }

        existingEntity.setEmpAddress(employeeDTO.getEmpAddress());

        if(employeeDTO.getDesignation()!=null)
        {
            validatedesignation(employeeDTO.getDesignation());
            existingEntity.setDesignation(employeeDTO.getDesignation());
        }

        existingEntity.setSalary(employeeDTO.getSalary());

        if(employeeDTO.getEmail()!=null)
        {
            validateEmail(employeeDTO.getEmail());
            existingEntity.setEmail(employeeDTO.getEmail());
        }

        if(employeeDTO.getMobile()!=null)
        {
            validateMobile(employeeDTO.getMobile());
            existingEntity.setMobile(employeeDTO.getMobile());
        }



        EmployeeEntity updatedEntity = employeeRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updatepatchEmployee(EmployeeDTO employeeDTO, Long empId)
    {
        EmployeeEntity existingEntity = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));


        if (employeeDTO.getEmpName() != null)
        {
            validateEname(employeeDTO.getEmpName());
            existingEntity.setEmpName(employeeDTO.getEmpName());
        }

        if (employeeDTO.getEmpAddress() != null) existingEntity.setEmpAddress(employeeDTO.getEmpAddress());

        if (employeeDTO.getDesignation() != null)
        {
            validatedesignation(employeeDTO.getDesignation());
            existingEntity.setDesignation(employeeDTO.getDesignation());
        }
        if (employeeDTO.getSalary() != null) existingEntity.setSalary(employeeDTO.getSalary());



        if(employeeDTO.getEmail()!= null)
        {
            validatedesignation(employeeDTO.getEmail());
            existingEntity.setEmail(employeeDTO.getEmail());
        }

        if(employeeDTO.getMobile()!= null)
        {
            validateMobile(employeeDTO.getMobile());
            existingEntity.setMobile(employeeDTO.getMobile());
        }

        EmployeeEntity updatedEntity = employeeRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployeeById(Long empId)
    {
        EmployeeEntity existingEntity = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));
        employeeRepository.delete(existingEntity);
    }

    @Override
    public Double getSecondHighestSalary() {
        return employeeRepository.findSecondHighestSalary();
    }




}
