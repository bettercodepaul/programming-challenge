package de.bcxp.challenge;

import de.bcxp.challenge.adapters.csv.CsvWeatherFileReader;
import de.bcxp.challenge.configuration.AppConfig;
import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.exceptions.CsvFileFormatException;
import de.bcxp.challenge.exceptions.FileNotFoundException;
import de.bcxp.challenge.ports.IFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvWeatherFileReaderTest {
    @Test
    public void testReadWeatherData_NoFile() {
        InputStream inputStream =
                AppConfig.class.getClassLoader().getResourceAsStream("no_weather_file.csv");
        IFileReader<WeatherRecord> weatherReader = new CsvWeatherFileReader.Builder(inputStream).build();

        Executable executable = weatherReader::readData;

        FileNotFoundException exception = assertThrows(FileNotFoundException.class, executable);
        assertEquals("The file is empty or not found", exception.getMessage());
    }

    @Test
    public void testReadWeatherData_EmptyFile() {
        InputStream inputStream =
                AppConfig.class.getClassLoader().getResourceAsStream("de/bcxp/challenge/empty_weather.csv");
        IFileReader<WeatherRecord> weatherReader = new CsvWeatherFileReader.Builder(inputStream).build();

        Executable executable = weatherReader::readData;

        FileNotFoundException exception = assertThrows(FileNotFoundException.class, executable);
        assertEquals("The file is empty or not found", exception.getMessage());
    }

    @Test
    public void testReadWeatherData_InvalidLine() {
        InputStream inputStream =
                AppConfig.class.getClassLoader().getResourceAsStream("de/bcxp/challenge/invalid_line_weather.csv");
        IFileReader<WeatherRecord> weatherReader = new CsvWeatherFileReader.Builder(inputStream).build();

        Executable executable = weatherReader::readData;

        CsvFileFormatException exception = assertThrows(CsvFileFormatException.class, executable);
        assertEquals("Error reading CSV file", exception.getMessage());
    }

    @Test
    public void testReadWeatherData_ValidFile() {
        InputStream inputStream =
                AppConfig.class.getClassLoader().getResourceAsStream("de/bcxp/challenge/test_weather.csv");
        IFileReader<WeatherRecord> weatherReader = new CsvWeatherFileReader.Builder(inputStream).build();

        List<WeatherRecord> weatherRecords = weatherReader.readData();

        // Assertions
        assertEquals(3, weatherRecords.size());
        assertEquals("1", weatherRecords.get(0).getDate());
        assertEquals(88.0, weatherRecords.get(0).getMaxTemperature(), 0.01);
        assertEquals(59.0, weatherRecords.get(0).getMinTemperature(), 0.01);
    }
}
