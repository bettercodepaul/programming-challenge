package de.bcxp.challenge.data;

import java.util.List;

@FunctionalInterface
public interface DataSourceReader<T> {

  List<T> readData();
}
