package de.bcxp.challenge;

import java.util.List;

public interface IWeatherService {

    void extractDataToDB(List<String[]> data);
    String getDayWithSmallestTempSpread();
    public void closeConnection();

}
