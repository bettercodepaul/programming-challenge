package de.bcxp.challenge.core.entities;

public class WeatherRecord {
    private final String date;
    private final double maxTemperature;
    private final double minTemperature;

    public WeatherRecord(String date, double maxTemperature, double minTemperature) {
        this.date = date;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public String getDate() {
        return date;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getTemperatureRange() {
        return maxTemperature - minTemperature;
    }
}