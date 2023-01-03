package de.bcxp.challenge.weather.stats;


import de.bcxp.challenge.weather.data.WeatherRecord;

import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WeatherStats {

    public static WeatherRecord findMinTemperatureSpread(Iterable<WeatherRecord> records) {

        Stream<WeatherRecord> recordStream = StreamSupport.stream(records.spliterator(), false);

        return recordStream
                .min(Comparator.comparing(WeatherStats::getMinMaxSpread))
                .orElse(null);
    }

    private static int getMinMaxSpread(WeatherRecord indexedRecord) {
        return indexedRecord.getMxT() - indexedRecord.getMnT();
    }

}
