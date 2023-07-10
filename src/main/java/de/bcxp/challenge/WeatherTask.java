package de.bcxp.challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WeatherTask {

    // Path to the weather data file
    private static String WEATHER_FILE_PATH = "src/main/resources/de/bcxp/challenge/weather.csv";

    public static void main(String[] args) {
        calculateMinTempSpread();
    }

    // Setter method for updating the weather data file path
    public static void setWeatherFilePath(String filePath) {
        WEATHER_FILE_PATH = filePath;
    }

    public static void calculateMinTempSpread() {
        int minTemperatureDifference = Integer.MAX_VALUE;
        int dayWithMinDifference = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(WEATHER_FILE_PATH))) {
            br.readLine(); // Skip header line
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 3) {
                    try {
                        int day = Integer.parseInt(values[0]);
                        int maxTemperature = Integer.parseInt(values[1]);
                        int minTemperature = Integer.parseInt(values[2]);

                        // Calculate the temperature difference
                        int temperatureDifference = maxTemperature - minTemperature;

                        // Update the minimum temperature difference and corresponding day if necessary
                        if (temperatureDifference < minTemperatureDifference) {
                            minTemperatureDifference = temperatureDifference;
                            dayWithMinDifference = day;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }

            // Print the day with the smallest temperature spread
            if (dayWithMinDifference != -1) {
                System.out.println("The day with the smallest temperature spread is: " + dayWithMinDifference);
            } else {
                System.out.println("No data found.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
