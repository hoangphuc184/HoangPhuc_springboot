package com.example.springboot.restcontroller;

import com.example.springboot.entity.Employee;
import com.example.springboot.exception.EmployeeException;
import com.example.springboot.repository.EmployeeRepository;
import com.example.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getListEmployees() {

        return employeeService.retrieveEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp == null) {
            throw new EmployeeException(employeeId);
        }
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping
    public void createEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public HashMap<String, String> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp == null) {
            throw new EmployeeException(employeeId);
        }
        employeeService.deleteEmployee(employeeId);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Delete successfully");
        return map;
    }

    @PutMapping("/{employeeId}")
    Employee updateEmp(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }
}
