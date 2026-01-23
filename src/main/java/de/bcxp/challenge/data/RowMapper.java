package de.bcxp.challenge.data;

@FunctionalInterface
public interface RowMapper<T> {

  T map(String[] columns);
}
