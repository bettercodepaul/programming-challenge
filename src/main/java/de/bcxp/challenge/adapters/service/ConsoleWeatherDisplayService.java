package de.bcxp.challenge.adapters.service;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.ports.IWeatherDisplayService;

import java.util.List;

public class ConsoleWeatherDisplayService implements IWeatherDisplayService {
    @Override
    public void displayAllWeatherData(List<WeatherRecord> weatherRecords) {
        for (WeatherRecord record : weatherRecords) {
            System.out.println("Date: " + record.getDate() + ", Max Temperature: " +
                    record.getMaxTemperature() + ", Min Temperature: " + record.getMinTemperature());
        }
    }

    @Override
    public void displayDayWithLowestTemperatureRange(WeatherRecord weatherRecord) {
        System.out.println("Day with the lowest temperature range: " + weatherRecord.getDate() +
                " with a range of " + weatherRecord.getTemperatureRange() + " degrees.");
    }

}