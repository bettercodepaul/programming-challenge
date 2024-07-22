package de.bcxp.challenge;

import de.bcxp.challenge.adapters.csv.CsvCountryFileReader;
import de.bcxp.challenge.adapters.repository.FileCountryRepository;
import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.ports.ICountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileCountryRepositoryTest {
    @Mock
    private CsvCountryFileReader mockCsvCountryFileReader;

    private ICountryRepository countryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        countryRepository = new FileCountryRepository(mockCsvCountryFileReader);
    }

    @Test
    public void testGetAllWeatherData() {
        // Mock data
        List<CountryRecord> mockCountryRecords = Arrays.asList(
                new CountryRecord("Austria", 8926000L, 83855.0),
                new CountryRecord("Croatia", 4036355L, 56594.0),
                new CountryRecord("Germany", 83120520L, 357386.0)
        );

        // Mock behavior
        when(mockCsvCountryFileReader.readData()).thenReturn(mockCountryRecords);

        // Test the method
        List<CountryRecord> CountryRecords = countryRepository.getAllCountryData();

        // Assertions
        assertEquals(3, CountryRecords.size());
        assertEquals("Austria", CountryRecords.get(0).getName());
        assertEquals(8926000L, CountryRecords.get(0).getPopulation(), 0.01);
        assertEquals(83855.0, CountryRecords.get(0).getArea(), 0.01);
    }
}
