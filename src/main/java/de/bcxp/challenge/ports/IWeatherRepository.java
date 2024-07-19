package de.bcxp.challenge.ports;

import de.bcxp.challenge.core.entities.WeatherRecord;

import java.util.List;

public interface IWeatherRepository {
    List<WeatherRecord> getAllWeatherData();
}