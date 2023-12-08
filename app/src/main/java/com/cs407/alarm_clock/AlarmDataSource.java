package com.cs407.alarm_clock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class AlarmDataSource {
    private SQLiteDatabase database;
    private AlarmDbHelper dbHelper;

    public AlarmDataSource(Context context) {
        dbHelper = new AlarmDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createAlarm(Alarm alarm) {
        ContentValues values = new ContentValues();
        values.put(AlarmDbHelper.COLUMN_HOUR, alarm.getHour());
        values.put(AlarmDbHelper.COLUMN_MINUTE, alarm.getMinute());
        values.put(AlarmDbHelper.COLUMN_NOTE, alarm.getNote());
        values.put(AlarmDbHelper.COLUMN_REPEATABLE, alarm.isRepeatable() ? 1 : 0);
        values.put(AlarmDbHelper.COLUMN_REPEAT_DAYS, alarm.isRepeatable() ? convertListToString(alarm.getRepeatDays()) : "");
        values.put(AlarmDbHelper.COLUMN_AM_PM, alarm.isAM() ? 1 : 0);

        return database.insert(AlarmDbHelper.TABLE_ALARMS, null, values);
    }

    public void updateAlarm(Alarm alarm) {
        ContentValues values = new ContentValues();
        values.put(AlarmDbHelper.COLUMN_HOUR, alarm.getHour());
        values.put(AlarmDbHelper.COLUMN_MINUTE, alarm.getMinute());
        values.put(AlarmDbHelper.COLUMN_NOTE, alarm.getNote());
        values.put(AlarmDbHelper.COLUMN_REPEATABLE, alarm.isRepeatable() ? 1 : 0);
        values.put(AlarmDbHelper.COLUMN_REPEAT_DAYS, alarm.isRepeatable() ? convertListToString(alarm.getRepeatDays()) : "");
        values.put(AlarmDbHelper.COLUMN_AM_PM, alarm.isAM() ? 1 : 0);

        String whereClause = AlarmDbHelper.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(alarm.getId())};

        database.update(AlarmDbHelper.TABLE_ALARMS, values, whereClause, whereArgs);
    }

    public void deleteAlarm(long alarmId) {
        String whereClause = AlarmDbHelper.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(alarmId)};

        database.delete(AlarmDbHelper.TABLE_ALARMS, whereClause, whereArgs);
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarms = new ArrayList<>();
        Cursor cursor = database.query(AlarmDbHelper.TABLE_ALARMS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Alarm alarm = cursorToAlarm(cursor);
                alarms.add(alarm);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return alarms;
    }

    private Alarm cursorToAlarm(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(AlarmDbHelper.COLUMN_ID);
        int hourIndex = cursor.getColumnIndex(AlarmDbHelper.COLUMN_HOUR);
        int minuteIndex = cursor.getColumnIndex(AlarmDbHelper.COLUMN_MINUTE);
        int noteIndex = cursor.getColumnIndex(AlarmDbHelper.COLUMN_NOTE);
        int repeatableIndex = cursor.getColumnIndex(AlarmDbHelper.COLUMN_REPEATABLE);
        int repeatDaysIndex = cursor.getColumnIndex(AlarmDbHelper.COLUMN_REPEAT_DAYS);
        int amPmIndex = cursor.getColumnIndex(AlarmDbHelper.COLUMN_AM_PM);

        long id = cursor.getLong(idIndex);
        int hour = cursor.getInt(hourIndex);
        int minute = cursor.getInt(minuteIndex);
        String note = cursor.getString(noteIndex);
        boolean isRepeatable = cursor.getInt(repeatableIndex) == 1;
        List<Integer> repeatDays = convertStringToList(cursor.getString(repeatDaysIndex));
        boolean isAM = cursor.getInt(amPmIndex) == 1;

        return new Alarm(id, hour, minute, note, isRepeatable, repeatDays, isAM);
    }

    private String convertListToString(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (Integer value : list) {
            builder.append(value).append(",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1); // Remove the trailing comma
        }
        return builder.toString();
    }

    private List<Integer> convertStringToList(String string) {
        List<Integer> list = new ArrayList<>();
        if (string != null && !string.isEmpty()) {
            String[] items = string.split(",");
            for (String item : items) {
                list.add(Integer.parseInt(item));
            }
        }
        return list;
    }
}
