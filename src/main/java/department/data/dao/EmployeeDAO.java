package department.data.dao;
import department.data.model.Department;
import department.data.model.Employee;
import department.di.annotation.Inject;
import department.di.annotation.Injectable;
import department.orm.EmployeeORM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO implements DAO<Employee>{

    private EmployeeORM employeeORM;

    public EmployeeDAO() throws SQLException {
        this.employeeORM = new EmployeeORM();
    }

    public void create(int depId, String name, int age, double salary) throws SQLException {
        employeeORM.beginTransaction();
        try {
            employeeORM.create(depId, name, age, salary);
            employeeORM.commitTransaction();
        } catch (SQLException e) {
            employeeORM.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public Employee getById(int employeeId) throws SQLException {
        return employeeORM.getById(employeeId);
    }

    @Override
    public void update(Employee employee) throws SQLException {
        employeeORM.beginTransaction();
        try {
            employeeORM.update(employee);
            employeeORM.commitTransaction();
        } catch (SQLException e) {
            employeeORM.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public void delete(int employeeId) throws SQLException {
        employeeORM.beginTransaction();
        try {
            employeeORM.delete(employeeId);
            employeeORM.commitTransaction();
        } catch (SQLException e) {
            employeeORM.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return employeeORM.getAll();
    }

    @Override
    public Employee getByName(String name) {
        return employeeORM.getByName(name);
    }

    @Override
    public void close(){
        employeeORM.close();
    }
}
