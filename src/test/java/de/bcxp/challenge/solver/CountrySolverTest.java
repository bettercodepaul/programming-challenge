package de.bcxp.challenge.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.bcxp.challenge.data.DataSourceReader;
import de.bcxp.challenge.model.Country;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountrySolverTest {

  @Mock private DataSourceReader<Country> dataSource;

  private CountrySolver solver;

  @BeforeEach
  void setUp() {
    solver = new CountrySolver(dataSource);
  }

  @Test
  @DisplayName("Should find country with highest population density")
  void shouldFindCountryWithHighestPopulationDensity() {
    var countries =
        List.of(
            new Country("Germany", 83120520, 357386), // density ~232
            new Country("Malta", 516100, 316), // density ~1633 (highest)
            new Country("Sweden", 10370000, 449964) // density ~23
            );
    when(dataSource.readData()).thenReturn(countries);

    var result = solver.solve();

    assertEquals("Malta", result);
  }

  @Test
  @DisplayName("Should handle single country input")
  void shouldHandleSingleCountry() {
    var countries = List.of(new Country("Luxembourg", 633347, 2586));
    when(dataSource.readData()).thenReturn(countries);

    var result = solver.solve();

    assertEquals("Luxembourg", result);
  }

  @Test
  @DisplayName("Should handle countries with same population density")
  void shouldHandleCountriesWithSameDensity() {
    var countries =
        List.of(
            new Country("CountryA", 1000, 10), // density = 100
            new Country("CountryB", 2000, 20), // density = 100
            new Country("CountryC", 500, 5) // density = 100
            );
    when(dataSource.readData()).thenReturn(countries);

    var result = solver.solve();

    assertEquals("CountryA", result);
  }

  @Test
  @DisplayName("Should throw exception for empty data")
  void shouldThrowExceptionForEmptyData() {
    when(dataSource.readData()).thenReturn(List.of());

    var exception = assertThrows(IllegalStateException.class, () -> solver.solve());
    assertEquals("No country data available", exception.getMessage());
  }

  @Test
  @DisplayName("Should handle large populations and areas")
  void shouldHandleLargePopulations() {
    var countries =
        List.of(
            new Country("SmallIsland", 100000, 50), // density = 2000 (highest)
            new Country("BigCountry", 100000000, 500000) // density = 200
            );
    when(dataSource.readData()).thenReturn(countries);

    var result = solver.solve();

    assertEquals("SmallIsland", result);
  }
}
