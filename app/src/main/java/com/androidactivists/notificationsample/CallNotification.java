package com.androidactivists.notificationsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CallNotification extends ActionBarActivity {
    private Button button;
    private EditText inputview;
    public String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button= (Button) findViewById(R.id.button);
        inputview= (EditText) findViewById(R.id.inputView);
        inputview.setGravity(Gravity.CENTER);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               input=inputview.getText().toString();

                if(input.length()==0){

                    toastIt();

                }
                else if(input.length()!=0){

                    createNotification();
                    StartService();
                }

            }
        });
    }

    private void toastIt() {
        Toast.makeText(this,"Dude you gotta enter something first",Toast.LENGTH_SHORT).show();
    }

    private void StartService() {

        Intent intent = new Intent(this,BackgroundService.class);
        startService(intent);
    }

    private void createNotification() {


        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // intent triggered, you can add other intent for other actions
        Intent intent = new Intent(this,NotificationReciever.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,0);


        Notification mNotification = new NotificationCompat.Builder(this)

                .setContentTitle("Sample Notification!")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_action_star)
                .setTicker("Dummy Notification")
                .setContentIntent(pIntent)
                .setSound(soundUri)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);



    }


}
