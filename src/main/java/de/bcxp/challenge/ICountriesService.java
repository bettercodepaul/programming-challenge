package de.bcxp.challenge;

import java.util.List;

public interface ICountriesService {

    void extractDataToDB(List<String[]> data);
    String getCountryWithHighestPopulationDensity();
    public void closeConnection();
}
