package com.ebay.lockers.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class LockersInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "LockersInstanceIDService";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);
        saveRegistrationToken(token);
    }

    private void saveRegistrationToken(String token) {

    }
}
