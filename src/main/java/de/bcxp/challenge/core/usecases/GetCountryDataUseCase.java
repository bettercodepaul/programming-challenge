package de.bcxp.challenge.core.usecases;

import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.ports.ICountryRepository;

import java.util.List;

public class GetCountryDataUseCase {
    private final ICountryRepository countryRepository;

    public GetCountryDataUseCase(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryRecord> execute() {
        return countryRepository.getAllCountryData();
    }
}
