package com.wedontanything.usedmarket.DataBase;

import android.content.Context;

import com.wedontanything.usedmarket.User.Token;

public class TokenManager {
    private static TokenManager INSTANCE;
    private Databasehelper helper;

    private TokenManager(Context context) {
        helper = Databasehelper.getInstance(context);
    }

    public static TokenManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TokenManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TokenManager(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    public Token getToken() {
        return helper.getToken();
    }

    public void setToken(Token token) {
        helper.insert(DatabaseManager.TABLE_TOKEN, token.getToken());
    }

    public void setToken(String tokenString) {
        Token token = new Token();
        token.setToken(tokenString);
        setToken(token);
    }
}
