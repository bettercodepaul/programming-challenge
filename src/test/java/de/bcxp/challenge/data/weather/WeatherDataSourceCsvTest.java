package de.bcxp.challenge.data.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class WeatherDataSourceCsvTest {

    @Test
    public void testInvalidPathThrowsException() {

        // GIVEN an invalid path to CSV file
        Path invalidPath = Path.of("./INVALID/PATH.csv");

        // WHEN a CsvDataSource is instantiated with the wrong path
        Exception exception = Assertions.assertThrows(FileNotFoundException.class, () ->
                new WeatherDataSourceCSV(invalidPath)
        );

        // THEN an exception is thrown
        Assertions.assertEquals(FileNotFoundException.class, exception.getClass());

    }

}
