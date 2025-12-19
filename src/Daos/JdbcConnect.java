package Daos;

import java.sql.*;

public class JdbcConnect {

    private final String driver = "org.mariadb.jdbc.Driver";
    private final String login = "tp_bank";
    private final String password = "braquo";
    public java.sql.Connection connection;

    public JdbcConnect() throws Exception {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tp_bank", login, password);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
