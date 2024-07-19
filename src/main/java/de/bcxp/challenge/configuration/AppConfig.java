package de.bcxp.challenge.configuration;

import de.bcxp.challenge.adapters.repository.CsvWeatherFileReader;
import de.bcxp.challenge.adapters.repository.FileWeatherRepository;
import de.bcxp.challenge.adapters.service.ConsoleWeatherDisplayService;
import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.core.usecases.FindDayLowestTemperatureRangeUseCase;
import de.bcxp.challenge.ports.IWeatherFileReader;
import de.bcxp.challenge.ports.IWeatherDisplayService;
import de.bcxp.challenge.ports.IWeatherRepository;

public class AppConfig {
    public static void main(String[] args) {

        String filePath = "de/bcxp/challenge/weather.csv"; // TODO make this a program parameter

        // Creates the File Reader and injects it on a new Weather Repository
        IWeatherFileReader fileReader = new CsvWeatherFileReader();
        IWeatherRepository weatherRepository = new FileWeatherRepository(fileReader, filePath);

        // Services
        IWeatherDisplayService weatherDisplayService = new ConsoleWeatherDisplayService();

        // Use cases
        FindDayLowestTemperatureRangeUseCase findDayLowestTemperatureRangeUseCase = new FindDayLowestTemperatureRangeUseCase(weatherRepository);

        // Execute the required use cases and display data
        WeatherRecord dayWithLowestTemperatureRange = findDayLowestTemperatureRangeUseCase.execute();
        weatherDisplayService.displayDayWithLowestTemperatureRange(dayWithLowestTemperatureRange);
    }
}