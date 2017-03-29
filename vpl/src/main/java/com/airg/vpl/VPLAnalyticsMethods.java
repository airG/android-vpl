package com.airg.vpl;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by MahramF.
 */

@SuppressWarnings({"unused", "WeakerAccess"})
interface VPLAnalyticsMethods {

    String EVENT_VPL_LAUNCH = "VPL Launch";
    String EVENT_UPGRADE = "VPL Upgraded";

    /**
     * VPL was launched
     */
    abstract void trackVPLLaunch();

    /**
     * VPL was upgraded to actual application
     */
    abstract void trackVPLUpgrade();

}
