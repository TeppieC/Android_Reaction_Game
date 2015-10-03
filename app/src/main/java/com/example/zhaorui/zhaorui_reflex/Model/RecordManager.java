package com.example.zhaorui.zhaorui_reflex.Model;

import android.content.Context;

/**
 * Created by zhaorui on 9/30/15.
 */
public class RecordManager{
    protected String fileName;
    protected Context context;

    public RecordManager(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

}


