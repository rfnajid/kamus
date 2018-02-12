package com.nnn.kamus.util.db;

import android.content.Context;
import android.content.res.Resources;

import com.nnn.kamus.R;
import com.nnn.kamus.model.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ridhaaaaazis on 09/02/18.
 */

public class Preloader {
    public static ArrayList<Word> preloadRaw(Context context, String filename) {
        ArrayList<Word> Words = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = context.getResources();
            InputStream raw_dict = res.openRawResource(
                    context.getResources().getIdentifier(filename,
                            "raw", context.getPackageName())

            );

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                Word Word;

                Word = new Word(splitstr[0], splitstr[1]);
                Words.add(Word);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Words;
    }
}
