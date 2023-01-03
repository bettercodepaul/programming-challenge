package de.bcxp.challenge.weather;


import de.bcxp.challenge.weather.data.DataSource;
import de.bcxp.challenge.weather.data.WeatherRecord;
import de.bcxp.challenge.weather.stats.WeatherStats;
import de.bcxp.challenge.weather.utils.WeatherDataSourceMock;
import de.bcxp.challenge.weather.utils.WeatherUiMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WeatherStatsAppTest {

    @Test
    void testAppDisplaysDayWithMinTempSpread() {

        // GIVEN a WeatherStatsApp
        DataSource<WeatherRecord> dataSource = new WeatherDataSourceMock(WeatherDataSourceMock.MOCK_RECORDS);
        WeatherUiMock ui = new WeatherUiMock();
        WeatherStatsApp app = new WeatherStatsApp(dataSource, ui);

        // WHEN the app runs
        app.run();

        // THEN it "displays" the weather record with minimum spread
        WeatherRecord expectedRecord = WeatherStats.findMinTemperatureSpread(dataSource.getData());
        assertEquals(1, ui.shownRecords.size());
        assertEquals(expectedRecord, ui.shownRecords.get(0));
    }

}
