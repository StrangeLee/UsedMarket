package com.wedontanything.usedmarket.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wedont.db";

    private static volatile Databasehelper INSTANCE

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

    public void insert(String tableName, Object insertValue) {
        final SQLiteDatabase db = this.getWritableDatabase();

        for (ContentValues contentValues :
                getContentValuesByInsertValue(insertValue)) {
            db.insert(tableName, null, contentValues);
        }
    }

}
