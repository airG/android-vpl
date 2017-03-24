package com.airg.vpl;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by MahramF.
 */

@SuppressWarnings({"unused", "WeakerAccess"})
abstract class VPLAnalytics {

    protected final Context context;
    protected final String serviceToken;

    static final String EVENT_VPL_LAUNCH = "VPL Launch";
    static final String EVENT_UPGRADE = "VPL Upgraded";

    VPLAnalytics(@NonNull final Context ctx, @NonNull final String token) {
        context = ctx;
        serviceToken = token;
    }

    /**
     * VPL was launched
     */
    abstract void trackVPLLaunch();

    /**
     * VPL was upgraded to actual application
     */
    abstract void trackVPLUpgrade();

}
