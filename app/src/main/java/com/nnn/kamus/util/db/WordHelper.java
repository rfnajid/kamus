package com.nnn.kamus.util.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.nnn.kamus.model.Word;
import com.nnn.kamus.util.S;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ridhaaaaazis on 09/02/18.
 */

public class WordHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public WordHelper(Context context) {
        this.context = context;
    }

    public WordHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public void begin(){
        database.beginTransaction();
    }

    public void success(){
        database.setTransactionSuccessful();
    }

    public void end(){
        database.endTransaction();
    }


    public ArrayList<Word> search(String language,String query){
        S.log("search : "+language +" : "+query);
        String result = "";
        //Cursor cursor = database.query(language,null,DatabaseContract.DictionaryColumns.ORIGIN+" LIKE ?",new String[]{query},null,null,null,null);
        String q = "select * from "+language+" where origin like '"+query+"%'";
        Cursor cursor = database.rawQuery(q,null);
        //Cursor cursor = database.rawQuery(q,null);
        cursor.moveToFirst();
        ArrayList<Word> arrayList = new ArrayList<>();
        Word Word;
        if (cursor.getCount()>0) {
            do {
                Word = new Word();
                Word.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DictionaryColumns._ID)));
                Word.setOrigin(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DictionaryColumns.ORIGIN)));
                Word.setMeaning(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DictionaryColumns.MEANING)));

                arrayList.add(Word);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        S.log("result : "+arrayList.size());
        return arrayList;
    }

    public void insert(String language,Word word){
        String sql = "INSERT INTO "+language+" ("+DatabaseContract.DictionaryColumns.ORIGIN+", "+ DatabaseContract.DictionaryColumns.MEANING
                +") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, word.getOrigin());
        stmt.bindString(2, word.getMeaning());
        stmt.execute();
        stmt.clearBindings();
    }

    public void bulkInsert(String language,List<Word> wordList){
        begin();


        try {
            for (Word word : wordList) {
                insert(language,word);
            }
            // Jika semua proses telah di set success maka akan di commit ke database
            success();

        } catch (Exception e) {
            // Jika gagal maka do nothing
            S.log("Bulk Insert gagal");
        }

        end();
    }

}

