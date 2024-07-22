/**
 * CSV Parser Class based on Builder Pattern Architecture.
 * Creates a parser for CSV readings
 */

package de.bcxp.challenge.adapters.csv;

import java.nio.charset.Charset;
import java.util.Locale;

public class CsvParser {
    // optional parameters
    private final String delimiter;
    private final Charset charset;
    private final Locale locale;

    public String getDelimiter() {
        return delimiter;
    }

    public Charset getCharset() {
        return charset;
    }

    public Locale getLocale() {
        return locale;
    }

    private CsvParser(CsvParserBuilder builder) {
        this.delimiter = builder.delimiter;
        this.charset = builder.charset;
        this.locale = builder.locale;
    }

    // Builder class
    public static class CsvParserBuilder {

        // optional parameters with default values
        private String delimiter = ",";
        private Charset charset = Charset.defaultCharset();
        private Locale locale = Locale.getDefault();

        public CsvParserBuilder withDelimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public CsvParserBuilder withCharset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public CsvParserBuilder withLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public CsvParser build() {
            return new CsvParser(this);
        }
    }

}
