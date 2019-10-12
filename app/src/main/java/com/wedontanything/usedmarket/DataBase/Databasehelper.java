package com.wedontanything.usedmarket.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wedontanything.usedmarket.User.Token;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wedont.db";

    private static volatile Databasehelper INSTANCE;

    private Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static Databasehelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (Databasehelper.class) {
                if(INSTANCE == null)
                    INSTANCE = new Databasehelper(context.getApplicationContext());
            }
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseManager.getCreateTableToken());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager.TABLE_TOKEN);
        onCreate(db);
    }

    public void insert(String tableName, String insertValue) {
        final SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("token", insertValue);
       // contentValues.put();

        db.insert(tableName, null, contentValues);
    }

    Token getToken() {
        final SQLiteDatabase db = this.getReadableDatabase();

        final Cursor res = db.rawQuery("SELECT * FROM token ORDER BY idx DESC limit 1", null);

        Token token = new Token();

        while (res.moveToNext()) {
            token.setToken(res.getString(res.getColumnIndex("token")));
        }

        res.close();

        return token;
    }

}
