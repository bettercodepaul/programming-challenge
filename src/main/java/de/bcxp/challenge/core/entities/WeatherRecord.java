package de.bcxp.challenge.core.entities;

public class WeatherRecord {

    private String date;

    private double maxTemperature;
    
    private double minTemperature;

    public WeatherRecord() {
    }

    public WeatherRecord(String date, double maxTemperature, double minTemperature) {
        this.date = date;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getTemperatureRange() {
        return maxTemperature - minTemperature;
    }
}