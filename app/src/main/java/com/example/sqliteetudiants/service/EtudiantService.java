package com.example.sqliteetudiants.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.sqliteetudiants.classes.Etudiant;
import com.example.sqliteetudiants.util.MySQLiteHelper;

public class EtudiantService {

    private static final String HC_TABLE = "etudiant";
    private static final String HC_KEY_ID = "id";
    private static final String HC_KEY_NOM = "nom";
    private static final String HC_KEY_PRENOM = "prenom";
    private static final String[] HC_COLUMNS = {HC_KEY_ID, HC_KEY_NOM, HC_KEY_PRENOM};
    private static final String HC_TAG = "HC_SQLite";

    private final MySQLiteHelper hcHelper;

    public EtudiantService(Context context) {
        this.hcHelper = new MySQLiteHelper(context);
    }

    public void create(Etudiant e) {
        SQLiteDatabase db = hcHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HC_KEY_NOM, e.getNom());
        values.put(HC_KEY_PRENOM, e.getPrenom());
        db.insert(HC_TABLE, null, values);
        Log.d(HC_TAG, "Inséré : " + e.getNom() + " " + e.getPrenom());
        db.close();
    }

    public void update(Etudiant e) {
        SQLiteDatabase db = hcHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HC_KEY_NOM, e.getNom());
        values.put(HC_KEY_PRENOM, e.getPrenom());
        db.update(HC_TABLE, values, "id = ?",
                new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public Etudiant findById(int id) {
        SQLiteDatabase db = hcHelper.getReadableDatabase();
        Cursor c = db.query(HC_TABLE, HC_COLUMNS,
                "id = ?", new String[]{String.valueOf(id)},
                null, null, null, null);

        Etudiant e = null;
        if (c.moveToFirst()) {
            e = new Etudiant();
            e.setId(c.getInt(0));
            e.setNom(c.getString(1));
            e.setPrenom(c.getString(2));
        }
        c.close();
        db.close();
        return e;
    }

    public void delete(Etudiant e) {
        SQLiteDatabase db = hcHelper.getWritableDatabase();
        db.delete(HC_TABLE, "id = ?",
                new String[]{String.valueOf(e.getId())});
        Log.d(HC_TAG, "Supprimé id=" + e.getId());
        db.close();
    }

    public List<Etudiant> findAll() {
        List<Etudiant> list = new ArrayList<>();
        SQLiteDatabase db = hcHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + HC_TABLE, null);

        if (c.moveToFirst()) {
            do {
                Etudiant e = new Etudiant();
                e.setId(c.getInt(0));
                e.setNom(c.getString(1));
                e.setPrenom(c.getString(2));
                list.add(e);
                Log.d(HC_TAG, "id=" + e.getId() + " " + e.getNom() + " " + e.getPrenom());
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
}
