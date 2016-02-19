package com.example.gululu.icreatorsdksampler.customizeview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 1 on 2015/12/17.
 */
public class TitleTextView extends TextView{
    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/pump.ttf"));
    }
}
