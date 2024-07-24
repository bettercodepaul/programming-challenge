package de.bcxp.challenge.adapters.csv;

import de.bcxp.challenge.core.entities.WeatherRecord;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CsvWeatherFileReader extends CsvFileReader<WeatherRecord> {
    public CsvWeatherFileReader(Builder builder) {
        super(builder);
    }

    @Override
    protected LocalizedHeaderColumnNameTranslateMappingStrategy<WeatherRecord> createMappingStrategy() {
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("Day", "date");
        columnMapping.put("MxT", "maxTemperature");
        columnMapping.put("MnT", "minTemperature");

        LocalizedHeaderColumnNameTranslateMappingStrategy<WeatherRecord> strategy =
                new LocalizedHeaderColumnNameTranslateMappingStrategy<>();
        strategy.setLocale(locale.toString()); // This must be defined before setType
        strategy.setType(WeatherRecord.class);
        strategy.setColumnMapping(columnMapping);

        return strategy;
    }

    // Builder class
    public static class Builder extends CsvFileReader.Builder<WeatherRecord> {

        public Builder(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public CsvWeatherFileReader build() {
            return new CsvWeatherFileReader(this);
        }
    }
}