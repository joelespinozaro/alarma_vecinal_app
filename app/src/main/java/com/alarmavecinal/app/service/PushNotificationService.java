package com.alarmavecinal.app.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent; //add
import android.app.TaskStackBuilder; //add
import android.content.Intent; //add

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.alarmavecinal.app.MessageAlerta; //add
import com.alarmavecinal.app.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {

    private PendingIntent pendingIntent; //add
    @SuppressLint("NewApi")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String text = remoteMessage.getNotification().getBody();
        String CHANEL_ID = "MESSAGE";
        CharSequence name;
        setPendingIntent(MessageAlerta.class, title, text);//add
        NotificationChannel channel = new NotificationChannel(CHANEL_ID,"Message Notification", NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this,CHANEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true).setContentIntent(pendingIntent); //modify
        NotificationManagerCompat.from(this).notify(1,notification.build());
        super.onMessageReceived(remoteMessage);
    }

    private void setPendingIntent(Class<?> activity, String title, String text) { //add
        Intent intent = new Intent(this, activity);
        intent.putExtra("dato1", title.toString());
        intent.putExtra("dato2", text.toString());
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(activity);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
