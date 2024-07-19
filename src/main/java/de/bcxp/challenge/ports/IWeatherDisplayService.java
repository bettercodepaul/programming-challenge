package de.bcxp.challenge.ports;

import de.bcxp.challenge.core.entities.WeatherRecord;

import java.util.List;

public interface IWeatherDisplayService {
    void displayAllWeatherData(List<WeatherRecord> weatherRecords);

    void displayDayWithLowestTemperatureRange(WeatherRecord weatherRecord);
}