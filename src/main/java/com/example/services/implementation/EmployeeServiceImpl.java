package com.example.services.implementation;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(employee);
    }

    @Override
    public Employee getEmployeeByFullName(String fullName) throws EmployeeNotFoundException {

            return employeeRepository.getEmployeeByFullName(fullName);

    }

    @Override
    public void deleteEmployeeByFullName(String fullName) throws EmployeeNotFoundException {
        Employee employee = getEmployeeByFullName(fullName);
        employeeRepository.deleteEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployeeList();
    }
}
