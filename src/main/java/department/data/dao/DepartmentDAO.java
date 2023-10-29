package department.data.dao;
import department.data.model.Department;
import department.data.model.Employee;
import department.di.annotation.Inject;
import department.di.annotation.Injectable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDAO implements DAO<Department> {

    private Connection connection;

    public DepartmentDAO() throws SQLException {
        this.connection =  DriverManager.getConnection("jdbc:h2:file:./companydb", "123", "123");
    }

    public void create(String name) throws SQLException {
        String sql = "INSERT INTO department (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    @Override
    public Department getById(int departmentId) throws SQLException {
        String sql = "SELECT * FROM department WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Department(resultSet.getInt("id"), resultSet.getString("name"));
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public Department getByName(String departmentName) throws SQLException {
        String sql = "SELECT * FROM department WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, departmentName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Department(resultSet.getInt("id"),resultSet.getString("name"));
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int departmentId) throws SQLException {
        String deleteEmployeesSql = "DELETE FROM Employee WHERE department_id = ?";
        try (PreparedStatement deleteEmployeesStatement = connection.prepareStatement(deleteEmployeesSql)) {
            deleteEmployeesStatement.setInt(1, departmentId);
            deleteEmployeesStatement.executeUpdate();
        }

        String deleteDepartmentSql = "DELETE FROM department WHERE id = ?";
        try (PreparedStatement deleteDepartmentStatement = connection.prepareStatement(deleteDepartmentSql)) {
            deleteDepartmentStatement.setInt(1, departmentId);
            deleteDepartmentStatement.executeUpdate();
        }
    }

    @Override
    public List<Department> getAll() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                departments.add(new Department(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }
        return departments;
    }

    public List<Employee> getEmployeesFromDepartment(int depId) throws SQLException {
        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT id, name, age, salary, department_id FROM Employee WHERE department_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, depId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    double salary = resultSet.getDouble("salary");

                    Employee employee = new Employee(employeeId, name, age, salary, depId);
                    employees.add(employee);
                }
            }
        }

        return employees;
    }

    @Override
    public void update(Department department) throws SQLException {
        String sql = "UPDATE department SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());
            statement.executeUpdate();
        }
    }
}
