package com.afs.employee.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    List<Employee> employees;

    public EmployeeRepository(){
        this.employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 8000));
        employees.add(new Employee(3, "Lucy", 21, "Female", 8000));
        employees.add(new Employee(4, "Poyo", 20, "Male", 8000));
    }

    public List<Employee> findAll(){
        return employees;
    }

    public Employee findEmployeeById(Integer id) {
        return employees.stream().filter(employee->employee.getId()==id).findFirst().orElseThrow(NoEmployeeFoundException::new) ;
    }

    public List<Employee> findEmployeeByGender(String gender) {
        return employees.stream().filter(employee-> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        Integer id = generateNextId();
        employee.setId(id);
        employees.add(employee);
        return employee;
    }

    private Integer generateNextId() {
        int nextId = employees.stream()
                .mapToInt(employee -> employee.getId())
                .max()
                .orElse(1);
        return nextId + 1;
    }

    public Employee update(Integer id, Employee employee) {
        Employee existingEmployee = findEmployeeById(id);
        if (employee.getAge() != null){
            existingEmployee.setAge(employee.getAge());
        }
        if(employee.getSalary()!= null){
            existingEmployee.setSalary(employee.getSalary());
        }
        return existingEmployee;
    }

    public void delete(Integer id) {
        employees.removeIf(employee -> employee.getId()==id);
    }

    public List<Employee> queryPage(int page, int pageSize) {
        return employees.subList((page-1)*pageSize,page*pageSize);
    }

    public void clearAll(){
        employees.clear();
    }
}
