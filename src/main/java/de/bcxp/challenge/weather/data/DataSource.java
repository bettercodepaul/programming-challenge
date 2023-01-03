package de.bcxp.challenge.weather.data;


public interface DataSource<T> {
    Iterable<T> getData();
}
