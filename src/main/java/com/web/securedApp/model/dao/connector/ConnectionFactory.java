package com.web.securedApp.model.dao.connector;

import org.apache.log4j.Logger;

import java.sql.*;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    public static Connection getConnection() {

        DBConnector dbConnector = DBConnector.getInstance();
        return dbConnector.DBConnection();
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception in closeConnection: " + e);
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception in closeStatement: " + e);
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception in closePreparedStatement: " + e);
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception in closeResultSet: " + e);
        }
    }

}