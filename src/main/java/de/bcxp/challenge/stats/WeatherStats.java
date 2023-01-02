package de.bcxp.challenge.stats;


import de.bcxp.challenge.data.weather.WeatherRecord;

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
