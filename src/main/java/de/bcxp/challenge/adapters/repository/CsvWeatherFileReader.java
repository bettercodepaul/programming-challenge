package de.bcxp.challenge.adapters.repository;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.ports.IWeatherFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvWeatherFileReader implements IWeatherFileReader {
    @Override
    public List<WeatherRecord> readWeatherData(String filePath) {

        List<WeatherRecord> weatherRecords = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            // Skip the header line
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                WeatherRecord record = new WeatherRecord(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]));
                weatherRecords.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherRecords;
    }
}