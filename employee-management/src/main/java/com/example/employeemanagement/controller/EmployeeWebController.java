package com.example.employeemanagement.controller;

import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeWebController(EmployeeRepository employeeRepository,
                                 DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // GET /employees/list → hiển thị danh sách
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee-list";   // trỏ đến file employee-list.html
    }

    // GET /employees/add → hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employee-add";
    }

    // POST /employees/add → xử lý submit form
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute EmployeeEntity employee) {
        employeeRepository.save(employee);
        return "redirect:/employees/list";
    }

    // GET /employees/search → trang tìm kiếm
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String department,
                         Model model) {
        java.util.List<EmployeeEntity> result;
        if (name != null && !name.isBlank()) {
            result = employeeRepository.findByNameContainingIgnoreCase(name);
        } else if (department != null && !department.isBlank()) {
            result = employeeRepository.findByDepartment_NameIgnoreCase(department);
        } else {
            result = employeeRepository.findAll();
        }
        model.addAttribute("employees", result);
        return "employee-search";
    }
}