package de.bcxp.challenge.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountryRowMapperTest {

  private CountryRowMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new CountryRowMapper();
  }

  @Test
  @DisplayName("Should map a valid row correctly")
  void shouldMapValidRow() {
    var columns = new String[] {"Malta", "Valletta", "2004", "516100", "316"};

    var result = mapper.map(columns);

    assertEquals("Malta", result.name());
    assertEquals(516100L, result.population());
    assertEquals(316.0, result.area());
  }

  @Test
  @DisplayName("Should handle specific number format")
  void shouldHandleSpecificNumberFormat() {
    // Croatia has format: "4.036.355,00"
    var columns = new String[] {"Croatia", "Zagreb", "2013", "4.036.355,00", "56594"};

    var result = mapper.map(columns);

    assertEquals("Croatia", result.name());
    assertEquals(4036355L, result.population());
  }

  @Test
  @DisplayName("Should handle data with leading and trailing spaces")
  void shouldHandleDataWithSpaces() {
    var columns = new String[] {" Malta ", "Valletta", "2004", " 516100 ", " 316 "};

    var result = mapper.map(columns);

    assertEquals("Malta", result.name());
    assertEquals(516100L, result.population());
    assertEquals(316.0, result.area());
  }

  @Test
  @DisplayName("Should handle formats with spaces correctly")
  void shouldHandleFormatsWithSpaces() {
    var columns = new String[] {" Croatia ", "Zagreb", "2013", " 4.036.355,00 ", "56594"};

    var result = mapper.map(columns);

    assertEquals("Croatia", result.name());
    assertEquals(4036355L, result.population());
  }

  @Test
  @DisplayName("Should handle large population correctly")
  void shouldHandleLargePopulation() {
    var columns = new String[] {"Germany", "Berlin", "Founder", "83120520", "357386"};

    var result = mapper.map(columns);

    assertEquals(83120520L, result.population());
  }

  @Test
  @DisplayName("Should throw exception for invalid population")
  void shouldThrowExceptionForInvalidPopulation() {
    var columns = new String[] {"Invalid", "Capital", "2004", "not-a-number", "316"};

    assertThrows(NumberFormatException.class, () -> mapper.map(columns));
  }

  @Test
  @DisplayName("Should throw exception for invalid area")
  void shouldThrowExceptionForMissingColumns() {
    var columns = new String[] {"Malta", "Valletta"};

    assertThrows(ArrayIndexOutOfBoundsException.class, () -> mapper.map(columns));
  }
}
