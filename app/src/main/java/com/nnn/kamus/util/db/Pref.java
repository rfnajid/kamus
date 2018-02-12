package com.nnn.kamus.util.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by ridhaaaaazis on 03/02/18.
 */

public class Pref {

    //private static String PREF="pref";

    //public static int DBVERSION=2;

    public static SharedPreferences.Editor getEditor(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx).edit();
    }

    public static SharedPreferences get(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setString(Context ctx, String key, String value){
        SharedPreferences.Editor editor = getEditor(ctx);
        editor.putString(key,value);
        editor.commit();
    }

    public static String getString(Context ctx, String key){
        SharedPreferences pref = get(ctx);
        return pref.getString(key,"");
    }

    public static void setBoolean(Context ctx, String key, boolean value){
        SharedPreferences.Editor editor = getEditor(ctx);
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static boolean getBoolean(Context ctx, String key){
        SharedPreferences pref = get(ctx);
        return pref.getBoolean(key,false);
    }

    public static void setOld(Context ctx){
        setBoolean(ctx,"new",true);
    }

    public static boolean isOld(Context ctx){
        return getBoolean(ctx,"new");
    }

}

