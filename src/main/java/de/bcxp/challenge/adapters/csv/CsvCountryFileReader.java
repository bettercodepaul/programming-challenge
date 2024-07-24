package de.bcxp.challenge.adapters.csv;

import de.bcxp.challenge.core.entities.CountryRecord;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CsvCountryFileReader extends CsvFileReader<CountryRecord> {

    public CsvCountryFileReader(Builder builder) {
        super(builder);
    }

    @Override
    protected LocalizedHeaderColumnNameTranslateMappingStrategy<CountryRecord> createMappingStrategy() {
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("Name", "name");
        columnMapping.put("Population", "population");
        columnMapping.put("Area (km²)", "area");

        LocalizedHeaderColumnNameTranslateMappingStrategy<CountryRecord> strategy =
                new LocalizedHeaderColumnNameTranslateMappingStrategy<>();
        strategy.setLocale(locale.toString()); // This must be defined before setType
        strategy.setType(CountryRecord.class);
        strategy.setColumnMapping(columnMapping);

        return strategy;
    }

    // Builder class
    public static class Builder extends CsvFileReader.Builder<CountryRecord> {
        public Builder(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public CsvCountryFileReader build() {
            return new CsvCountryFileReader(this);
        }
    }

}