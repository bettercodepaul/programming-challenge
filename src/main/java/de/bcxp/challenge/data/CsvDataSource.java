package de.bcxp.challenge.data;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvDataSource<T> implements DataSourceReader<T> {

  private static final Logger log = LoggerFactory.getLogger(CsvDataSource.class);

  private final String resourcePath;
  private final char delimiter;
  private final RowMapper<T> rowMapper;

  public CsvDataSource(String resourcePath, char delimiter, RowMapper<T> rowMapper) {
    this.resourcePath = resourcePath;
    this.delimiter = delimiter;
    this.rowMapper = rowMapper;
  }

  @Override
  public List<T> readData() {
    var lines = readLinesFromResource();
    return parseLines(lines);
  }

  private List<String[]> readLinesFromResource() {
    var inputStream = getClass().getResourceAsStream(resourcePath);
    Objects.requireNonNull(inputStream, "Resource not found: " + resourcePath);

    try {
      var reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      var csvReader = createCsvReader(reader);

      return csvReader.readAll();

    } catch (IOException | CsvException e) {
      log.error("Failed to read CSV file: {}", resourcePath, e);
      throw new RuntimeException("Failed to read CSV file: " + resourcePath, e);
    }
  }

  private CSVReader createCsvReader(InputStreamReader reader) {
    var parser = new CSVParserBuilder().withSeparator(delimiter).build();

    return new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();
  }

  private List<T> parseLines(List<String[]> lines) {
    return lines.stream().map(rowMapper::map).toList();
  }
}
