package com.erp.employee_service;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController // 해당 Class(EmployeeController) REST API Controller 선언
@RequestMapping("/employees") // 모든 요청 주소 /employees로 시작하도록 설정
@RequiredArgsConstructor // final 필드 생성자 자동 생성
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    /* 생성자 생략 가능 */

    @PostMapping // POST /employees
    public Map<String, Long> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return Map.of("id", savedEmployee.getId());
    }

    @GetMapping("/{id}") // GET /employees/{id}
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @GetMapping // GET /employees
    public List<Employee> getEmployees(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String position) {

        List<Employee> allEmployees = employeeRepository.findAll();

        return allEmployees.stream()
                .filter(e -> (department == null || e.getDepartment().equals(department)))
                .filter(e -> (position == null || e.getPosition().equals(position)))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}") // PUT /employees/{id}
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        if (updates.containsKey("name") || updates.containsKey("id") || updates.containsKey("createdAt")) {
            throw new RuntimeException("Error: Only department and position can be updated.");
        }

        if (updates.containsKey("department")) {
            employee.setDepartment((String) updates.get("department"));
        }
        if (updates.containsKey("position")) {
            employee.setPosition((String) updates.get("position"));
        }

        return employeeRepository.save(employee);
    }
}