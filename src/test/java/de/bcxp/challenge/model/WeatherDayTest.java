package de.bcxp.challenge.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeatherDayTest {

  @Test
  @DisplayName("should calculate temperature spread correctly")
  void shouldCalculateTemperatureSpread() {
    var weatherDay = new WeatherDay(1, 88.0, 59.0);

    assertEquals(29.0, weatherDay.getTemperatureSpread());
  }

  @Test
  @DisplayName("should return day number as string")
  void shouldReturnDayNumberAsString() {
    var weatherDay = new WeatherDay(14, 61.0, 59.0);

    assertEquals("14", weatherDay.getDayNumber());
  }

  @Test
  @DisplayName("should handle zero spread")
  void shouldHandleZeroSpread() {
    var weatherDay = new WeatherDay(1, 50.0, 50.0);

    assertEquals(0.0, weatherDay.getTemperatureSpread());
  }

  @Test
  @DisplayName("should handle negative temperatures")
  void shouldHandleNegativeTemperatures() {
    var weatherDay = new WeatherDay(1, -5.0, -20.0);

    assertEquals(15.0, weatherDay.getTemperatureSpread());
  }
}
