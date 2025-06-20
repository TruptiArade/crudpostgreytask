package com.crudpostgrey.repository;

import com.crudpostgrey.dto.EmployeeDTO;
import com.crudpostgrey.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,  Long>
{

    @Query("SELECT DISTINCT e.salary FROM EmployeeEntity e ORDER BY e.salary DESC")
    List<Double> findDistinctSalaries();
    default Double findSecondHighestSalary() {
        List<Double> salaries = findDistinctSalaries();
        if (salaries.size() < 2) {
            return null; // or throw an exception if you prefer
        }
        return salaries.get(1); // Return the second highest salary
    }

}
