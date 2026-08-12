package com.example.employeemanagement.controller;

import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/employees-db")
public class EmployeeCrudController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeCrudController.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeCrudController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET tất cả
    @GetMapping
    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    // GET theo id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // POST thêm mới
    @PostMapping
    public EmployeeEntity create(@Valid @RequestBody EmployeeEntity employee) {
        EmployeeEntity saved = employeeRepository.save(employee);
        log.info("Đã thêm nhân viên mới: id={}, name={}", saved.getId(), saved.getName());
        return saved;
    }

    // PUT cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable Long id, @Valid @RequestBody EmployeeEntity updated) {
        return employeeRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            existing.setDepartment(updated.getDepartment());
            EmployeeEntity saved = employeeRepository.save(existing);
            log.info("Đã cập nhật nhân viên: id={}. name={}", saved.getId(), saved.getName());
            return ResponseEntity.ok(saved);
        }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
        log.info("Đã xóa nhân viên: id={}", id);
        return ResponseEntity.noContent().build();
    }

    // Tìm theo tên
    @GetMapping("/search-by-name")
    public List<EmployeeEntity> searchByName(@RequestParam String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Tìm theo phòng ban
    @GetMapping("/search-by-department")
    public List<EmployeeEntity> searchByDepartment(@RequestParam String department) {
        return employeeRepository.findByDepartment_NameIgnoreCase(department);
    }
}