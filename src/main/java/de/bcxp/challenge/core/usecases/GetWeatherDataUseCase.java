package de.bcxp.challenge.core.usecases;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.ports.IWeatherRepository;

import java.util.List;

public class GetWeatherDataUseCase {
    private final IWeatherRepository weatherRepository;

    public GetWeatherDataUseCase(IWeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<WeatherRecord> execute() {
        return weatherRepository.getAllWeatherData();
    }
}
