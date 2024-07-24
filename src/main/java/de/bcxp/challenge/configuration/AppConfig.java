package de.bcxp.challenge.configuration;

import de.bcxp.challenge.adapters.csv.CsvCountryFileReader;
import de.bcxp.challenge.adapters.csv.CsvWeatherFileReader;
import de.bcxp.challenge.adapters.repository.FileCountryRepository;
import de.bcxp.challenge.adapters.repository.FileWeatherRepository;
import de.bcxp.challenge.adapters.service.ConsoleCountryDisplayService;
import de.bcxp.challenge.adapters.service.ConsoleWeatherDisplayService;
import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.core.usecases.FindCountryHighestDensityUseCase;
import de.bcxp.challenge.core.usecases.FindDayLowestTemperatureRangeUseCase;
import de.bcxp.challenge.core.usecases.GetCountryDataUseCase;
import de.bcxp.challenge.ports.*;

import java.io.InputStream;
import java.util.Locale;

public class AppConfig {
    public static void main(String[] args) {

        // Creates files input streams
        InputStream weatherInputStream =
                AppConfig.class.getClassLoader().getResourceAsStream("de/bcxp/challenge/weather.csv");
        InputStream countryInputStream =
                AppConfig.class.getClassLoader().getResourceAsStream("de/bcxp/challenge/countries.csv");

        // Creates file readers using common file interfaces and injects file streams
        IFileReader<WeatherRecord> weatherReader = new CsvWeatherFileReader.Builder(weatherInputStream).build();
        IFileReader<CountryRecord> countryReader =
                new CsvCountryFileReader.Builder(countryInputStream).withLocale(Locale.GERMANY)
                        .withSeparator(';')
                        .build(); // Use de-DE locale for parsing file

        // Creates repositories and injects file readers
        IWeatherRepository weatherRepository = new FileWeatherRepository(weatherReader);
        ICountryRepository countryRepository = new FileCountryRepository(countryReader);

        // Services
        IWeatherDisplayService weatherDisplayService = new ConsoleWeatherDisplayService();
        ICountryStatisticsService countryStatisticsService = new ConsoleCountryDisplayService();

        // Use cases
        FindDayLowestTemperatureRangeUseCase findDayLowestTemperatureRangeUseCase =
                new FindDayLowestTemperatureRangeUseCase(weatherRepository);
        GetCountryDataUseCase getCountryDataUseCase = new GetCountryDataUseCase(countryRepository);
        FindCountryHighestDensityUseCase findCountryHighestDensityUseCase = new FindCountryHighestDensityUseCase();

        // Execute the required use cases and display data

        WeatherRecord dayWithLowestTemperatureRange = findDayLowestTemperatureRangeUseCase.execute();
        weatherDisplayService.displayDayWithLowestTemperatureRange(dayWithLowestTemperatureRange);

        CountryRecord countryWithHighestPopulation =
                findCountryHighestDensityUseCase.execute(getCountryDataUseCase.execute());
        countryStatisticsService.displayCountryWithHighestDensity(countryWithHighestPopulation);
    }
}