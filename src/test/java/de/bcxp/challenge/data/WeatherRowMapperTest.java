package de.bcxp.challenge.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeatherRowMapperTest {

  private WeatherRowMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new WeatherRowMapper();
  }

  @Test
  @DisplayName("Should map valid row correctly")
  void shouldMapValidRow() {
    var columns = new String[] {"14", "61", "59", "other", "columns"};

    var result = mapper.map(columns);

    assertEquals(14, result.day());
    assertEquals(61.0, result.maxTemp());
    assertEquals(59.0, result.minTemp());
  }

  @Test
  @DisplayName("Should handle decimal temperatures")
  void shouldHandleDecimalTemperatures() {
    var columns = new String[] {"1", "88.5", "59.3"};

    var result = mapper.map(columns);

    assertEquals(88.5, result.maxTemp());
    assertEquals(59.3, result.minTemp());
  }

  @Test
  @DisplayName("Should throw exception for invalid day")
  void shouldThrowExceptionForInvalidDay() {
    var columns = new String[] {"invalid", "61", "59"};

    assertThrows(NumberFormatException.class, () -> mapper.map(columns));
  }

  @Test
  @DisplayName("Should throw exception for invalid temperature")
  void shouldThrowExceptionForInvalidTemperature() {
    var columns = new String[] {"1", "not-a-number", "59"};

    assertThrows(NumberFormatException.class, () -> mapper.map(columns));
  }

  @Test
  @DisplayName("Should throw exception for missing columns")
  void shouldThrowExceptionForMissingColumns() {
    var columns = new String[] {"1"};

    assertThrows(ArrayIndexOutOfBoundsException.class, () -> mapper.map(columns));
  }
}
