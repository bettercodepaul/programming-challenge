package de.bcxp.challenge.adapters.repository;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.ports.IWeatherFileReader;
import de.bcxp.challenge.ports.IWeatherRepository;

import java.util.List;

public class FileWeatherRepository implements IWeatherRepository {
    private final IWeatherFileReader weatherFileReader;
    private final String filePath;

    public FileWeatherRepository(IWeatherFileReader weatherFileReader, String filePath) {
        this.weatherFileReader = weatherFileReader;
        this.filePath = filePath;
    }

    @Override
    public List<WeatherRecord> getAllWeatherData() {
        return weatherFileReader.readWeatherData(filePath);
    }
}