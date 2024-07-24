package de.bcxp.challenge.adapters.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.bcxp.challenge.exceptions.CsvFileFormatException;
import de.bcxp.challenge.exceptions.FileNotFoundException;
import de.bcxp.challenge.ports.IFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * Base class for Csv File Reader
 * This class uses OpenCSV in combination with Java Beans for parsing data
 */

public abstract class CsvFileReader<T> implements IFileReader<T> {

    // Required fields
    private final InputStream inputStream;
    private final char separator;
    // Optional fields
    protected Locale locale;

    protected CsvFileReader(Builder<T> builder) {
        this.inputStream = builder.inputStream;
        this.locale = builder.locale;
        this.separator = builder.separator;
    }

    protected abstract LocalizedHeaderColumnNameTranslateMappingStrategy<T> createMappingStrategy();

    @Override
    public List<T> readData() throws CsvFileFormatException, FileNotFoundException {
        try {
            if (inputStream == null || inputStream.available() == 0) {
                throw new FileNotFoundException("The file is empty or not found");
            }
        } catch (IOException e) {
            throw new FileNotFoundException("The file is empty or not found");
        }

        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            LocalizedHeaderColumnNameTranslateMappingStrategy<T> strategy = createMappingStrategy();
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withThrowExceptions(true)
                    .withSeparator(separator)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new CsvFileFormatException("Error reading CSV file", e);
        }
    }

    // Builder class
    public static abstract class Builder<T> {
        private final InputStream inputStream;
        private Locale locale = Locale.getDefault();
        private char separator = ',';

        public Builder(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public Builder<T> withLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder<T> withSeparator(char separator) {
            this.separator = separator;
            return this;
        }

        public abstract CsvFileReader<T> build();
    }
}