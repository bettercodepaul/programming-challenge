package de.bcxp.challenge.adapters.service;

import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.ports.ICountryStatisticsService;

import java.util.List;

public class ConsoleCountryDisplayService implements ICountryStatisticsService {
    @Override
    public void calculateStatistics(List<CountryRecord> countryRecords) {
        for (CountryRecord record : countryRecords) {
            System.out.println("Country: " + record.getName() + ", Population Density: " + record.getDensity());
        }
    }

    @Override
    public void displayCountryWithHighestDensity(CountryRecord countryRecord) {
        System.out.println("Country with the highest population density: " + countryRecord.getName() +
                " with a density of " + countryRecord.getDensity() + " people per km².");
    }
}
