package de.bcxp.challenge;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private final String filePath;
    private final char delimiter;


    public CsvReader(String filePath, char delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public List<CSVRecord> readRecords() throws IOException {
        Reader in = new FileReader(filePath);
        // Configure CSVFormat using Builder to set the header and skip the header record
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(delimiter)
                .setHeader() // Dynamically use the first record to define header names
                .setSkipHeaderRecord(true) // Skip the header record when parsing
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();

        try (CSVParser parser = new CSVParser(in, csvFormat)) {
            List<CSVRecord> records = new ArrayList<>();
            parser.forEach(records::add);
            return records;
        }
    }
}
