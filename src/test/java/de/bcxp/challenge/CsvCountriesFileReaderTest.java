package de.bcxp.challenge;

import de.bcxp.challenge.adapters.csv.CsvCountryFileReader;
import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.exceptions.FileFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import de.bcxp.challenge.adapters.csv.CsvParser;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvCountriesFileReaderTest {

    private CsvCountryFileReader csvCountryFileReader;
    private CsvParser csvParser;

    @BeforeEach
    public void setUp() {
        csvParser = new CsvParser.CsvParserBuilder().withDelimiter(";").withLocale(Locale.GERMANY).withCharset(StandardCharsets.UTF_8).build();
    }

    @Test
    public void testReadCountryData_InvalidHeader() {
        String filePath = "de/bcxp/challenge/invalid_header_countries.csv";
        csvCountryFileReader = new CsvCountryFileReader(filePath, csvParser);

        Executable executable = () -> csvCountryFileReader.readData();

        FileFormatException exception = assertThrows(FileFormatException.class, executable);
        assertEquals("Invalid CSV file format. Expected headers: Name,Population,Area (km²)", exception.getMessage());
    }

    @Test
    public void testReadCountryData_InvalidLine() {
        String filePath = "de/bcxp/challenge/invalid_line_countries.csv";
        csvCountryFileReader = new CsvCountryFileReader(filePath, csvParser);

        List<CountryRecord> countryRecords = csvCountryFileReader.readData();

        // Check that valid records are read correctly
        assertEquals(1, countryRecords.size());
        assertEquals("Germany", countryRecords.get(0).getName());
        assertEquals(83120520, countryRecords.get(0).getPopulation(), 0.01);
        assertEquals(357386, countryRecords.get(0).getArea(), 0.01);

    }

    @Test
    public void testReadCountryData_ValidFile() {
        String filePath = "de/bcxp/challenge/test_countries.csv";
        csvCountryFileReader = new CsvCountryFileReader(filePath, csvParser);
        List<CountryRecord> countryRecords = csvCountryFileReader.readData();

        // Assertions
        assertEquals(3, countryRecords.size());
        assertEquals("Austria", countryRecords.get(0).getName());
        assertEquals(8926000, countryRecords.get(0).getPopulation(), 0.01);
        assertEquals(83855, countryRecords.get(0).getArea(), 0.01);
    }
}