package de.bcxp.challenge.core.usecases;

import de.bcxp.challenge.core.entities.CountryRecord;

import java.util.List;

public class FindCountryHighestDensityUseCase {

    public CountryRecord execute(List<CountryRecord> countryRecords) {
        CountryRecord countryWithHighestDensity = null;

        for (CountryRecord record : countryRecords) {
            if (countryWithHighestDensity == null || record.getDensity() > countryWithHighestDensity.getDensity()) {
                countryWithHighestDensity = record;
            }
        }
        return countryWithHighestDensity;
    }
}
