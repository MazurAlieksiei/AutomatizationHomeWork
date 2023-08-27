package org.sqltask.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbProvider {

    private static final String CONNECTION_STR = "jdbc:sqlite:C:\\Users\\Alex\\IdeaProjects\\Automatization\\Homework_4\\home4.db";

    private static Connection connection;

    private DbProvider() {

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");

            } catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(CONNECTION_STR);

            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        return connection;
    }
}
