package de.bcxp.challenge;

public interface ICountriesRepository {
    public void createDB();
    public void closeConnection();
    public void insertRow(String name, int population, float area, float density);
    public String selectCountryWithHighestPopulationDensity();
}
