package department.data.dao;
import department.data.model.Department;
import department.data.model.Employee;
import department.di.annotation.Inject;
import department.di.annotation.Injectable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Injectable
public class DepartmentDAO {

    private Connection connection;

    public DepartmentDAO() throws SQLException {
        this.connection =  DriverManager.getConnection("jdbc:h2:file:I:/вуз/3 курс/databases/agencydb", "123", "123");
    }

    public void createDepartment(Department department) throws SQLException {
        String sql = "INSERT INTO department (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getName());
            statement.executeUpdate();
        }
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
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

    public Department getDepartmentByName(String departmentName) throws SQLException {
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

    public void deleteDepartment(int departmentId) throws SQLException {
        String sql = "DELETE FROM department WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            statement.executeUpdate();
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
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


    public void addEmployeeToDepartment(Employee employee, String depName){

    }
//    public void updateDepartment(Department department) throws SQLException {
//        String sql = "UPDATE department SET name = ? WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, department.getName());
//            statement.setInt(2, department.getId());
//            statement.executeUpdate();
//        }
//    }
}
