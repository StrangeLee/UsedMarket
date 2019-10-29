package com.wedontanything.usedmarket.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    static final String ID = "id";
    static final String PASSWORD = "passWord";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    // 계정 정보 저장
    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(ID, userName);
        editor.putString(PASSWORD, userName);
        editor.commit();
    }

    // 저장된 정보 가져오기
    public static String getLoginId(Context ctx) {
        return getSharedPreferences(ctx).getString(ID, "");
    }

    public static String getLoginPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PASSWORD, "");
    }

    // 로그아웃
    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}