package de.bcxp.challenge.data.weather;

import de.bcxp.challenge.data.DataSource;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

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

        ArrayList<WeatherRecord> records = new ArrayList<>();

        records.add(new WeatherRecord(1, 88, 59));
        records.add(new WeatherRecord(2, 79, 63));
        records.add(new WeatherRecord(3, 77, 55));

        return new WeatherDataSourceMock(records);
    }

    private static DataSource<WeatherRecord> newCsv() throws FileNotFoundException {
        Path csvPath = Path.of("./src/test/resources/challenge/weather.csv");
        return new WeatherDataSourceCSV(csvPath);
    }

}
