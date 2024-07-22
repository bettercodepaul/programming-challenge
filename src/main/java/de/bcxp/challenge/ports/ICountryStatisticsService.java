package de.bcxp.challenge.ports;

import de.bcxp.challenge.core.entities.CountryRecord;

import java.util.List;

public interface ICountryStatisticsService {
    void calculateStatistics(List<CountryRecord> countryRecords);

    void displayCountryWithHighestDensity(CountryRecord countryRecord);
}
