package be.pxl.student.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManager {
    private static Logger logger = (Logger) LogManager.getLogger(DAOManager.class);

    private String url;
    private String username;
    private String password;
    private Connection connection;

    public DAOManager(String url) {
        this.url = url;
    }

    public DAOManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || (connection.isClosed())) {
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
        }
        return connection;
    }


}
