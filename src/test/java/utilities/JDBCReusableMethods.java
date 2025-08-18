package utilities;

import java.sql.*;

public class JDBCReusableMethods {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void createMyConnection(){

        String URL = ConfigReader.getProperty("URL");
        String USERNAME = ConfigReader.getProperty("USERNAME");
        String PASSWORD = ConfigReader.getProperty("PASSWORD");

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getMyConnection(){

        String URL = ConfigReader.getProperty("URL");
        String USERNAME = ConfigReader.getProperty("USERNAME");
        String PASSWORD = ConfigReader.getProperty("PASSWORD");

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeMyConnection(){

        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static Statement getStatement(){
        createMyConnection();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    public static ResultSet executeMyQuery(String Query){

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            resultSet = statement.executeQuery(Query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public static int getRowCount() {
        try {
            resultSet.last();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int rowCount = 0;
        try {
            rowCount = resultSet.getRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowCount;
    }

    public static Integer executeMyUpdateQuery(String query){
        int affectedRowCount;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            affectedRowCount = statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return affectedRowCount;
    }

}
