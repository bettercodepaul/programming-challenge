package de.bcxp.challenge.data.weather;


import de.bcxp.challenge.data.DataSource;

public class WeatherDataSourceMock implements DataSource<WeatherRecord> {

    private final Iterable<WeatherRecord> records;

    public WeatherDataSourceMock(Iterable<WeatherRecord> mockRecords) {
        this.records = mockRecords;
    }

    @Override
    public Iterable<WeatherRecord> getData() {
        return this.records;
    }

}
