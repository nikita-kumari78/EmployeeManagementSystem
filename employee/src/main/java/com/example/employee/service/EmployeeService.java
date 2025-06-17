package com.example.employee.service;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service 
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

   
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    
    public Employee addEmployee(Employee employee) {
        
        return employeeRepository.save(employee);
    }

    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

    




