package com.airg.vpl.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.airg.vpl.VPLHelper;

/**
 * Extend this receiver to be informed asynchronously when your app has been upgraded.
 */
public abstract class VPLUpgradeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d("VPL", "app upgraded");
        VPLHelper.onApplicationLaunched(context, getAnalyticsToken());
    }

    protected abstract String getAnalyticsToken();

}
