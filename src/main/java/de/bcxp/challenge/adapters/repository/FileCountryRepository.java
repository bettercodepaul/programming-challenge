package de.bcxp.challenge.adapters.repository;

import de.bcxp.challenge.core.entities.CountryRecord;
import de.bcxp.challenge.ports.ICountryRepository;
import de.bcxp.challenge.ports.IFileReader;

import java.util.List;

public class FileCountryRepository implements ICountryRepository {
    private final IFileReader<CountryRecord> countryFileReader;

    public FileCountryRepository(IFileReader<CountryRecord> countryFileReader) {
        this.countryFileReader = countryFileReader;
    }

    @Override
    public List<CountryRecord> getAllCountryData() {
        return countryFileReader.readData();
    }
}
