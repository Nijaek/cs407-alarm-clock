package com.cs407.alarm_clock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "questions.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_FALSE_1 = "false1";
    public static final String COLUMN_FALSE_2 = "false2";
    public static final String COLUMN_FALSE_3 = "false3";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE_QUESTIONS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_QUESTION + " text not null, " +
            COLUMN_ANSWER + " text not null, " +
            COLUMN_FALSE_1 + " text not null, " +
            COLUMN_FALSE_2 + " text not null, " +
            COLUMN_FALSE_3 + " text not null);";

    public QuestionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }
}
