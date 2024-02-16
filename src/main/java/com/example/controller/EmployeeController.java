package com.example.controller;

import com.example.model.Director;
import com.example.model.Employee;
import com.example.services.EmployeeService;
import com.example.services.implementation.DirectorNotFoundException;
import com.example.services.implementation.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String showEmployeePage(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "employees-page";
    }

    @GetMapping("/add")
    public String showAddEmployeePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-employee";
    }

    @PostMapping("/add")
    public String showEmployeesPageOnAddEmployee(@ModelAttribute("director") Employee employee) {
        System.out.println("In post method");
        employeeService.createEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/update")
    public String showUpdateEmployeePage(Model model, @RequestParam(value = "fullname") String fullname) {
        Employee employee;
        try {
            employee = employeeService.getEmployeeByFullName(fullname);
        } catch (EmployeeNotFoundException e) {
            return "employee-not-found";
        }
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/update")
    public String showUpdateEmployeePageOnUpdateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employee";
    }


    @GetMapping("/delete")
    public String deleteEmployee(Model model, @RequestParam(value = "fullname") String fullname) {
        Employee employee;
        try {
            employee = employeeService.getEmployeeByFullName(fullname);
            employeeService.deleteEmployeeByFullName(employee.getFullName());

        } catch (EmployeeNotFoundException e) {
            return "employee-not-found";
        }
        return "redirect:/employee";
    }
}
