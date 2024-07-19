package de.bcxp.challenge.core.usecases;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.ports.IWeatherRepository;

import java.util.List;

public class FindDayLowestTemperatureRangeUseCase {
    private final IWeatherRepository weatherRepository;

    public FindDayLowestTemperatureRangeUseCase(IWeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherRecord execute() {
        List<WeatherRecord> weatherRecords = weatherRepository.getAllWeatherData();
        WeatherRecord dayWithLowestRange = null;

        for (WeatherRecord record : weatherRecords) {
            if (dayWithLowestRange == null || record.getTemperatureRange() < dayWithLowestRange.getTemperatureRange()) {
                dayWithLowestRange = record;
            }
        }
        return dayWithLowestRange;
    }
}
