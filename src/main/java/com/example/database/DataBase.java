package com.example.database;

import com.example.model.Department;
import com.example.model.Director;
import com.example.model.Employee;
import com.example.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class DataBase {
    private List<Director> directors;
    private List<Employee> employees;

    private List<String> logMessages = new ArrayList<>();

    public void saveLogMessage(String message) {
        logMessages.add(message);
    }


    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    @PostConstruct
    public void init() {

        employees = new ArrayList<>();
        employees.add(new Employee("Arkadiy A.G", Post.JUNIOR));
        employees.add(new Employee("Akakiy G.N", Post.JUNIOR));
        employees.add(new Employee("Grigoriy G.G", Post.JUNIOR));
        employees.add(new Employee("Andrey D.N", Post.SENIOR));
        employees.add(new Employee("Egor E.V", Post.CLEANER));
        employees.add(new Employee("Magamed G.M", Post.MIDDLE));
        employees.add(new Employee("Vasya G.G", Post.MIDDLE));


        directors = new ArrayList<>();
        directors.add(new Director(Department.BACKEND, "Mikhail M.B.", getRandomEmployees(3)));
        directors.add(new Director(Department.BACKEND, "Evgeniy A.A.", getRandomEmployees(3)));
        directors.add(new Director(Department.BACKEND, "Georgiy M.N.", getRandomEmployees(4)));
        directors.add(new Director(Department.BACKEND, "Mikhail", getRandomEmployees(5)));


    }

    List<Employee> getRandomEmployees(int amount) {
        List<Employee> list = new ArrayList<>();
        while (amount > 0) {
            Random random = new Random();
            list.add(employees.get(random.nextInt(employees.size())));
            --amount;
        }
        return list;
    }




}
