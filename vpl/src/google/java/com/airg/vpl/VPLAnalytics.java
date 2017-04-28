package com.airg.vpl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


/**
 * Google VPLAnalytics
 * Created by MahramF.
 */

@SuppressWarnings("unused")
class VPLAnalytics implements VPLAnalyticsMethods {

    private final GoogleAnalytics ga;
    private final Tracker tracker;

    VPLAnalytics(@NonNull final Context ctx, final String token) {
        //noinspection MissingPermission
        ga = GoogleAnalytics.getInstance(ctx);
        tracker = ga.newTracker(token);
    }

    private void trackWithAction(final String action) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("VPL")
                .setAction(action)
                .build());
    }

    @Override
    public void trackVPLLaunch() {
        trackWithAction(EVENT_VPL_LAUNCH);
    }

    @Override
    public void trackVPLUpgrade() {
        trackWithAction(EVENT_UPGRADE);
    }
}
