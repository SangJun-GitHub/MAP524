package com.example.todolistdb.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;
    public static final String ID = "_id";
    public static final String TABLE_NAME = "items";
    public static final String DESCRIPTION = "description";
    public static final String COMPLETED = "completed";

    private static DatabaseHelper instance = null;

    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "description TEXT NOT NULL, " +
                "completed INTEGER NOT NULL DEFAULT 0)";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
