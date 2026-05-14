package com.example.sqliteetudiants.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int HC_DB_VERSION = 1;
    private static final String HC_DB_NAME = "hc_ecole";

    private static final String HC_CREATE_TABLE =
            "CREATE TABLE etudiant(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT," +
                    "prenom TEXT)";

    public MySQLiteHelper(Context context) {
        super(context, HC_DB_NAME, null, HC_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HC_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS etudiant");
        this.onCreate(db);
    }
}