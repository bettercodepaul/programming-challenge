package de.bcxp.challenge;

import de.bcxp.challenge.core.entities.WeatherRecord;


import java.util.Arrays;
import java.util.List;

import de.bcxp.challenge.core.usecases.FindDayLowestTemperatureRangeUseCase;
import de.bcxp.challenge.ports.IWeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class FindDayLowestTemperatureRangeTest {
    @Mock
    private IWeatherRepository mockWeatherRepository;

    private FindDayLowestTemperatureRangeUseCase findDayWithLowestTemperatureRangeUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        findDayWithLowestTemperatureRangeUseCase = new FindDayLowestTemperatureRangeUseCase(mockWeatherRepository);
    }

    @Test
    public void testExecute() {
        // Mock data
        List<WeatherRecord> mockWeatherRecords = Arrays.asList(
                new WeatherRecord("1", 88.0, 59),
                new WeatherRecord("2", 79.0, 63.0),
                new WeatherRecord("3", 77.0, 55.0)
        );

        // Mock behavior
        when(mockWeatherRepository.getAllWeatherData()).thenReturn(mockWeatherRecords);

        // Test the method
        WeatherRecord result = findDayWithLowestTemperatureRangeUseCase.execute();

        // Assertions
        assertEquals("2", result.getDate());
        assertEquals(16.0, result.getTemperatureRange(), 0.01);
    }
}
