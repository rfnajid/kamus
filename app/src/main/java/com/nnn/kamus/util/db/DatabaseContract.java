package com.nnn.kamus.util.db;

import android.provider.BaseColumns;

/**
 * Created by dicoding on 10/12/2017.
 */

public class DatabaseContract {

    public static String TABLE_ENGLISH = "en";

    public static final class DictionaryColumns implements BaseColumns {

        //Note title
        static String ORIGIN = "origin";
        //Note description
        static String MEANING = "meaning";

    }

    public static String TABLE_INDO = "id";
}
