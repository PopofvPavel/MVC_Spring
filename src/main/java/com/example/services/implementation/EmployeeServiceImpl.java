package com.example.services.implementation;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.repository.EmployeeRepositoryImpl;
import com.example.services.EmployeeService;
import com.example.services.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepositoryImpl employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(Employee employee) throws IllegalArgumentException{
        checkUniqueName(employee.getFullName());
        employeeRepository.addEmployee(employee);
    }

    private void checkUniqueName(String fullName) throws IllegalArgumentException {
        boolean hasThisNameInList =  employeeRepository.getEmployeeList().stream()
                .anyMatch(e -> e.getFullName().equals(fullName));
        if (hasThisNameInList) {
            throw new IllegalArgumentException("Name should be unique, this name is already in the list");
        }
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
