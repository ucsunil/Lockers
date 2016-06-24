package com.ebay.lockers.push;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by sudavid on 6/23/2016.
 */
public class LockersMessagingService extends FirebaseMessagingService {

    private static final String TAG = "LockersNotificationService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Notification message body: " + remoteMessage.getNotification().getBody());
    }
}
