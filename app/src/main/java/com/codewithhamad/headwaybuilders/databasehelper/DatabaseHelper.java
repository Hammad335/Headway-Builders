package com.codewithhamad.headwaybuilders.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME= "headway.db";
    private static final int DB_VERSION= 1;
//    private int increment= 0;
//    private  String tableName= "building";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery= "CREATE TABLE buildings (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, " +
                "buildingAreaInSqFt INTEGER, numberOfFlats INTEGER, parkingAreaInSqFt INTEGER, details VARCHAR, " +
                "buildingImage INTEGER)";

        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
