package de.bcxp.challenge.weather;

import de.bcxp.challenge.weather.data.DataSource;
import de.bcxp.challenge.weather.data.WeatherRecord;
import de.bcxp.challenge.weather.utils.WeatherDataSourceEnum;
import de.bcxp.challenge.weather.utils.WeatherDataSourceFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class WeatherDataSourceTest {

    @ParameterizedTest
    @EnumSource(WeatherDataSourceEnum.class)  // Run test for all DataSource implementations (as listed in the enum)
    void testAllWeatherDataSourcesReturnPlausibleWeatherRecords(WeatherDataSourceEnum dataSourceEnum) throws FileNotFoundException {

        // GIVEN a data source for weather records
        DataSource<WeatherRecord> dataSource = WeatherDataSourceFactory.newInstance(dataSourceEnum);
        assertNotNull(dataSource);

        // WHEN data is read into a list
        List<WeatherRecord> records = new ArrayList<>();
        dataSource.getData().forEach(records::add);

        // THEN there are three plausible weather records
        for (int i = 0; i < 3; i++) {
            WeatherRecord record = records.get(i);
            assertEquals(i+1, record.getDay());
            assert(record.getMxT() >= record.getMnT());
        }

    }

    @ParameterizedTest
    @EnumSource(WeatherDataSourceEnum.class)  // Run test for all DataSource implementations (as listed in the enum)
    void testDataSourcesReturnKnownMockData(WeatherDataSourceEnum dataSourceEnum) throws FileNotFoundException {

        // GIVEN a data source for weather records
        DataSource<WeatherRecord> dataSource = WeatherDataSourceFactory.newInstance(dataSourceEnum);
        assertNotNull(dataSource);

        // WHEN data is read into a list
        List<WeatherRecord> records = new ArrayList<>();
        dataSource.getData().forEach(records::add);

        // THEN the weather records contain expected values (regression test)
        assertEquals(88, records.get(0).getMxT());
        assertEquals(79, records.get(1).getMxT());
        assertEquals(63, records.get(1).getMnT());
        assertEquals(55, records.get(2).getMnT());
    }

}
