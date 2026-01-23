package de.bcxp.challenge.solver;

import de.bcxp.challenge.data.DataSourceReader;
import de.bcxp.challenge.model.Country;
import java.util.Comparator;
import java.util.List;

public class CountrySolver implements SolverService {

  private final DataSourceReader<Country> dataSource;

  public CountrySolver(DataSourceReader<Country> dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public String solve() {
    var countries = loadData();
    var countryWithMaxDensity = findMaxPopulationDensity(countries);
    return countryWithMaxDensity.getName();
  }

  private List<Country> loadData() {
    return dataSource.readData();
  }

  private Country findMaxPopulationDensity(List<Country> countries) {
    return countries.stream()
        .max(Comparator.comparingDouble(Country::getPopulationDensity))
        .orElseThrow(() -> new IllegalStateException("No country data available"));
  }
}
