package com.example.employee.DTO;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

	import java.time.LocalDate;

	@Data
	public class EmployeeDTO {
		private Long id;
	    private String name;
	    private String department;
	    private double salary;
	   

	    

	    public EmployeeDTO(Long id, String name, String department, Double salary, LocalDate dateOfJoining) {
	        this.id = id;
	        this.name = name;
	        this.department = department;
	        this.salary = salary;
	       
	    }
	}


 
