package com.example.employee.config;

import com.example.employee.interceptor.LoggingInterceptor;
import com.example.employee.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration 
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    private final LoggingInterceptor loggingInterceptor;
    private final EmployeeService employeeService; 

    
    @Autowired
    public WebConfig(LoggingInterceptor loggingInterceptor, EmployeeService employeeService) {
        this.loggingInterceptor = loggingInterceptor;
        this.employeeService = employeeService;
    }

   
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
        logger.info("LoggingInterceptor registered successfully.");
    }

    @PostConstruct
    public void initializeApplicationContext() {
        logger.info("Initializing application context: Fetching all employees on startup...");
        
        employeeService.getAllEmployees();
        logger.info("Employee data fetched during application startup.");
    }
}