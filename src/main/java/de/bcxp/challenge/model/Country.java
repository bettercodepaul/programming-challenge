package de.bcxp.challenge.model;

public record Country(String name, long population, double area) {

  public double getPopulationDensity() {
    return population / area;
  }

  public String getName() {
    return name;
  }
}
