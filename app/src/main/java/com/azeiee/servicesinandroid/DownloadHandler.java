package com.azeiee.servicesinandroid;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DownloadHandler extends Handler {
    private static final String TAG = "MyTag";
    private final MainActivity mActivity;

    public DownloadHandler(MainActivity activity) {
        this.mActivity=activity;
    }

    @Override
    public void handleMessage(Message msg) {

    }

}
