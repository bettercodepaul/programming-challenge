package de.bcxp.challenge.data;

import de.bcxp.challenge.model.WeatherDay;

public class WeatherRowMapper implements RowMapper<WeatherDay> {

  private static final int DAY_INDEX = 0;
  private static final int MAX_TEMP_INDEX = 1;
  private static final int MIN_TEMP_INDEX = 2;

  @Override
  public WeatherDay map(String[] columns) {
    var day = Integer.parseInt(columns[DAY_INDEX].trim());
    var maxTemp = Double.parseDouble(columns[MAX_TEMP_INDEX].trim());
    var minTemp = Double.parseDouble(columns[MIN_TEMP_INDEX].trim());

    return new WeatherDay(day, maxTemp, minTemp);
  }
}
