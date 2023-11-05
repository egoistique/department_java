package department.connection;

import department.di.annotation.Inject;
import department.di.annotation.Injectable;
import department.di.factory.BeanFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Injectable
public class DatabaseConnectionManager {
    @Inject
    ConnectionConfiguration configuration = BeanFactory.getInstance().getBean(ConnectionConfiguration.class);
    private final String jdbcUrl = configuration.getJdbcUrl();
    private final String username = configuration.getUsername();
    private final String password = configuration.getPassword();

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("manager closed connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
