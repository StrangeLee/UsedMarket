package com.wedontanything.usedmarket.Activity;

import android.content.Context;
import android.widget.Toast;

public class Basic {

    public void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
