package com.example.gululu.icreatorsdksampler.database;

import android.provider.BaseColumns;

/**
 * Created by 1 on 2015/12/16.
 */
public final class HistoryDBConfig {
    public HistoryDBConfig() {
    }
    public static abstract class dbConfig implements BaseColumns{
        public static final String TABLE_NAME = "ScanHistory";
        public static final String DATABASE_NAME = "iCreatorScan.db";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_UID = "uid";
        public static final String COLUMN_NAME_STATE = "state";

    }


}
