package com.example.services;

import com.example.model.Employee;
import com.example.services.implementation.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    Employee getEmployeeByFullName(String fullName) throws EmployeeNotFoundException;

    void deleteEmployeeByFullName(String fullName) throws EmployeeNotFoundException;

    List<Employee> getEmployees();
}
