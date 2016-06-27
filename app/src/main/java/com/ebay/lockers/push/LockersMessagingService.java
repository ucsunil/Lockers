package com.ebay.lockers.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.ebay.lockers.R;
import com.ebay.lockers.models.NotificationObject;
import com.ebay.lockers.views.fragments.NotificationsFragment;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sudavid on 6/23/2016.
 */
public class LockersMessagingService extends FirebaseMessagingService {

    private static final String TAG = "LockersNotificationService";
    private static int NOTIFICATION = 1001;

    private NotificationsFragment notificationsFragment;

    @Override
    public void onCreate() {
        super.onCreate();

        notificationsFragment = NotificationsFragment.getInstance();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification() != null) {

            if(remoteMessage.getNotification().getBody().startsWith("/topics/")) {
                Log.d(TAG, "Topics message: " + remoteMessage.getNotification().getBody());
            }
            return;
        }
        Map<String, String> params = remoteMessage.getData();
        createNotification(params);
        addNotificationToFragment(params);
    }

    private void createNotification(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(params.get("user"));

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_add_alert_black_48dp);

        Notification.Action accept;
        Notification.Action counter;
        Notification.Action decline;

        if(Build.VERSION.SDK_INT >= 23) {
            accept = new Notification.Action.Builder(Icon.createWithResource(this, R.drawable.ic_check_black_24dp),
                    getString(R.string.accept), null).build();
            counter = new Notification.Action.Builder(Icon.createWithResource(this, R.drawable.ic_sync_black_24dp),
                    getString(R.string.counter), null).build();
            decline = new Notification.Action.Builder(Icon.createWithResource(this, R.drawable.ic_block_black_24dp),
                    getString(R.string.decline), null).build();
        } else {
            accept = new Notification.Action.Builder(R.drawable.ic_check_black_24dp,
                    getString(R.string.accept), null).build();
            counter = new Notification.Action.Builder(R.drawable.ic_sync_black_24dp,
                    getString(R.string.counter), null).build();
            decline = new Notification.Action.Builder(R.drawable.ic_block_black_24dp,
                    getString(R.string.decline), null).build();
        }

        switch (params.get("transaction")) {
            case "buy":
                sb.append(" has offered you ").append(params.get("offer"))
                        .append(" for your locker item: ").append(params.get("itemInterested"));
                builder.setContentTitle("Buy offer from " + params.get("user"));
                builder.addAction(accept);
                break;

            case "exchange":
                sb.append(" has offered to exchange his item: ").append(params.get("offer"))
                        .append(" for your locker item: ").append(params.get("itemInterested"));
                builder.setContentTitle("Exchange offer from " + params.get("user"));
                builder.addAction(accept);
                break;
        }

        builder.addAction(counter).addAction(decline);
        builder.setContentText(sb.toString()).setPriority(Notification.PRIORITY_HIGH).setDefaults(Notification.DEFAULT_ALL);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION, builder.build());
    }

    private void addNotificationToFragment(Map<String, String> map) {
        List<NotificationObject> list = notificationsFragment.getNotificationsList();
        NotificationObject object = new NotificationObject(map.get("user"), map.get("transaction"),
                                            map.get("offer"), map.get("itemInterested"));
        list.add(object);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                notificationsFragment.getNotificationsAdapter().notifyDataSetChanged();
            }
        });
    }
}
