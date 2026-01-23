package de.bcxp.challenge.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountryTest {

  @Test
  @DisplayName("should calculate population density correctly")
  void shouldCalculatePopulationDensity() {
    var country = new Country("Malta", 516100, 316.0);

    assertEquals(1633.23, country.getPopulationDensity(), 0.01);
  }

  @Test
  @DisplayName("should return country name")
  void shouldReturnCountryName() {
    var country = new Country("Germany", 83120520, 357386.0);

    assertEquals("Germany", country.getName());
  }

  @Test
  @DisplayName("should handle large population")
  void shouldHandleLargePopulation() {
    var country = new Country("Germany", 83120520, 357386.0);

    assertEquals(232.58, country.getPopulationDensity(), 0.1);
  }

  @Test
  @DisplayName("should handle small area")
  void shouldHandleSmallArea() {
    var country = new Country("Luxembourg", 633347, 2586.0);

    assertEquals(244.91, country.getPopulationDensity(), 0.01);
  }
}
