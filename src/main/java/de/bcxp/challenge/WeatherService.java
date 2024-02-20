package de.bcxp.challenge;

import java.util.List;

public class WeatherService implements IWeatherService {

    IWeatherRepository repository;

    public WeatherService() {
        this.repository = new WeatherRepository();
        this.repository.createDB();
    }

    @Override
    public String getDayWithSmallestTempSpread() {
        return repository.selectDayWithSmallestTemperatureSpread();
    }

    @Override
    public void extractDataToDB(List<String[]> data) {

        int day;
        float mxt;
        float mnt;
        float tempSpread;

        // read data line by line
        for(String[] nextRecord : data) {
            day = Integer.parseInt(nextRecord[0]);
            mxt = Float.parseFloat(nextRecord[1]);
            mnt = Float.parseFloat(nextRecord[2]);
            tempSpread = mxt - mnt;
            repository.insertRow(day, mxt, mnt, tempSpread);
        }
    }

    @Override
    public void closeConnection() {
        repository.closeConnection();
    }
}
