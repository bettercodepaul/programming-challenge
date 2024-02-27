package de.bcxp.challenge;

public interface IWeatherRepository extends IRepository {

    void insertRow(int day, float mxt, float mnt, float tempSpread);
    String selectDayWithSmallestTemperatureSpread();
}
