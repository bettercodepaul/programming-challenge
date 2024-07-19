package de.bcxp.challenge;

import de.bcxp.challenge.adapters.repository.CsvWeatherFileReader;
import de.bcxp.challenge.core.entities.WeatherRecord;

import java.util.List;

import de.bcxp.challenge.exceptions.FileFormatException;
import de.bcxp.challenge.exceptions.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvWeatherFileReaderTest {
    private CsvWeatherFileReader csvWeatherFileReader;

    @BeforeEach
    public void setUp() {
        csvWeatherFileReader = new CsvWeatherFileReader();
    }

    @Test
    public void testReadWeatherData_EmptyFile() {
        String filePath = "de/bcxp/challenge/empty_weather.csv";

        Executable executable = () -> csvWeatherFileReader.readWeatherData(filePath);

        FileNotFoundException exception = assertThrows(FileNotFoundException.class, executable);
        assertEquals("The file is empty or not found: " + filePath, exception.getMessage());
    }

    @Test
    public void testReadWeatherData_InvalidHeader() {
        String filePath = "de/bcxp/challenge/invalid_header_weather.csv";

        Executable executable = () -> csvWeatherFileReader.readWeatherData(filePath);

        FileFormatException exception = assertThrows(FileFormatException.class, executable);
        assertEquals("Invalid CSV file format. Expected headers: Day,MxT,MnT", exception.getMessage());
    }

    @Test
    public void testReadWeatherData_InvalidLine() throws Exception {
        String filePath = "de/bcxp/challenge/invalid_line_weather.csv";

        List<WeatherRecord> weatherRecords = csvWeatherFileReader.readWeatherData(filePath);

        assertEquals(1, weatherRecords.size());
        assertEquals("3", weatherRecords.get(0).getDate());
        assertEquals(88.0, weatherRecords.get(0).getMaxTemperature(), 0.01);
        assertEquals(75.0, weatherRecords.get(0).getMinTemperature(), 0.01);
    }

    @Test
    public void testReadWeatherData_ValidFile() throws Exception {
        String filePath = "de/bcxp/challenge/test_weather.csv"; // Ensure this file has valid data

        List<WeatherRecord> weatherRecords = csvWeatherFileReader.readWeatherData(filePath);

        // Assertions
        assertEquals(3, weatherRecords.size());
        assertEquals("1", weatherRecords.get(0).getDate());
        assertEquals(88.0, weatherRecords.get(0).getMaxTemperature(), 0.01);
        assertEquals(59.0, weatherRecords.get(0).getMinTemperature(), 0.01);
    }
}
