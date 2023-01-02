package de.bcxp.challenge.weather;

import com.opencsv.bean.CsvBindByName;

public class WeatherRecord {

        @CsvBindByName(column = "Day", required = true)
        private int day;

        @CsvBindByName(column = "MxT", required = true)
        private int mxt;

        @CsvBindByName(column = "MnT", required = true)
        private int mnt;

        public WeatherRecord() {}  // openCSV needs this constructor when reading weather records from file

        public WeatherRecord(int day, int mxt, int mnt) {
                this.day = day;
                this.mxt = mxt;
                this.mnt = mnt;
        }

        public int getDay() {
                return day;
        }

        public int getMxT() {
                return mxt;
        }

        public int getMnT() {
                return mnt;
        }

}
