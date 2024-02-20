package de.bcxp.challenge;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVFileParser implements IFileParser {

    public List<String[]> parseFile(String filePath, char separator) {

        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(parser).withSkipLines(1).build();
            return reader.readAll();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
