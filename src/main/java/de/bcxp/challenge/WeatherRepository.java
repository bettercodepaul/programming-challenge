package de.bcxp.challenge;

import java.sql.*;

public class WeatherRepository implements IWeatherRepository {

    private final String jdbcURL = "jdbc:h2:mem:test";

    private Connection connection;

    @Override
    public void createDB() {
        try {
            connection = DriverManager.getConnection(jdbcURL);
            System.out.println("Connected to H2 in-memory database.");

            String sql = "CREATE TABLE Weather (`Day` int primary key, MxT decimal, MnT decimal, TempSpread decimal)";

            Statement statement = connection.createStatement();
            statement.execute(sql);

            System.out.println("Created table Weather.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void insertRow(int day, float mxt, float mnt, float tempSpread) {
        String sql = String.format(""
                + "INSERT INTO Weather (`Day`, MxT, MnT, TempSpread) "
                + "values (%s, %s, %s, %s)",
                day, mxt, mnt, tempSpread);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String selectDayWithSmallestTemperatureSpread() {
        String sql = ""
                + "SELECT `Day`, TempSpread FROM Weather "
                + "WHERE TempSpread = "
                + "(SELECT MIN(TempSpread) FROM Weather)";

        try {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                return result.getString("Day");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
