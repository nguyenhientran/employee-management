package com.example.employeemanagement.controller;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // GET tất cả phòng ban
    @GetMapping
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    // GET theo id
    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        return departmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST thêm phòng ban mới
    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    // PUT cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department updated) {
        return departmentRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            return ResponseEntity.ok(departmentRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!departmentRepository.existsById(id)) return ResponseEntity.notFound().build();
        departmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}