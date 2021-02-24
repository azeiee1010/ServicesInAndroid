package com.azeiee.servicesinandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
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
        final String songName = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        MyDownloadTask myDownloadTask = new MyDownloadTask();
        myDownloadTask.execute(songName);

        //this will tells that what will happen if application destroy during service running
        /**
         * START_STICKY   this will resume the service but we will not again get the intent.
         * START_NOT_STICKY  this will niether resume service nor the intent
         * START_REDELIVER_INTENT  this will resume both
         */
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

    class MyDownloadTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... songs) {
            for (String song:songs) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(song);
            }
            return "All songs have been downloaded";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d(TAG, "onProgressUpdate: song download" + values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: result is "+ s);
        }
    }
}