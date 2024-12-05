package com.example.appgo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLocalizacion extends SQLiteOpenHelper {

    public SqlLocalizacion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ubicaciones (id INTEGER PRIMARY KEY, calle TEXT, latitud REAL, longitud REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ubicaciones");
        onCreate(db);
    }
}
