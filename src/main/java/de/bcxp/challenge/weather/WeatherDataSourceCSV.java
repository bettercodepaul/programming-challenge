package de.bcxp.challenge.weather;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

public class WeatherDataSourceCSV implements DataSource<WeatherRecord> {

    private final FileReader csvFileReader;

    public WeatherDataSourceCSV(Path csvPath) throws FileNotFoundException {
        // FileNotFoundException is an exception type that is clearly specific to a file-based implementation of the
        // DataSource interface. Usually we should avoid throwing implementation-specific exceptions that are not
        // defined by the interface. But for the constructor it's okay under the assumption that the only place that
        // will use the implementation-specific constructor is the main function in which the different dependencies
        // are plugged together.
        this.csvFileReader = new FileReader(csvPath.toString());
    }

    @Override
    public Iterable<WeatherRecord> getData() {

        CsvToBeanBuilder<WeatherRecord> beanBuilder = new CsvToBeanBuilder<>(this.csvFileReader);

        return beanBuilder
                .withType(WeatherRecord.class)
                .build()
                .parse();
    }

}
