package com.thevapequeen.vapequeenkiosk.kioskservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
public class OnScreenOffReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {

        }
    }

    private void wakeUpDevice(AppContext context) {
        PowerManager.WakeLock wakeLock = context.getWakeLock(); // get WakeLock reference via AppContext
        if (wakeLock.isHeld()) {
            wakeLock.release(); // release old wake lock
        }

        // create a new wake lock...
        wakeLock.acquire();

        // ... and release again
        wakeLock.release();
    }
}
