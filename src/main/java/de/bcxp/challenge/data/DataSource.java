package de.bcxp.challenge.data;


public interface DataSource<T> {
    Iterable<T> getData();
}
