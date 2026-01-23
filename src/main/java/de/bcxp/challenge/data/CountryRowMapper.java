package de.bcxp.challenge.data;

import de.bcxp.challenge.model.Country;

public class CountryRowMapper implements RowMapper<Country> {

  private static final int NAME_INDEX = 0;
  private static final int POPULATION_INDEX = 3;
  private static final int AREA_INDEX = 4;

  @Override
  public Country map(String[] columns) {
    var name = columns[NAME_INDEX];
    var population = parseFormattedNumber(columns[POPULATION_INDEX]);
    var area = Double.parseDouble(columns[AREA_INDEX]);

    return new Country(name, population, area);
  }

  private long parseFormattedNumber(String value) {
    // Handle localized format in csv: "4.036.355,00" -> 4036355
    var normalized = value.replace(".", "").replace(",", ".");

    return (long) Double.parseDouble(normalized);
  }
}
