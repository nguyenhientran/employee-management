package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByNameContainingIgnoreCase(String name);
    List<EmployeeEntity> findByDepartment_NameIgnoreCase(String departmentName);

    // Thống kê số nhân viên theo từng phòng ban
    @Query("SELECT e.department.name AS department, COUNT(e) AS total " +
            "FROM EmployeeEntity e GROUP BY e.department.name")
    List<DepartmentCountProjection> countEmployeesByDepartment();

    interface DepartmentCountProjection {
        String getDepartment();
        Long getTotal();
    }
}