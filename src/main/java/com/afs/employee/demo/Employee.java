package com.afs.employee.demo;

public class Employee {
    private Integer id;
    private String name;

    private Integer age;

    private Integer salary;

    private String gender;

    public Employee(Integer id, String name ,Integer age, String gender, Integer salary){
        this.id = id;
        this.name = name;
        this.age =age;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
