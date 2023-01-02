package de.bcxp.challenge.stats;


import de.bcxp.challenge.data.weather.WeatherDataSourceMock;
import de.bcxp.challenge.data.weather.WeatherRecord;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class WeatherStatsTest {

    @Test
    void testMinSpreadForEmptyListIsNull() {

        // GIVEN an empty list of weather records
        Iterable<WeatherRecord> emptyRecords = new ArrayList<>();

        // WHEN the record with minimum temperature spread is calculated
        WeatherRecord minSpreadRecord = WeatherStats.findMinTemperatureSpread(emptyRecords);

        // THEN the result is null
        assertNull(minSpreadRecord);
    }

    @Test
    void testMinimumTemperatureSpreadIsFound() {

        // GIVEN weather records and their minimum temperature spread
        List<WeatherRecord> records = WeatherDataSourceMock.MOCK_RECORDS;
        WeatherRecord referenceMinSpreadRecord = records
                .stream()
                .min(
                        Comparator.comparing(WeatherStatsTest::calcTemperatureSpread)
                )
                .orElse(null);

        // WHEN the record with minimum temperature spread is calculated
        WeatherRecord calculatedMinSpreadRecord = WeatherStats.findMinTemperatureSpread(records);

        // THEN it corresponds to the reference minimum
        assertNotNull(referenceMinSpreadRecord);
        assertEquals(referenceMinSpreadRecord.getDay(), calculatedMinSpreadRecord.getDay());
    }

    private static int calcTemperatureSpread(WeatherRecord record) {
        return record.getMxT() - record.getMnT();
    }

}
