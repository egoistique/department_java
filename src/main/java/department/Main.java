package department;

import department.data.dao.DepartmentDAO;
import department.data.dao.EmployeeDAO;
import department.view.ConsoleView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {

        String jdbcUrl = "jdbc:h2:file:I:/вуз/3 курс/databases/agencydb";
        String username = "123";
        String password = "123";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Соединение установлено.");

            try (Statement statement = connection.createStatement()) {
                String createTablesSQL = "CREATE TABLE IF NOT EXISTS department (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255));" +
                        "CREATE TABLE IF NOT EXISTS employee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT, salary DOUBLE, department_id INT, FOREIGN KEY (department_id) REFERENCES department(id));";
                statement.execute(createTablesSQL);
                System.out.println("Таблицы созданы.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConsoleView view = new ConsoleView();
        view.displayMenu();
    }
}

