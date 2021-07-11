package ua.practice.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection(){
        Connection connection;
        try {
            Properties props = new Properties();
            try (InputStream in = Main.class.getClassLoader().getResourceAsStream("database.properties")) {
                props.load(in);
            }
            String dbHost = props.getProperty("dbHost");
            String dbName = props.getProperty("dbName");
            String dbUrl = dbHost + "/" + dbName;
            String dbUser = props.getProperty("dbUser");
            String dbPassword = props.getProperty("dbPassword");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed");
            return null;
        }
        return connection;
    }

}
