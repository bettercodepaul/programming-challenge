package de.bcxp.challenge.weather.utils;


import de.bcxp.challenge.weather.data.WeatherRecord;
import de.bcxp.challenge.weather.ui.WeatherUi;

import java.util.ArrayList;
import java.util.List;


public class WeatherUiMock implements WeatherUi {

    public List<WeatherRecord> shownRecords = new ArrayList<>();

    @Override
    public void showMinTempSpread(WeatherRecord weatherRecord) {
        this.shownRecords.add(weatherRecord);
    }

}
