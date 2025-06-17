package com.example.employee.controller;
import java.time.LocalDate;
import java.util.List;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeViewController {

    private final EmployeeService service;

    public EmployeeViewController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping()
    public String viewEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employees";
    }
}
