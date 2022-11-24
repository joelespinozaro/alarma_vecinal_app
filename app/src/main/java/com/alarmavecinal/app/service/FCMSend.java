package com.alarmavecinal.app.service;

import android.app.Activity;
import android.content.Context;
import android.os.StrictMode;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FCMSend {
    private static String URL = "https://fcm.googleapis.com/fcm/send";
    private static String SERVER_KEY ="key=AAAAYNykRtE:APA91bH0sDlI8gMvUMav4Ev06fAon8qZyWGYZc1ld9HSxGo98cGIdNTZchDYbt2SCCNOSfDVBAabc07s4Mhp5bKf0oLNP0Jz7cR9c0dloh6UacmFVclZpImiIZ_OHolRovfqL_lHpU9G";

    public static void pushNotification(Context context, String token, String title, String message){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(context);
        try{
            JSONObject json = new JSONObject();
            json.put("to",token);

            JSONObject notification = new JSONObject();
            notification.put("title",title);
            notification.put("body",message);
            notification.put("click_action","com.alarmavecinal.app_TARGET_NOTIFICATION");
            notification.put("channel_id","MESSAGE");
            notification.put("android_channel_id","MESSAGE");

            JSONObject data = new JSONObject();
            data.put("extra_information","This is some extra information");

            json.put("notification",notification);
            json.put("data",data);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("FCM " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Error al ejecutar");
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("Content-Type","application/json");
                    params.put("Authorization",SERVER_KEY);
                    return params;
                }
            };

            queue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
