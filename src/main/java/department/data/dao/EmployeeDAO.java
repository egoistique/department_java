package department.data.dao;
import department.data.model.Department;
import department.data.model.Employee;
import department.di.annotation.Inject;
import department.di.annotation.Injectable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    String jdbcUrl = "jdbc:h2:file:I:/вуз/3 курс/databases/agencydb";
    String username = "123";
    String password = "123";
    @Inject
    private Connection connection;

    public EmployeeDAO() throws SQLException {
        this.connection =  DriverManager.getConnection(jdbcUrl, username, password);
    }

    public void createEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (name, age, salary, department_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, employee.getDepartmentId());
            statement.executeUpdate();
        }
    }

    public Employee getEmployeeById(int employeeId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Employee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getDouble("salary"),
                            resultSet.getInt("department_id")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, age = ?, salary = ?, department_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, employee.getDepartmentId());
            statement.setInt(5, employee.getId());
            statement.executeUpdate();
        }
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("department_id")
                ));
            }
        }
        return employees;
    }
}
