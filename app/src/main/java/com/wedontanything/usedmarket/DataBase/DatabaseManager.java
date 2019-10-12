package com.wedontanything.usedmarket.DataBase;

public final class DatabaseManager {
    public static final String TABLE_TOKEN = "token";

    private DatabaseManager() {

    }

    static String getCreateTableToken() {
        return "CREATE TABLE " + TABLE_TOKEN + " ( "
                + "idx INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "token String, "
                + ");";
    }
}
