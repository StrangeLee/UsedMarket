package com.wedontanything.usedmarket.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class Basic {

    public void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public void showDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title).setMessage(message);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

}
