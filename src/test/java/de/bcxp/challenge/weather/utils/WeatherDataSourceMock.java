package de.bcxp.challenge.weather.utils;


import de.bcxp.challenge.weather.DataSource;
import de.bcxp.challenge.weather.WeatherRecord;

import java.util.Arrays;
import java.util.List;

public class WeatherDataSourceMock implements DataSource<WeatherRecord> {

    public static final List<WeatherRecord> MOCK_RECORDS = Arrays.asList(
            new WeatherRecord(1, 88, 59),
            new WeatherRecord(2, 79, 63),
            new WeatherRecord(3, 77, 55)
    );

    private final Iterable<WeatherRecord> records;

    public WeatherDataSourceMock(Iterable<WeatherRecord> mockRecords) {
        this.records = mockRecords;
    }

    @Override
    public Iterable<WeatherRecord> getData() {
        return this.records;
    }

}
