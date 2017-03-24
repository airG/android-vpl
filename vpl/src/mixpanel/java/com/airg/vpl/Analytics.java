package com.airg.vpl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

/**
 * MixPanel Analytics
 * Created by MahramF.
 */

@SuppressWarnings("unused")
class Analytics extends VPLAnalytics {

    private final MixpanelAPI mixPanel;

    Analytics(@NonNull final Context ctx, @NonNull final String token) {
        super(ctx, token);
        mixPanel = MixpanelAPI.getInstance(ctx, token);
    }

    @Override
    void trackVPLLaunch() {
        mixPanel.track(EVENT_VPL_LAUNCH);
        mixPanel.flush();
    }

    @Override
    void trackVPLUpgrade() {
        mixPanel.track(EVENT_UPGRADE);
        mixPanel.flush();
    }
}
