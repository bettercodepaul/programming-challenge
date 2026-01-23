package de.bcxp.challenge;

import de.bcxp.challenge.data.CountryRowMapper;
import de.bcxp.challenge.data.CsvDataSource;
import de.bcxp.challenge.data.WeatherRowMapper;
import de.bcxp.challenge.model.Country;
import de.bcxp.challenge.model.WeatherDay;
import de.bcxp.challenge.solver.CountrySolver;
import de.bcxp.challenge.solver.SolverService;
import de.bcxp.challenge.solver.WeatherSolver;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as
 * baseline for your software design. Read: create your own classes and packages as appropriate.
 */
public final class App {

  private static final String WEATHER_CSV_PATH = "/de/bcxp/challenge/weather.csv";
  private static final String COUNTRIES_CSV_PATH = "/de/bcxp/challenge/countries.csv";

  /**
   * This is the main entry method of your program.
   *
   * @param args The CLI arguments passed
   */
  public static void main(String... args) {
    var weatherSolver = createWeatherSolver();
    var countrySolver = createCountrySolver();

    var dayWithSmallestTempSpread = weatherSolver.solve();
    System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

    var countryWithHighestPopulationDensity = countrySolver.solve();
    System.out.printf(
        "Country with highest population density: %s%n", countryWithHighestPopulationDensity);
  }

  private static SolverService createWeatherSolver() {
    var dataSource = new CsvDataSource<WeatherDay>(WEATHER_CSV_PATH, ',', new WeatherRowMapper());
    return new WeatherSolver(dataSource);
  }

  private static SolverService createCountrySolver() {
    var dataSource = new CsvDataSource<Country>(COUNTRIES_CSV_PATH, ';', new CountryRowMapper());
    return new CountrySolver(dataSource);
  }
}
