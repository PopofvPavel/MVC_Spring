package com.example.repository;

import com.example.database.DataBase;
import com.example.model.Employee;
import com.example.services.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
   DataBase dataBase;

   @Autowired
    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    private List<Employee> employeeList;

    @PostConstruct
    public void init() {
        employeeList = dataBase.getEmployees();
    }

    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);

    }

    public void deleteEmployee(Employee employee) {
        int index = getEmployeeIndexInListByName(employee);
        employeeList.remove(index);
    }

    public int getEmployeeIndexInListByName(Employee employee) {
        for (int i = 0; i < employeeList.size(); i++) {
            Employee current = employeeList.get(i);
            if (employee.getFullName().equals(current.getFullName())) {
                return i;
            }
        }
        return -1;
    }

    public void updateEmployee(Employee employees) {
        int index = getEmployeeIndexInListByName(employees);
        employeeList.set(index, employees);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee getEmployeeByFullName(String fullName) throws EmployeeNotFoundException {
        Optional<Employee> result = employeeList.stream()
                .filter(employee -> employee.getFullName().equals(fullName))
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }
}
