package com.azeiee.servicesinandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.azeiee.servicesinandroid.MainActivity;

public class MyDownloadService extends Service {
    public static final String TAG= "MyTag";
    public MyDownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //null will saying that this is startetService
       return null;
    }


// intent from main activity will be recieve here
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String songName = intent.getStringExtra(MainActivity.MESSAGE_KEY);
        downloadSong(songName);
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
}