package com.bnu.zhuyongchun.poetry.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by ranzi on 2017/6/6.
 */
public final class CustomFontTextView {
    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName); replaceFont(staticTypefaceFieldName, regular);
    }
    protected static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class .getDeclaredField(staticTypefaceFieldName); staticField.setAccessible(true);
            staticField.set(null, newTypeface); }
        catch (NoSuchFieldException | IllegalAccessException e) { e.printStackTrace(); } }}
