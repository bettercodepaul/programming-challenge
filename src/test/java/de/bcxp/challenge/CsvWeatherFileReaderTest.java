package de.bcxp.challenge;

import de.bcxp.challenge.adapters.csv.CsvWeatherFileReader;
import de.bcxp.challenge.core.entities.WeatherRecord;

import java.util.List;

import de.bcxp.challenge.exceptions.FileFormatException;
import de.bcxp.challenge.exceptions.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import de.bcxp.challenge.adapters.csv.CsvParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvWeatherFileReaderTest {
    private CsvWeatherFileReader csvWeatherFileReader;
    private CsvParser csvParser;

    @BeforeEach
    public void setUp() {
        csvParser = new CsvParser.CsvParserBuilder().build();
    }

    @Test
    public void testReadWeatherData_EmptyFile() {
        String filePath = "de/bcxp/challenge/empty_weather.csv";
        csvWeatherFileReader = new CsvWeatherFileReader(filePath, csvParser);

        Executable executable = () -> csvWeatherFileReader.readData();

        FileNotFoundException exception = assertThrows(FileNotFoundException.class, executable);
        assertEquals("The file is empty or not found: " + filePath, exception.getMessage());
    }

    @Test
    public void testReadWeatherData_InvalidHeader() {
        String filePath = "de/bcxp/challenge/invalid_header_weather.csv";
        csvWeatherFileReader = new CsvWeatherFileReader(filePath, csvParser);

        Executable executable = () -> csvWeatherFileReader.readData();

        FileFormatException exception = assertThrows(FileFormatException.class, executable);
        assertEquals("Invalid CSV file format. Expected headers: Day,MxT,MnT", exception.getMessage());
    }

    @Test
    public void testReadWeatherData_InvalidLine() {
        String filePath = "de/bcxp/challenge/invalid_line_weather.csv";
        csvWeatherFileReader = new CsvWeatherFileReader(filePath, csvParser);

        List<WeatherRecord> weatherRecords = csvWeatherFileReader.readData();

        assertEquals(1, weatherRecords.size());
        assertEquals("3", weatherRecords.get(0).getDate());
        assertEquals(88.0, weatherRecords.get(0).getMaxTemperature(), 0.01);
        assertEquals(75.0, weatherRecords.get(0).getMinTemperature(), 0.01);
    }

    @Test
    public void testReadWeatherData_ValidFile() {
        String filePath = "de/bcxp/challenge/test_weather.csv";
        csvWeatherFileReader = new CsvWeatherFileReader(filePath, csvParser);

        List<WeatherRecord> weatherRecords = csvWeatherFileReader.readData();

        // Assertions
        assertEquals(3, weatherRecords.size());
        assertEquals("1", weatherRecords.get(0).getDate());
        assertEquals(88.0, weatherRecords.get(0).getMaxTemperature(), 0.01);
        assertEquals(59.0, weatherRecords.get(0).getMinTemperature(), 0.01);
    }
}
