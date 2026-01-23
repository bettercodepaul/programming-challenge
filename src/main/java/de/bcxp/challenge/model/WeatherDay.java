package de.bcxp.challenge.model;

public record WeatherDay(int day, double maxTemp, double minTemp) {

  public double getTemperatureSpread() {
    return maxTemp - minTemp;
  }

  public String getDayNumber() {
    return String.valueOf(day);
  }
}
