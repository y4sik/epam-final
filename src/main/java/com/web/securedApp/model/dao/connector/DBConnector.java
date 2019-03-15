package com.web.securedApp.model.dao.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = Logger.getLogger(DBConnector.class);


    private Connection connection;

    private static DBConnector instance = null;

    private DBConnector() {
        connect();
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    private void connect() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String url = resource.getString("db.url");
            String user = resource.getString("db.user");
            String pass = resource.getString("db.password");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(url, user, pass);
            LOGGER.info("Connection successful");
        } catch (NullPointerException e) {
            LOGGER.error("ResourceBundle failed: ", e);
        } catch (SQLException e) {
            System.out.println("Connection error");
            LOGGER.error("Database connection failed: ", e);

            e.printStackTrace();
        }
    }

    public Connection DBConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed check isClosed connection", e);
        }
        return connection;
    }

}
