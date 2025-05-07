package fr.bts.sio.tp_calculatrice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL  = "jdbc:mysql://localhost:3306/bts_sio";
    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection instance;

    private DbConnection() { }

    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(URL, USER, PASS);
        }
        return instance;
    }
}
