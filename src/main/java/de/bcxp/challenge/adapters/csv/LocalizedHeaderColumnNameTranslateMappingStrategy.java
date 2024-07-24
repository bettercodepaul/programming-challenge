package de.bcxp.challenge.adapters.csv;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvBadConverterException;

import java.lang.reflect.Field;
import java.util.Currency;
import java.util.UUID;

/**
 * Custom extension to OpenCSV's HeaderColumnNameTranslateMappingStrategy class
 * to support localization when converting field values.
 * Note: Entities shouldn't know things related to how to read/get the data,
 * this is a solution since we don't want to annotate entity classes with Csv Annotations,
 * and OpenCsv library doesn't give us a nice API for custom validations without the using of annotations
 * @see <a href="https://sourceforge.net/p/opencsv/feature-requests/125/">this thread</a>
 */
public class LocalizedHeaderColumnNameTranslateMappingStrategy<T> extends HeaderColumnNameTranslateMappingStrategy<T> {
    private String locale = null;

    /**
     * Note: This function must be called before setType() when building object
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    protected CsvConverter determineConverter(
            Field field,
            Class<?> elementType,
            String locale,
            String writeLocale,
            Class<? extends AbstractCsvConverter> customConverter)
            throws CsvBadConverterException {
        CsvConverter converter;

        // Ignoring all CsvAnnotations since we don't want to annotate entities for csv

        if (elementType.equals(Currency.class)) {
            converter = new ConverterCurrency(this.errorLocale);
        } else if (elementType.isEnum()) {
            converter = new ConverterEnum(elementType, this.locale, this.locale, this.errorLocale);
        } else if (elementType.equals(UUID.class)) {
            converter = new ConverterUUID(this.errorLocale);
        } else {
            converter = new ConverterPrimitiveTypes(elementType, this.locale, this.locale, this.errorLocale);
        }

        return converter;
    }
}
