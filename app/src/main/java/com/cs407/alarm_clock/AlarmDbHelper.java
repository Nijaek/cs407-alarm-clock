package com.cs407.alarm_clock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alarm.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ALARMS = "alarms";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_REPEATABLE = "repeatable";
    public static final String COLUMN_REPEAT_DAYS = "repeat_days";
    public static final String COLUMN_AM_PM = "am_pm";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE_ALARMS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_HOUR + " integer not null, " +
            COLUMN_MINUTE + " integer not null, " +
            COLUMN_NOTE + " text, " +
            COLUMN_REPEATABLE + " integer not null, " +
            COLUMN_REPEAT_DAYS + " text, " +
            COLUMN_AM_PM + " integer not null);";

    public AlarmDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
        onCreate(db);
    }
}
