package de.bcxp.challenge;

public interface IWeatherRepository {

    public void createDB();
    public void closeConnection();
    void insertRow(int day, float mxt, float mnt, float tempSpread);
    public String selectDayWithSmallestTemperatureSpread();
}
