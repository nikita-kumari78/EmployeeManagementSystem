package com.example.employee.config;

import com.example.employee.interceptor.LoggingInterceptor;
import com.example.employee.service.EmployeeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final EmployeeService service;

    public WebConfig(EmployeeService service) {
        this.service = service;
        System.out.println("Loaded Employees at startup: " + service.getAllEmployees().size());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
    }
}
