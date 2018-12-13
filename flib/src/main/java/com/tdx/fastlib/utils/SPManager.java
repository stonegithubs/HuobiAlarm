package com.tdx.fastlib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tongdexin on 2018/5/5.
 **/

public class SPManager {

    private static final String SP_NAME = "mathtest";

    private static final String SP_KEY_COUNT_DOWN_LOGIN = "countdown_login";
    private static final String SP_KEY_COUNT_DOWN_SIGN = "countdown_sign";

    private static final String SP_KEY_TOKEN = "token";
    private static final String SP_MATH_DATA = "math_data";

    public static void saveCodeCountDownTimeLogin(Context context, long time) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(SP_KEY_COUNT_DOWN_LOGIN, time);
        editor.apply();
    }

    public static long getCodeCountDownTimeLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        long time = sp.getLong(SP_KEY_COUNT_DOWN_LOGIN, 0);
        return time;
    }

    public static void saveCodeCountDownTimeSign(Context context, long time) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(SP_KEY_COUNT_DOWN_SIGN, time);
        editor.apply();
    }

    public static long getCodeCountDownTimeSign(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        long time = sp.getLong(SP_KEY_COUNT_DOWN_SIGN, 0);
        return time;
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_KEY_TOKEN, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(SP_KEY_TOKEN, "");
    }

    public static void saveMathData(Context context, String data) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_MATH_DATA, data);
        editor.apply();
    }

    public static String getMathData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(SP_MATH_DATA, "");
    }
}
