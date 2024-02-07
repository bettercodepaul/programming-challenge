package de.bcxp.challenge.task2;

import org.apache.commons.csv.CSVRecord;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class CountryAnalyzer implements DataAnalyzer {
    private final String nameColumn;
    private final String populationColumn;
    private final String areaColumn;

    public CountryAnalyzer(String nameColumn, String populationColumn, String areaColumn) {
        this.nameColumn = nameColumn;
        this.populationColumn = populationColumn;
        this.areaColumn = areaColumn;
    }

    @Override
    public String analyzeData(List<CSVRecord> records) {
        double highestDensity = 0;
        String countryWithHighestDensity = "";

        for (CSVRecord record : records) {
            System.out.println(record.get(nameColumn));
            double population = parseNumber(record.get(populationColumn).trim());
            double area = parseNumber(record.get(areaColumn).trim());
            double density = population / area;

            if (density > highestDensity) {
                highestDensity = density;
                countryWithHighestDensity = record.get(nameColumn).trim();
            }
        }

        return countryWithHighestDensity;
    }

    private double parseNumber(String numberString) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        try {
            return format.parse(numberString).doubleValue();
        } catch (ParseException e) {
            // TODO: proper error handling
            e.printStackTrace();
            return 0;
        }
    }
}
