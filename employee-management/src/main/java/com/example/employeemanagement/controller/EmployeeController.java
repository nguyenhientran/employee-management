package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // Danh sách in-memory (dữ liệu tạm, mất khi restart app)
    private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // GET /api/employees → lấy danh sách tất cả nhân viên
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    // GET /api/employees/{id} → lấy 1 nhân viên theo id (path variable)
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/employees → thêm nhân viên mới
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee.setId(idCounter.getAndIncrement());
        employees.add(employee);
        return ResponseEntity.ok(employee);
    }

    // GET /api/employees/search?department=IT → tìm theo request param
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchByDepartment(@RequestParam String department) {
        List<Employee> result = employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .toList();
        return ResponseEntity.ok(result);
    }
}