package com.example.employee.controller;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller 
@RequestMapping("/employees") 
public class EmployeeController {

    private final EmployeeService employeeService;

   
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    
    @GetMapping("/v1/view")
    public String viewEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees); 
        return "employee-list"; 
    }

  
    @GetMapping("/v1/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee()); 
        return "employee-form"; 
    }

    
    @PostMapping("/v1/add")
    public String addEmployeeFromForm(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        
        if (employee.getDateOfJoining() == null) {
            employee.setDateOfJoining(LocalDate.now()); 
        }
        employeeService.addEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
        return "redirect:/employees/v1/view"; 
    }

   
   
    

    
    }

