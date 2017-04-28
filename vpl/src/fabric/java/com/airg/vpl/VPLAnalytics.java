package com.airg.vpl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import io.fabric.sdk.android.Fabric;

/**
 * MixPanel VPLAnalytics
 * Created by MahramF.
 */

@SuppressWarnings("unused")
class VPLAnalytics implements VPLAnalyticsMethods {

    private final Answers answers;

    VPLAnalytics(@NonNull final Context ctx, @Nullable final String token) {
        Fabric.with(ctx, new Answers());
        answers = Answers.getInstance();
    }

    @Override
    public void trackVPLLaunch() {
        answers.logCustom(new CustomEvent(EVENT_VPL_LAUNCH));
    }

    @Override
    public void trackVPLUpgrade() {
        answers.logCustom(new CustomEvent(EVENT_UPGRADE));
    }
}
