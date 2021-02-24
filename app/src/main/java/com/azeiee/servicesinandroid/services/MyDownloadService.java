package com.azeiee.servicesinandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.azeiee.servicesinandroid.DownloadHandler;
import com.azeiee.servicesinandroid.DownloadThread;
import com.azeiee.servicesinandroid.MainActivity;

public class MyDownloadService extends Service {
    public static final String TAG= "MyTag";
    private DownloadThread mDownloadTread;
    public MyDownloadService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: called");
        mDownloadTread = new DownloadThread();

        // untill mHanler will not initialise it will move in this loop
        while (mDownloadTread.mHandler == null){

        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        //null will saying that this is startetService
        Log.d(TAG, "onBind: called");
       return null;
    }


// intent from main activity will be recieve here
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: called");
        String songName = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        Message msg = new Message();
        msg.obj=songName;
        mDownloadTread.mHandler.sendMessage(msg);

        return START_REDELIVER_INTENT;
    }

    private void downloadSong(final String songName) {
        Log.d(TAG, "run: staring download");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSong: "+songName+"downloaded");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: called");
        super.onDestroy();
    }
}