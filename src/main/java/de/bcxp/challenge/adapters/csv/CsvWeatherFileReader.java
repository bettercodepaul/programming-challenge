package de.bcxp.challenge.adapters.csv;

import de.bcxp.challenge.core.entities.WeatherRecord;
import de.bcxp.challenge.exceptions.FileFormatException;

import java.util.Map;

public class CsvWeatherFileReader extends CsvFileReader<WeatherRecord> {
    public CsvWeatherFileReader(String filePath) {
        super(filePath);
    }

    public CsvWeatherFileReader(String filePath, CsvParser parser) {
        super(filePath, parser);
    }
    @Override
    protected WeatherRecord parseLine(String[] values, Map<String, Integer> columnIndexes) throws FileFormatException {
        if (values.length < columnIndexes.size()) {
            throw new FileFormatException("Invalid CSV line: " + String.join(",", values));
        }

        String date = validateDate(values[columnIndexes.get("Day")]);
        try {
            double maxTemperature = validateTemperature(values[columnIndexes.get("MxT")]);
            double minTemperature = validateTemperature(values[columnIndexes.get("MnT")]);
            return new WeatherRecord(date, maxTemperature, minTemperature);
        } catch (NumberFormatException e) {
            throw new FileFormatException("Invalid CSV line: " + String.join(",", values));
        }
    }

    @Override
    protected String[] getExpectedHeaders() {
        return new String[]{"Day", "MxT", "MnT"}; // TODO refactor to receive headers as parameters
    }

    private String validateDate(String dateStr) {
        try {
            int date = Integer.parseInt(dateStr);
            if (date < 1 || date > 31) {
                throw new FileFormatException("Invalid date number: " + dateStr);
            }
            return Integer.toString(date);
        } catch (NumberFormatException e) {
            throw new FileFormatException("Invalid date format: " + dateStr, e);
        }
    }

    private double validateTemperature(String tempStr) throws NumberFormatException {
        return Double.parseDouble(tempStr);
    }
}