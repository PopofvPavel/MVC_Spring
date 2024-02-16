package com.example.model;


public class Employee {

    private String fullName;

    private Post post;

    public Employee(String fullName, Post post) {
        this.fullName = fullName;
        this.post = post;
    }

    public Employee() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Employee : " + fullName + " post : " + post + " | ";
    }
}
