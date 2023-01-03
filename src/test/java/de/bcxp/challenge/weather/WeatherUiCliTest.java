package de.bcxp.challenge.weather;


import de.bcxp.challenge.weather.data.DataSource;
import de.bcxp.challenge.weather.data.WeatherRecord;
import de.bcxp.challenge.weather.ui.WeatherUi;
import de.bcxp.challenge.weather.ui.WeatherUiCli;
import de.bcxp.challenge.weather.utils.WeatherDataSourceMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WeatherUiCliTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testRecordIsPrinted() {

        // GIVEN a CLI user interface and a weather record
        WeatherUi ui = new WeatherUiCli();
        DataSource<WeatherRecord> dataSource = new WeatherDataSourceMock(WeatherDataSourceMock.MOCK_RECORDS);
        WeatherRecord record = dataSource.getData().iterator().next();

        // WHEN the record is shown in the UI
        ui.showMinTempSpread(record);

        // THEN it is printed to (mocked) stdout
        assert(outContent.toString().contains("Day with smallest temperature spread: 1"));
    }

    @Test
    void testNullRecordIsPrinted() {

        // GIVEN a CLI user interface and a weather record
        WeatherUi ui = new WeatherUiCli();

        // WHEN the record is shown in the UI
        ui.showMinTempSpread(null);

        // THEN it is printed to (mocked) stdout
        assert(outContent.toString().contains("Day with smallest temperature spread: [N/A]"));
    }

}
