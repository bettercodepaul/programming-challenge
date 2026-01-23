package de.bcxp.challenge.solver;

import de.bcxp.challenge.data.DataSourceReader;
import de.bcxp.challenge.model.WeatherDay;
import java.util.Comparator;
import java.util.List;

public class WeatherSolver implements SolverService {

  private final DataSourceReader<WeatherDay> dataSource;

  public WeatherSolver(DataSourceReader<WeatherDay> dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public String solve() {
    var weatherData = loadData();
    var dayWithMinSpread = findMinTemperatureSpread(weatherData);
    return dayWithMinSpread.getDayNumber();
  }

  private List<WeatherDay> loadData() {
    return dataSource.readData();
  }

  private WeatherDay findMinTemperatureSpread(List<WeatherDay> weatherData) {
    return weatherData.stream()
        .min(Comparator.comparingDouble(WeatherDay::getTemperatureSpread))
        .orElseThrow(() -> new IllegalStateException("No weather data available"));
  }
}
