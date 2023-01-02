package de.bcxp.challenge.weather;


public interface DataSource<T> {
    Iterable<T> getData();
}
