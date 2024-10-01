package br.olinda.factory.model.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/factory";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection conn = null;

    protected static Connection getCurrentConnection() throws SQLException {

        if (conn == null)
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
              return conn;

    }

    protected static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}