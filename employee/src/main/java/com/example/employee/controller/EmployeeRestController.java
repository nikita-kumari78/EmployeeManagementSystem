package com.example.employee.controller;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

public class EmployeeRestController {
	@RestController 
	@RequestMapping("/employees") 
	public class EmployeeController {

	    private final EmployeeService employeeService;

	   
	    @Autowired
	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }

	    
	    @GetMapping("/view")
	    public String viewEmployees(Model model) {
	        List<Employee> employees = employeeService.getAllEmployees();
	        model.addAttribute("employees", employees); 
	        return "employee-list"; 
	    }

	   
	    @GetMapping("/add")
	    public String showAddEmployeeForm(Model model) {
	        model.addAttribute("employee", new Employee()); 
	        return "employee-form"; 
	    }

	    
	    @PostMapping("/add")
	    public String addEmployeeFromForm(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
	        
	        if (employee.getDateOfJoining() == null) {
	            employee.setDateOfJoining(LocalDate.now()); 
	        }
	        employeeService.addEmployee(employee);
	        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
	        return "redirect:/employees/view"; 
	    }

	   
	    @PostMapping("/add")
	    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
	        
	        if (employee.getDateOfJoining() == null) {
	            employee.setDateOfJoining(LocalDate.now());
	        }
	        Employee newEmployee = employeeService.addEmployee(employee);
	        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
	    }

	    
	    @GetMapping("/view")
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	        List<Employee> employees = employeeService.getAllEmployees();
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }
	}


}
