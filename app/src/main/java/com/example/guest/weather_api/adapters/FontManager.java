package com.example.guest.weather_api.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;

import com.example.guest.weather_api.R;

/**
 * Created by Guest on 11/30/16.
 */
public class FontManager {
    public static final String ROOT = "fonts/";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), ROOT + font);
    }

    public static String setIcon(Context context, int id) {
        String result = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(context.getString(id),Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            result = Html.fromHtml(context.getString(id)).toString();
        }
        return result;
    }
}
