package de.bcxp.challenge;

public interface ICountriesRepository extends IRepository {
    void insertRow(String name, int population, float area, float density);
    String selectCountryWithHighestPopulationDensity();
}
