package com.example.employeemanagement.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Không tìm thấy nhân viên với id: " + id);
    }
}