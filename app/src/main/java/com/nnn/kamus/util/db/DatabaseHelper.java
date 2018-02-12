package com.nnn.kamus.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nnn.kamus.model.Word;

import java.util.ArrayList;


/**
 * Created by sidiqpermana on 11/23/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_kamus";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_ENGLISH = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)" ,
            DatabaseContract.TABLE_ENGLISH,
            DatabaseContract.DictionaryColumns._ID,
            DatabaseContract.DictionaryColumns.ORIGIN,
            DatabaseContract.DictionaryColumns.MEANING
    );

    private static final String SQL_CREATE_TABLE_INDO = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)" ,
            DatabaseContract.TABLE_INDO,
            DatabaseContract.DictionaryColumns._ID,
            DatabaseContract.DictionaryColumns.ORIGIN,
            DatabaseContract.DictionaryColumns.MEANING
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ENGLISH);
        db.execSQL(SQL_CREATE_TABLE_INDO);
    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_ENGLISH+","+DatabaseContract.TABLE_INDO);
        onCreate(db);
    }
}
