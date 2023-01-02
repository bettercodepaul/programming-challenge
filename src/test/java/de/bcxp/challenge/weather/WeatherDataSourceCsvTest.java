package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class WeatherDataSourceCsvTest {

    @Test
    public void testInvalidPathThrowsException() {

        // GIVEN an invalid path to CSV file
        Path invalidPath = Path.of("./INVALID/PATH.csv");

        // WHEN a CSV data source is instantiated with that path
        // THEN a FileNotFoundException is thrown
        Assertions.assertThrows(FileNotFoundException.class, () ->
                new WeatherDataSourceCSV(invalidPath)
        );

    }


    @Test
    public void testEmptyCsvResultsInRuntimeException() throws FileNotFoundException {

        // GIVEN a data source for an empty CSV file
        Path csvPath = Path.of("./src/test/resources/challenge/weather_empty.csv");
        DataSource<WeatherRecord> dataSource = new WeatherDataSourceCSV(csvPath);

        // WHEN data is requested
        // THEN an RuntimeException is thrown
        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                dataSource.getData()
        );

        // AND the error message is descriptive
        assert(exception.getMessage().contains("Error capturing CSV header"));
    }


    @Test
    public void testUnexpectedFloatValuesLeadToRuntimeException() throws FileNotFoundException {

        // GIVEN a data source pointing to a CSV with float values instead of the expected integers
        Path csvPath = Path.of("./src/test/resources/challenge/weather_with_float.csv");
        DataSource<WeatherRecord> dataSource = new WeatherDataSourceCSV(csvPath);

        // WHEN data is requested
        // THEN an RuntimeException is thrown
        Assertions.assertThrows(RuntimeException.class, () ->
                dataSource.getData()
        );
    }

}
