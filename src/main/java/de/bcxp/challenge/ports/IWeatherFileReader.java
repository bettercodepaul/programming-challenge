package de.bcxp.challenge.ports;

import de.bcxp.challenge.core.entities.WeatherRecord;

import java.util.List;

public interface IWeatherFileReader {
    List<WeatherRecord> readWeatherData(String filePath);
}
