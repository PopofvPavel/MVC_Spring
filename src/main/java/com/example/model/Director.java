package com.example.model;


import java.util.List;



public class Director {

    private Department department;

    private String fullName;

    private List<Employee> employees;

    public Director() {

    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Director(Department department, String fullName, List<Employee> employees) {
        this.department = department;
        this.fullName = fullName;
        this.employees = employees;
    }
}
