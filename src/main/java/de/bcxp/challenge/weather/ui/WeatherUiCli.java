package de.bcxp.challenge.weather.ui;


import de.bcxp.challenge.weather.data.WeatherRecord;

public class WeatherUiCli implements WeatherUi {

    @Override
    public void showMinTempSpread(WeatherRecord minSpreadRecord) {
        String minSpreadDay = minSpreadRecord != null ? String.valueOf(minSpreadRecord.getDay()) : "[N/A]";
        System.out.printf("Day with smallest temperature spread: %s%n", minSpreadDay);
    }

}
