package de.bcxp.challenge.exceptions;

public class CsvFileFormatException extends FileException {
    public CsvFileFormatException(String message) {
        super(message);
    }

    public CsvFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}