package com.example.employeemanagement.controller;

import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final EmployeeRepository employeeRepository;

    public StatisticsController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/by-department")
    public List<EmployeeRepository.DepartmentCountProjection> byDepartment() {
        return employeeRepository.countEmployeesByDepartment();
    }

    @GetMapping("/total")
    public Map<String, Long> total() {
        return Map.of("totalEmployees", employeeRepository.count());
    }
}