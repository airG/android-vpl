package com.airg.vpl.sample;

import com.airg.vpl.receiver.VPLUpgradeReceiver;

/**
 * Created by MahramF.
 */

public final class UpgradeReceiver extends VPLUpgradeReceiver {
    @Override
    protected String getAnalyticsToken() {
        return BuildConfig.TOKEN;
    }
}
