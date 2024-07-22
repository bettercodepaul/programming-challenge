package de.bcxp.challenge.ports;

import java.util.List;

public interface IFileReader<T> {
    List<T> readData();
}
