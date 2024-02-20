package de.bcxp.challenge;

import java.sql.*;

public class CountriesRepository implements ICountriesRepository {

    private final String jdbcURL = "jdbc:h2:mem:test";

    private Connection connection;

    @Override
    public void createDB() {
        try {
            connection = DriverManager.getConnection(jdbcURL);
            System.out.println("Connected to H2 in-memory database.");

            String sql = "CREATE TABLE Countries (Name varchar(50) primary key, Population int, Area decimal, Density decimal)";

            Statement statement = connection.createStatement();
            statement.execute(sql);

            System.out.println("Created table Countries.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void insertRow(String name, int population, float area, float density) {
        String sql = String.format(""
                        + "INSERT INTO Countries (Name, Population, Area, Density) "
                        + "values ('%s', %s, %s, %s)",
                name, population, area, density);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String selectCountryWithHighestPopulationDensity() {
        String sql = ""
                + "SELECT Name, Density FROM Countries "
                + "WHERE Density = "
                + "(SELECT MAX(Density) FROM Countries)";

        try {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                return result.getString("Name");
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
