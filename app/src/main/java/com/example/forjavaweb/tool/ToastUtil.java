package com.example.forjavaweb.tool;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void sendToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
