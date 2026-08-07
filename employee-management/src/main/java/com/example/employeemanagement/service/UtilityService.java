package com.example.employeemanagement.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UtilityService {

    // Tạo mã nhân viên tự động
    public String generateEmployeeCode() {
        return "NV-" + UUID.randomUUID().toString()
                .substring(0, 8).toUpperCase();
    }

    // Format chuỗi: viết hoa chữ cái đầu mỗi từ
    public String formatName(String name) {
        if (name == null || name.isBlank()) return name;
        String[] words = name.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String w : words) {
            result.append(Character.toUpperCase(w.charAt(0)))
                    .append(w.substring(1).toLowerCase())
                    .append(" ");
        }
        return result.toString().trim();
    }
}