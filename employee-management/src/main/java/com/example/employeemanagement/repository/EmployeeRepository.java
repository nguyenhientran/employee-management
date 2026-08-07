package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByNameContainingIgnoreCase(String name);
    List<EmployeeEntity> findByDepartment_NameIgnoreCase(String departmentName);
}