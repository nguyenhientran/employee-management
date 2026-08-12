package com.example.employeemanagement.service;

import com.example.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportService.class);
    private final EmployeeRepository employeeRepository;

    public ReportService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Cacheable("employeeCountCache")
    public long getTotalEmployees() {
        log.info("Đang tính lại tổng số nhân viên từ DB (không lấy từ cache)");
        return employeeRepository.count();
    }
}