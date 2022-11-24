package com.alarmavecinal.app.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent; //add
import android.app.TaskStackBuilder; //add
import android.content.Context;
import android.content.Intent; //add
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alarmavecinal.app.MessageAlerta; //add
import com.alarmavecinal.app.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class PushNotificationService extends FirebaseMessagingService {
    private static final String TAG="FirebaseMessagingService";

    @SuppressLint("NewApi")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        /*Verifica si el mensaje contiene un dato payload*/
        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Message data payload: " + remoteMessage.getData());
            try{
                JSONObject data = new JSONObject(remoteMessage.getData());
                String jsonMessage = data.getString("extra_information");
                Log.d(TAG,"onMessageReceived: \n" +
                        "Extra Information: " + jsonMessage);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        //Verifica que el mensaje contiene una notificacion para cargar
        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            String click_action = remoteMessage.getNotification().getClickAction();
            String a = remoteMessage.getNotification().getChannelId();

            CharSequence name;
            Log.d(TAG,"Message Notificacion Title: " + title);
            Log.d(TAG,"Message Notificacion Body: " + message);
            Log.d(TAG,"Message Notificacion click_action: " + click_action);
            Log.d(TAG,"Message Notificacion channel_id: " + a);
            sendNotification(title,message,click_action);
        }
    }
    private void sendNotification(String title,String messageBody, String click_action){
        String CHANEL_ID = "MESSAGE";
        Intent intent = null;
        if(click_action.equals("com.alarmavecinal.app_TARGET_NOTIFICATION")){
            intent = new Intent(this,MessageAlerta.class);
            intent.putExtra("title",title);
            intent.putExtra("message",messageBody);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationChannel channel = new NotificationChannel(CHANEL_ID, "Message Notification", NotificationManager.IMPORTANCE_HIGH);
        Log.d(TAG,channel.getId());
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,CHANEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                //.setChannelId(CHANEL_ID)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
