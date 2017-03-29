package com.airg.vpl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

/**
 * MixPanel VPLAnalytics
 * Created by MahramF.
 */

@SuppressWarnings("unused")
class VPLAnalytics implements VPLAnalyticsMethods {

    private final MixpanelAPI mixPanel;

    VPLAnalytics(@NonNull final Context ctx, @NonNull final String token) {
        mixPanel = MixpanelAPI.getInstance(ctx, token);
    }

    @Override
    public void trackVPLLaunch() {
        mixPanel.track(EVENT_VPL_LAUNCH);
        mixPanel.flush();
    }

    @Override
    public void trackVPLUpgrade() {
        mixPanel.track(EVENT_UPGRADE);
        mixPanel.flush();
    }
}
