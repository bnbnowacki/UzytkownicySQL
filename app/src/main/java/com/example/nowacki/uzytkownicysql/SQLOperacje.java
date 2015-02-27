package com.example.nowacki.uzytkownicysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nowacki on 2015-02-26.
 */
public class SQLOperacje extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public String CREATE_QUERY ="CREATE TABLE " + SQLContract.SQLDane.TABLE_NAME+"("+ SQLContract.SQLDane.COLUMN_NAME_ID +" integer primary key autoincrement, "+
            SQLContract.SQLDane.COLUMN_NAME_NAZWA+" TEXT,"+ SQLContract.SQLDane.COLUMN_NAME_HASLO+
            " TEXT"+");";

    public SQLOperacje(Context context) {
        super(context, SQLContract.SQLDane.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Operacje SQL", "Utworzono bazę danych.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Operacje SQL", "Utworzono tabelę uzytkownicy.");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onInsert(SQLOperacje operacje, String nazwa, String haslo){
        SQLiteDatabase SQ = operacje.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLContract.SQLDane.COLUMN_NAME_NAZWA, nazwa);
        values.put(SQLContract.SQLDane.COLUMN_NAME_HASLO, haslo);
        SQ.insert(SQLContract.SQLDane.TABLE_NAME, null, values);
        Log.d("Operacje SQL", "Dodano nowy rząd SQL.");
    }
}
