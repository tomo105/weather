package com.ppak.controller;

import com.ppak.Logger;
import com.ppak.model.Weather;

import java.sql.*;
import java.util.List;

public class ConnectionDb {
    private Connection connection;
    private Logger logger;

    /**
     * @return connection with mysql driver
     */

    public ConnectionDb(){
        logger = Logger.getInstance();
    }


    public Connection getConnection() {

        String dbName = "weather_db";
        String userName = "root";
        String password = "poziomka16";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC", userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            if (!connection.isValid(2)) {
                connection = null;
                throw new SQLException("Invalid Connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * creates DB
     */
    public void createDB() {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=poziomka16&serverTimezone=UTC");
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS weather_db");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates tables in DB
     */
    public void createTables() {
        Connection connection = new ConnectionDb().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS weather(date VARCHAR(50), clouds VARCHAR(50), id INT, humidity DOUBLE, pressure DOUBLE,  temperature DOUBLE, wind_speed DOUBLE, deg DOUBLE);";
            statement.execute(sql);
            logger.info("Utworzono baze danych");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToDatabase(List<Weather> list) {
        Connection connection = new ConnectionDb().getConnection();

        try {
            Statement statement = connection.createStatement();
            System.out.println(list.get(0).getData());
            for (int i = 0; i < list.size(); i++)
            {
                String sql = "INSERT INTO weather VALUES('" + list.get(i).getData() + "','" + list.get(i).getDescription() + "'," + list.get(i).getId() + "," + list.get(i).getHumidity() + "," + list.get(i).getPressure() + "," + list.get(i).getTemp() + "," + list.get(i).getSpeed() + "," + list.get(i).getDeg() + ")";
                statement.execute(sql);
                logger.info("Dodano rekord do bazy danych");

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Weather> getFromDatabase(List<Weather> oldList){
        List<Weather> list = oldList;

        Connection connection = new ConnectionDb().getConnection();

        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < oldList.size(); i++)
            {

                String sql = "SELECT * FROM weather WHERE id="+ i + ";";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next())
                {
                    list.get(i).setData(resultSet.getString("date"));
                    list.get(i).setDescription(resultSet.getString("clouds"));
                    list.get(i).setHumidity(resultSet.getLong("humidity"));
                    list.get(i).setPressure(resultSet.getDouble("pressure"));
                    list.get(i).setTemp(resultSet.getDouble("temperature"));
                    list.get(i).setSpeed(resultSet.getDouble("wind_speed"));
                    list.get(i).setDeg(resultSet.getDouble("deg"));
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
