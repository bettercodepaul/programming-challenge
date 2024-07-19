package de.bcxp.challenge.adapters.repository;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.exceptions.FileException;
import de.bcxp.challenge.exceptions.FileFormatException;
import de.bcxp.challenge.exceptions.FileNotFoundException;
import de.bcxp.challenge.ports.IWeatherFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvWeatherFileReader implements IWeatherFileReader {
    @Override
    public List<WeatherRecord> readWeatherData(String filePath) {

        List<WeatherRecord> weatherRecords = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String headerLine = br.readLine();
            // Check if the file is empty
            if (inputStream == null || headerLine == null) {
                throw new FileNotFoundException("The file is empty or not found: " + filePath);
            }

            String[] headers = headerLine.split(",");
            if (headers.length < 3 ||
                    !Arrays.asList(headers).contains("Day") ||
                    !Arrays.asList(headers).contains("MxT") ||
                    !Arrays.asList(headers).contains("MnT")) {
                throw new FileFormatException("Invalid CSV file format. Expected headers: Day,MxT,MnT");
            }

            int dayColumnIndex = Arrays.asList(headers).indexOf("Day");
            int mxtColumnIndex = Arrays.asList(headers).indexOf("MxT");
            int mntColumnIndex = Arrays.asList(headers).indexOf("MnT");

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] values = line.split(",");
                    if (values.length < 3) {
                        throw new FileFormatException("Invalid CSV line: " + line);
                    }
                    String date = values[dayColumnIndex];

                    // Validate numbers
                    double maxTemperature = validateNumber(values[mxtColumnIndex]);
                    double minTemperature = validateNumber(values[mntColumnIndex]);

                    WeatherRecord record = new WeatherRecord(date, maxTemperature, minTemperature);
                    weatherRecords.add(record);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }
        }
        catch (IOException e) {
            throw new FileException("Error reading CSV file: " + filePath, e);
        }

        return weatherRecords;
    }

    private double validateNumber(String s) {
        return Double.parseDouble(s);
    }
}