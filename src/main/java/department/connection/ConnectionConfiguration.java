package department.connection;

public class ConnectionConfiguration {
    private String jdbcUrl;
    private String username;
    private String password;

    public ConnectionConfiguration() {
        this.jdbcUrl = "jdbc:h2:file:./companydb";
        this.username = "123";
        this.password = "123";
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
