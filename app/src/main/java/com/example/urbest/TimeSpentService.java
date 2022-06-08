package com.example.urbest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class TimeSpentService extends Service {

    long start, end;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        start = Calendar.getInstance().getTimeInMillis();
        return  START_STICKY;
    }

    @Override
    public void onDestroy() {
        String t = "";
        end = Calendar.getInstance().getTimeInMillis();
        long spentTime = (end-start)/1000;
        if(spentTime >= 60 ){
            long a = spentTime%60;
            spentTime = spentTime/60;
            t = String.valueOf(spentTime) + " min and " + String.valueOf(a) + " seconds.";
        }
        else{
            t = String.valueOf(spentTime) + " seconds.";
        }

        Toast.makeText(getApplicationContext(),"Time spent on the app : " + t , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
