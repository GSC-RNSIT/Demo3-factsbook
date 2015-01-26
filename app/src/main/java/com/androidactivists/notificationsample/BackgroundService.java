package com.androidactivists.notificationsample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BackgroundService extends Service {

    final String TAG ="BackGroundService";


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service Started");
        Toast.makeText(this,"Demo background service",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Service Killed !!");
    }

}
