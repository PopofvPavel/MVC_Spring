package com.example.controller;

import com.example.database.DataBase;
import com.example.model.Director;
import com.example.model.Employee;
import com.example.services.DirectorService;
import com.example.services.EmployeeService;
import com.example.services.exceptions.DirectorNotFoundException;
import com.example.services.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/director")
public class DirectorController {

    DirectorService directorService;
    EmployeeService employeeService;

    @Autowired
    DataBase dataBase;
    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDirectorService(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public String showDirectorsPage(Model model) {
        model.addAttribute("directors", directorService.getDirectors());
        //System.out.println("DB" + dataBase.getLogMessageString());
        return "directors-page";
    }

    @GetMapping("/add")
    public String showAddDirectorPage(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        Director director = new Director();
        model.addAttribute("director", director);
        return "add_director";
    }

    @PostMapping("/add")
    public String showDirectorsPageOnAddDirector(@ModelAttribute("director") Director director,
                                                 @RequestParam(value = "added", required = false) List<String> addedEmployeesList,
                                                 Model model) {
        if (addedEmployeesList == null || addedEmployeesList.size() < 3) {
            model.addAttribute("error", "You must select at least 3 employees.");
            System.out.println("Please select at least 3 employees");
            model.addAttribute("employees", employeeService.getEmployees());
            return "add_director";
        }
        //System.out.println("Selected " + addedEmployeesList);
        List<Employee> selectedEmployees = new ArrayList<>();
        for (String added : addedEmployeesList) {
            try {
                Employee employee = employeeService.getEmployeeByFullName(added);
                selectedEmployees.add(employee);
            } catch (EmployeeNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        director.setEmployees(selectedEmployees);

        try {
            directorService.createDirector(director);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("director", director);
            model.addAttribute("employees", employeeService.getEmployees());
            System.out.println("In catch block");
            return "add_director";
        }
        return "redirect:/director";
    }

    @GetMapping("/update")
    public String showUpdateDirectorPage(Model model, @RequestParam(value = "fullname") String fullname) {
        Director director;
        try {
            director = directorService.getDirectorByFullName(fullname);
        } catch (DirectorNotFoundException e) {
            return "director-not-found";
        }
        model.addAttribute("director", director);
        return "update-director";
    }

    @PostMapping("/update")
    public String showUpdateDirectorPageOnUpdateDirector(@ModelAttribute("director") Director director) {
        directorService.updateDirector(director);
        return "redirect:/director";
    }


    @GetMapping("/delete")
    public String deleteDirector(Model model, @RequestParam(value = "fullname") String fullname) {
        Director director;
        try {
            director = directorService.getDirectorByFullName(fullname);
            directorService.deleteDirectorByFullName(director.getFullName());

        } catch (DirectorNotFoundException e) {
            return "director-not-found";
        }
        return "redirect:/director";
    }




}
