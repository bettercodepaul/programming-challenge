package de.bcxp.challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountryTask {

    // Path to the countries data file
    private static String COUNTRIES_FILE_PATH = "src/main/resources/de/bcxp/challenge/countries.csv";

    public static void main(String[] args) {
        calculateHighestPopulationDensity();
    }

    // Setter method for updating the countries data file path
    public static void setCountriesFilePath(String filePath) {
        COUNTRIES_FILE_PATH = filePath;
    }

    public static void calculateHighestPopulationDensity() {
        // Variables to store the maximum population density and the corresponding country
        int maxPopulationDensity = 0;
        String countryWithMaxDensity = "";

        try (FileReader fileReader = new FileReader(COUNTRIES_FILE_PATH);
             BufferedReader br = new BufferedReader(fileReader)) {

            br.readLine(); // Skip the header line
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                if (values.length >= 5) {
                    String country = values[0];
                    int population = parseIntWithoutCommas(values[3]);
                    int area = parseIntWithoutCommas(values[4]);

                    // Calculate population density
                    int populationDensity = population / area;

                    // Update the maximum population density and corresponding country if necessary
                    if (populationDensity > maxPopulationDensity) {
                        maxPopulationDensity = populationDensity;
                        countryWithMaxDensity = country;
                    }
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }

            // Print the country with the highest population density
            if (!countryWithMaxDensity.isEmpty()) {
                System.out.println("The country with the highest population density is: " + countryWithMaxDensity);
            } else {
                System.out.println("No data found.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int parseIntWithoutCommas(String value) {
        // Remove commas and unnecessary decimal parts from the value
        String cleanedValue = value.replace(".", "").replaceAll(",00", "");
        return Integer.parseInt(cleanedValue);
    }
}
