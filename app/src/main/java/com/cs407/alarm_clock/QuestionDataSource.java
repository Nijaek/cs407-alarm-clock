package com.cs407.alarm_clock;

import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionDataSource {
    private SQLiteDatabase database;
    private QuestionDbHelper dbHelper;

    public QuestionDataSource(Context context) {
        dbHelper = new QuestionDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createQuestion(QuestionObject question) {
        ContentValues values = new ContentValues();
        values.put(QuestionDbHelper.COLUMN_QUESTION, question.getQuestion());
        values.put(QuestionDbHelper.COLUMN_ANSWER, question.getAnswer());
        values.put(QuestionDbHelper.COLUMN_FALSE_1, question.getFalse1());
        values.put(QuestionDbHelper.COLUMN_FALSE_2, question.getFalse2());
        values.put(QuestionDbHelper.COLUMN_FALSE_3, question.getFalse3());

        return database.insert(QuestionDbHelper.TABLE_QUESTIONS, null, values);
    }

    public void updateQuestion(QuestionObject question) {
        ContentValues values = new ContentValues();
        values.put(QuestionDbHelper.COLUMN_QUESTION, question.getQuestion());
        values.put(QuestionDbHelper.COLUMN_ANSWER, question.getAnswer());
        values.put(QuestionDbHelper.COLUMN_FALSE_1, question.getFalse1());
        values.put(QuestionDbHelper.COLUMN_FALSE_2, question.getFalse2());
        values.put(QuestionDbHelper.COLUMN_FALSE_3, question.getFalse3());

        String whereClause = QuestionDbHelper.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(question.getId())};

        database.update(QuestionDbHelper.TABLE_QUESTIONS, values, whereClause, whereArgs);
    }

    public void deleteQuestion(long questionId) {
        String whereClause = QuestionDbHelper.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(questionId)};

        database.delete(QuestionDbHelper.TABLE_QUESTIONS, whereClause, whereArgs);
    }

    public void deleteAllQuestions() {
        database.delete(QuestionDbHelper.TABLE_QUESTIONS, null, null);
    }


    public List<QuestionObject> getAllQuestions() {
        List<QuestionObject> questions = new ArrayList<>();
        Cursor cursor = database.query(QuestionDbHelper.TABLE_QUESTIONS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                QuestionObject question = cursorToQuestion(cursor);
                questions.add(question);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return questions;
    }

    public QuestionObject getRandomQuestion() {
        // Retrieve the count of questions in the database
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + QuestionDbHelper.TABLE_QUESTIONS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            return null; // Return null if there are no questions in the database
        }

        // Generate a random index within the range of available questions
        Random random = new Random();
        int randomIndex = random.nextInt(count);

        // Retrieve a random question from the database
        cursor = database.rawQuery("SELECT * FROM " + QuestionDbHelper.TABLE_QUESTIONS + " LIMIT 1 OFFSET " + randomIndex, null);

        if (cursor != null && cursor.moveToFirst()) {
            QuestionObject randomQuestion = cursorToQuestion(cursor);
            cursor.close();
            return randomQuestion;
        } else {
            return null; // Return null if there was an issue retrieving the random question
        }
    }

    private QuestionObject cursorToQuestion(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(QuestionDbHelper.COLUMN_ID);
        int questionIndex = cursor.getColumnIndex(QuestionDbHelper.COLUMN_QUESTION);
        int answerIndex = cursor.getColumnIndex(QuestionDbHelper.COLUMN_ANSWER);
        int false1Index = cursor.getColumnIndex(QuestionDbHelper.COLUMN_FALSE_1);
        int false2Index = cursor.getColumnIndex(QuestionDbHelper.COLUMN_FALSE_2);
        int false3Index = cursor.getColumnIndex(QuestionDbHelper.COLUMN_FALSE_3);

        long id = cursor.getLong(idIndex);
        String questionText = cursor.getString(questionIndex);
        String answer = cursor.getString(answerIndex);
        String false1 = cursor.getString(false1Index);
        String false2 = cursor.getString(false2Index);
        String false3 = cursor.getString(false3Index);

        return new QuestionObject(id, questionText, answer, false1, false2, false3);
    }

}
