package de.bcxp.challenge.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.bcxp.challenge.data.DataSourceReader;
import de.bcxp.challenge.model.WeatherDay;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeatherSolverTest {

  @Mock private DataSourceReader<WeatherDay> dataSource;

  private WeatherSolver solver;

  @BeforeEach
  void setUp() {
    solver = new WeatherSolver(dataSource);
  }

  @Test
  @DisplayName("Should find day with smallest temperature spread")
  void shouldFindDayWithSmallestTemperatureSpread() {
    var weatherData =
        List.of(
            new WeatherDay(1, 88, 59), // spread = 29
            new WeatherDay(14, 61, 59), // spread = 2 (smallest)
            new WeatherDay(9, 86, 32) // spread = 54
            );
    when(dataSource.readData()).thenReturn(weatherData);

    var result = solver.solve();

    assertEquals("14", result);
  }

  @Test
  @DisplayName("Should handle single day input")
  void shouldHandleSingleDay() {
    var weatherData = List.of(new WeatherDay(5, 70, 60));
    when(dataSource.readData()).thenReturn(weatherData);

    var result = solver.solve();

    assertEquals("5", result);
  }

  @Test
  @DisplayName("Should handle multiple days with same temperature spread")
  void shouldHandleMultipleDaysWithSameSpread() {
    var weatherData =
        List.of(
            new WeatherDay(1, 60, 50), // spread = 10
            new WeatherDay(2, 70, 60), // spread = 10
            new WeatherDay(3, 80, 70) // spread = 10
            );
    when(dataSource.readData()).thenReturn(weatherData);

    var result = solver.solve();

    assertEquals("1", result);
  }

  @Test
  @DisplayName("Should throw exception for empty data")
  void shouldThrowExceptionForEmptyData() {
    when(dataSource.readData()).thenReturn(List.of());

    var exception = assertThrows(IllegalStateException.class, () -> solver.solve());
    assertEquals("No weather data available", exception.getMessage());
  }

  @Test
  @DisplayName("Should handle negative temperatures")
  void shouldHandleNegativeTemperatures() {
    var weatherData =
        List.of(
            new WeatherDay(1, -5, -10), // spread = 5
            new WeatherDay(2, -2, -5), // spread = 3 (smallest)
            new WeatherDay(3, 0, -10) // spread = 10
            );
    when(dataSource.readData()).thenReturn(weatherData);

    var result = solver.solve();

    assertEquals("2", result);
  }
}
