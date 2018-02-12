package com.nnn.kamus.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by ridhaaaaazis on 25/01/18.
 */

public class S {

    public static String TAG="CATALOG";

    private static String API="https://api.themoviedb.org/3/";
    private static String API_KEY="377f89ac1b62ae7925710b6c51986759";

    public static String getAPIUrl(){
        return API;
    }


    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void goTo(Context ctx, Class cls) {
        Intent i = new Intent(ctx, cls);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }

    public static void goTo(Context ctx, Class cls, Serializable s) {
        Intent i = new Intent(ctx, cls);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("extra", s);
        ctx.startActivity(i);
    }

    public static void goTo(Context ctx, Class cls, String s){
        Intent i = new Intent(ctx,cls);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("extra",s);
        ctx.startActivity(i);
    }

    public static void goTo(Context ctx, Class cls, Bundle b) {
        Intent i = new Intent(ctx, cls);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtras(b);
        ctx.startActivity(i);
    }

    public static int getResourceId(Context ctx, String uri){
        int res = ctx.getResources().getIdentifier(uri, null, ctx.getPackageName());
        return res;
    }



    public static boolean isStringNull(String str){
        return (str == null || str.isEmpty());
    }


    public static void log(String message){
        Log.d(TAG,message);
    }
}

