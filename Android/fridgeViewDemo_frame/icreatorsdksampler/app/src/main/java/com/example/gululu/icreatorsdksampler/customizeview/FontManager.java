package com.example.gululu.icreatorsdksampler.customizeview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Haku Hak on 2015/12/18.
 */
public final class FontManager {
    private static String mFont;

    public FontManager(String font) {
        mFont = font;
       // mContext=context;
    }

    public static void changeFonts(ViewGroup root, Context context) {

        Typeface tf = Typeface.createFromAsset(context.getResources().getAssets(), mFont);

        for (int i = 0; i < root.getChildCount(); i++) {

            View v = root.getChildAt(i);

            if (v instanceof TextView) {

                ((TextView) v).setTypeface(tf);

            } else if (v instanceof Button) {

                ((Button) v).setTypeface(tf);

            } else if (v instanceof EditText) {

                ((EditText) v).setTypeface(tf);

            } else if (v instanceof ViewGroup) {

                changeFonts((ViewGroup) v, context);

            }

        }

    }

    public static HashMap<String, Typeface> TypefaceMap = new HashMap<String, Typeface>();

    public static Typeface getTypefaceByFontName(Context context,String name){
        if (TypefaceMap.containsKey(name)) {
            return TypefaceMap.get(name);
        } else {
            Typeface tf = Typeface.createFromAsset(context.getResources().getAssets(), name);
            TypefaceMap.put(name, tf);
            return tf;
        }
    }
}
