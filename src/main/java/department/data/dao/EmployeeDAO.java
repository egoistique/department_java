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

    private Connection connection;
    private EmployeeORM employeeORM;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
        this.employeeORM = new EmployeeORM(connection);
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

    public Employee getById(int employeeId) throws SQLException {
        return employeeORM.getById(employeeId);
    }

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

    public List<Employee> getAll() throws SQLException {
        return employeeORM.getAll();
    }

    public Employee getByName(String name) {
        return employeeORM.getByName(name);
    }


}
