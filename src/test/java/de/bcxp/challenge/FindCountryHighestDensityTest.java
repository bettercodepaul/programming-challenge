package de.bcxp.challenge;

import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.core.usecases.FindCountryHighestDensityUseCase;
import de.bcxp.challenge.core.usecases.FindDayLowestTemperatureRangeUseCase;
import de.bcxp.challenge.core.usecases.GetCountryDataUseCase;
import de.bcxp.challenge.ports.ICountryRepository;
import de.bcxp.challenge.ports.IWeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class FindCountryHighestDensityTest {
    @Mock
    private ICountryRepository mockCountryRepository;

    private FindCountryHighestDensityUseCase findCountryWithHighestDensityUseCase;
    private GetCountryDataUseCase getCountryDataUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getCountryDataUseCase = new GetCountryDataUseCase(mockCountryRepository);
        findCountryWithHighestDensityUseCase = new FindCountryHighestDensityUseCase();
    }

    @Test
    public void testExecute() {
        // Mock data
        List<CountryRecord> mockCountryRecords = Arrays.asList(
                new CountryRecord("Austria", 8926000L, 83855.0),
                new CountryRecord("Croatia", 4036355L, 56594.0),
                new CountryRecord("Germany", 83120520L, 357386.0)
        );

        // Mock behavior
        when(mockCountryRepository.getAllCountryData()).thenReturn(mockCountryRecords);

        // Test the method
        CountryRecord result = findCountryWithHighestDensityUseCase.execute(getCountryDataUseCase.execute());

        // Assertions
        assertEquals("Germany", result.getName());
        assertEquals(232.57, result.getDensity(), 0.01);
    }
}
