package br.com.youse.redditfeed.utils;

import android.app.AlertDialog;
import android.content.Context;

public class Utility {

    public static AlertDialog.Builder dialog;

    public static void alertSimpleDialog(final Context context, final String title, final String message) {
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNeutralButton("OK", null);
        dialog.show();
    }

    public static boolean isConnected(Context context) {
        if (ConnectivityChecker.isConnectedFast(context)) {
            return true;
        } else {
            return false;
        }
    }
}
