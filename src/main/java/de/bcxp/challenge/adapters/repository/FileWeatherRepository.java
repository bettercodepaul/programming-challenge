package de.bcxp.challenge.adapters.repository;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.ports.IFileReader;
import de.bcxp.challenge.ports.IWeatherRepository;

import java.util.List;

public class FileWeatherRepository implements IWeatherRepository {
    private final IFileReader<WeatherRecord> weatherFileReader;

    public FileWeatherRepository(IFileReader<WeatherRecord> weatherFileReader) {
        this.weatherFileReader = weatherFileReader;
    }

    @Override
    public List<WeatherRecord> getAllWeatherData() {
        return weatherFileReader.readData();
    }
}