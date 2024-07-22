package de.bcxp.challenge.configuration;

import de.bcxp.challenge.adapters.csv.CsvCountryFileReader;
import de.bcxp.challenge.adapters.csv.CsvWeatherFileReader;
import de.bcxp.challenge.adapters.repository.*;
import de.bcxp.challenge.adapters.service.ConsoleCountryDisplayService;
import de.bcxp.challenge.adapters.service.ConsoleWeatherDisplayService;
import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.core.usecases.FindCountryHighestDensityUseCase;
import de.bcxp.challenge.core.usecases.FindDayLowestTemperatureRangeUseCase;
import de.bcxp.challenge.core.usecases.GetCountryDataUseCase;
import de.bcxp.challenge.ports.*;
import de.bcxp.challenge.adapters.csv.CsvParser;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class AppConfig {
    public static void main(String[] args) {

        CsvParser weatherCsvParser = new CsvParser.CsvParserBuilder()
                .build();

        // Creates File Readers and injects them on Repositories objects
        IFileReader<WeatherRecord> weatherFileReader = new CsvWeatherFileReader("de/bcxp/challenge/weather.csv", weatherCsvParser);
        IWeatherRepository weatherRepository = new FileWeatherRepository(weatherFileReader);


        CsvParser countryCsvParser = new CsvParser.CsvParserBuilder()
                .withDelimiter(";")
                .withCharset(StandardCharsets.UTF_8)
                .withLocale(Locale.GERMANY)
                .build();
        IFileReader<CountryRecord> countryFileReader = new CsvCountryFileReader("de/bcxp/challenge/countries.csv", countryCsvParser);
        ICountryRepository countryRepository = new FileCountryRepository(countryFileReader);

        // Services
        IWeatherDisplayService weatherDisplayService = new ConsoleWeatherDisplayService();
        ICountryStatisticsService countryStatisticsService = new ConsoleCountryDisplayService();

        // Use cases
        FindDayLowestTemperatureRangeUseCase findDayLowestTemperatureRangeUseCase = new FindDayLowestTemperatureRangeUseCase(weatherRepository);

        // Execute the required use cases and display data
        WeatherRecord dayWithLowestTemperatureRange = findDayLowestTemperatureRangeUseCase.execute();
        weatherDisplayService.displayDayWithLowestTemperatureRange(dayWithLowestTemperatureRange);

        // Country data configuration
        GetCountryDataUseCase getCountryDataUseCase = new GetCountryDataUseCase(countryRepository);
        FindCountryHighestDensityUseCase findCountryHighestDensityUseCase = new FindCountryHighestDensityUseCase();
        countryStatisticsService.displayCountryWithHighestDensity(findCountryHighestDensityUseCase.execute(getCountryDataUseCase.execute()));
    }
}