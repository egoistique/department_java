package department.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 Класс отдела
 */

public class Department {
    private String name;

    private List<Employee> employees;

    private int numberOfEmployees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.numberOfEmployees = employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfEmployees() {
        numberOfEmployees = employees.size();
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public void addEmployee(Employee employee) {
        if (this.employees == null) {
            this.employees = new ArrayList<>();
        }

        this.employees.add(employee);
    }

}