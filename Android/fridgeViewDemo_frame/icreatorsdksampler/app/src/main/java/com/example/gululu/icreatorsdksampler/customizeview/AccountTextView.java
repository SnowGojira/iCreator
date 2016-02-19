package com.example.gululu.icreatorsdksampler.customizeview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Haku Hal on 2015/12/17.
 */
public class AccountTextView extends TextView{
    public AccountTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Infinity.ttf"));
    }
}
