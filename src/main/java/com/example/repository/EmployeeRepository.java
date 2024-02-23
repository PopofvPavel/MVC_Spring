package com.example.repository;

import com.example.database.DataBase;
import com.example.model.Employee;
import com.example.services.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeRepository {

    void addEmployee(Employee employee);

    int getEmployeeIndexInListByName(Employee employee);

    void updateEmployee(Employee employees);

    List<Employee> getEmployeeList();

    Employee getEmployeeByFullName(String fullName) throws EmployeeNotFoundException;

    void deleteEmployee(Employee employee);
}
