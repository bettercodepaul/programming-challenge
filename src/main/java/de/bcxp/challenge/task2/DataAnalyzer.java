package de.bcxp.challenge.task2;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface DataAnalyzer {
    String analyzeData(List<CSVRecord> records);
}
