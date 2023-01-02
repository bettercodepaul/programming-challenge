package de.bcxp.challenge.weather.utils;

import de.bcxp.challenge.weather.DataSource;
import de.bcxp.challenge.weather.WeatherDataSourceCSV;
import de.bcxp.challenge.weather.WeatherRecord;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class WeatherDataSourceFactory {

    public static DataSource<WeatherRecord> newInstance(WeatherDataSourceEnum dataSourceEnum) throws FileNotFoundException {

        if (dataSourceEnum == WeatherDataSourceEnum.MOCK) {
            return newMock();
        } else if (dataSourceEnum == WeatherDataSourceEnum.CSV) {
            return newCsv();
        }

        return null;
    }

    private static DataSource<WeatherRecord> newMock() {
        Iterable<WeatherRecord> records = WeatherDataSourceMock.MOCK_RECORDS;
        return new WeatherDataSourceMock(records);
    }

    private static DataSource<WeatherRecord> newCsv() throws FileNotFoundException {
        Path csvPath = Path.of("./src/test/resources/challenge/weather.csv");
        return new WeatherDataSourceCSV(csvPath);
    }

}
