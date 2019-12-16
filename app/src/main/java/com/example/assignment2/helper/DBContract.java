package com.example.assignment2.helper;

import android.provider.BaseColumns;

public class DBContract {

    private DBContract() {}

    public static final class BikeEntry implements BaseColumns {
        public static final String TABLE_NAME   = "BikeList";
//        public static final String COLUMN_ID    = "BikeID";
        public static final String COLUMN_BRAND = "BikeBrand";
        public static final String COLUMN_MODEL = "BikeModel";
        public static final String COLUMN_DOP   = "BikeDOP";
        public static final String COLUMN_PICID = "BikePIC";




    }
}
