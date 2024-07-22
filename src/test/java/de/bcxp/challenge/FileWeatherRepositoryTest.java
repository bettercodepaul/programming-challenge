package de.bcxp.challenge;

import de.bcxp.challenge.adapters.csv.CsvWeatherFileReader;
import de.bcxp.challenge.adapters.repository.FileWeatherRepository;
import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.ports.IWeatherRepository;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileWeatherRepositoryTest {
    @Mock
    private CsvWeatherFileReader mockCsvWeatherFileReader;

    private IWeatherRepository weatherRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherRepository = new FileWeatherRepository(mockCsvWeatherFileReader);
    }

    @Test
    public void testGetAllWeatherData() {
        // Mock data
        List<WeatherRecord> mockWeatherRecords = Arrays.asList(
                new WeatherRecord("1", 88.0, 59),
                new WeatherRecord("2", 79.0, 63.0),
                new WeatherRecord("3", 77.0, 55.0)
        );

        // Mock behavior
        when(mockCsvWeatherFileReader.readData()).thenReturn(mockWeatherRecords);

        // Test the method
        List<WeatherRecord> weatherRecords = weatherRepository.getAllWeatherData();

        // Assertions
        assertEquals(3, weatherRecords.size());
        assertEquals("1", weatherRecords.get(0).getDate());
        assertEquals(88.0, weatherRecords.get(0).getMaxTemperature(), 0.01);
        assertEquals(59.0, weatherRecords.get(0).getMinTemperature(), 0.01);
    }
}
