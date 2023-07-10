package de.bcxp.challenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {

    @Test
    void testCalculateMinTempSpread() {
        String expectedOutput = "The day with the smallest temperature spread is: 14";
        String output = captureOutput(WeatherTask::calculateMinTempSpread);
        assertTrue(output.startsWith("The day with the smallest temperature spread is:"), "Unexpected output");
        assertTrue(output.contains("14"), "Unexpected output");
    }

    @Test
    void testCalculateMinTempSpread_EmptyFile() {
        String newFilePath ="src/main/resources/de/bcxp/challenge/testresources/weather_EmptyFile.csv";
        WeatherTask.setWeatherFilePath(newFilePath);
        String expectedOutput = "No data found.";
        String output = captureOutput(() -> {
            WeatherTask.calculateMinTempSpread();
        });
        assertEquals(expectedOutput, output, "Unexpected output");
    }

    @Test
    void testCalculateMinTempSpread_InvalidData() {
        // Prepare test data
        String weatherFilePath = "src/main/resources/de/bcxp/challenge/testresources/weather_InvalidData.csv";
        WeatherTask.setWeatherFilePath(weatherFilePath);

        // Execute the method under test
        String output = captureOutput(WeatherTask::calculateMinTempSpread);

        // Verify the output
        String expectedStartsWith = "Invalid data format in line:";
        assertTrue(output.startsWith(expectedStartsWith), "Unexpected output");
    }

    @Test
    void testCalculateHighestPopulationDensity() {
        // Prepare test data
        String countriesFilePath = "src/main/resources/de/bcxp/challenge/countries.csv";
        CountryTask.setCountriesFilePath(countriesFilePath);

        // Execute the method under test
        String output = captureOutput(CountryTask::calculateHighestPopulationDensity);

        // Verify the output
        String expectedOutput = "The country with the highest population density is: Malta";
        assertEquals(expectedOutput, output, "Unexpected output");
    }

    @Test
    void testCalculateHighestPopulationDensity_EmptyFile() {
        // Prepare test data
        String countriesFilePath = "src/main/resources/de/bcxp/challenge/testresources/countries_EmptyFile.csv";
        CountryTask.setCountriesFilePath(countriesFilePath);

        // Execute the method under test
        String output = captureOutput(CountryTask::calculateHighestPopulationDensity);

        // Verify the output
        String expectedOutput = "No data found.";
        assertEquals(expectedOutput, output, "Unexpected output");
    }

    @Test
    void testCalculateHighestPopulationDensity_InvalidData() {
        // Prepare test data
        String countriesFilePath = "src/main/resources/de/bcxp/challenge/testresources/countries_InvalidData.csv";
        CountryTask.setCountriesFilePath(countriesFilePath);

        // Execute the method under test
        String output = captureOutput(CountryTask::calculateHighestPopulationDensity);

        // Verify the output
        String expectedStartsWith = "Invalid data format in line:";
        assertTrue(output.startsWith(expectedStartsWith), "Unexpected output");
    }



    // Helper method to capture output to the console
    private String captureOutput(Runnable code) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        code.run();

        System.setOut(originalOut);
        return outputStream.toString().trim();
    }
}


