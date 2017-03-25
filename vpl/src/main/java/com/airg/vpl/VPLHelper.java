package com.airg.vpl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by MahramF.
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public final class VPLHelper {

    private static final String TAG = "VPL";
    private static final String PREFERENCES_FILE = "com.airg.vpl";

    private static final String PREFERENCE_VPL_LAUNCH = "vpl_launch";

    private final Context context;
    private final VPLAnalytics analytics;
    private final SharedPreferences preferences;

    private VPLHelper(@NonNull final Context ctx, @NonNull final String analyticsToken) {
        context = ctx;
        analytics = new Analytics(ctx, analyticsToken);
        preferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    private void vplLaunch() throws VPLAppStoreLaunchException {
        final boolean vplWasHere = preferences.getBoolean(PREFERENCE_VPL_LAUNCH, false);

        if (!vplWasHere) {
            Log.d(TAG, "First VPL Launch");
            preferences.edit().putBoolean(PREFERENCE_VPL_LAUNCH, true).apply();
        } else {
            Log.d(TAG, "VPL Launch");
        }

        try {
            analytics.trackVPLLaunch();
        } catch (Exception ignored) {
        }

        launchPlayStore();
    }

    private void vplUpgrade() {
        final boolean vplWasHere = preferences.getBoolean(PREFERENCE_VPL_LAUNCH, false);

        if (!vplWasHere) {
            Log.d(TAG, "Not a VPL upgrade");
            return; // not upgraded from VPL
        }

        preferences.edit().clear().apply(); // future updates don't need to track this

        try {
            Log.d(TAG, "Upgraded from VPL");
            analytics.trackVPLUpgrade();
        } catch (Exception ignored) {
        }
    }

    private void launchPlayStore() throws VPLAppStoreLaunchException {
        final String marketUri = "market://details?id=" + context.getPackageName();
        Log.d(TAG, "Launching " + marketUri);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(marketUri));

        if (null == intent.resolveActivity(context.getPackageManager()))
            throw new VPLAppStoreLaunchException("Unable to launch Google Play Store");

        context.startActivity(intent);
    }

    /**
     * The VPL application has launched. Call from the {@link android.app.Activity#onCreate(Bundle)} of your VPL
     * Launcher activity.
     *
     * @param context        context
     * @param analyticsToken Analytics service token identifying your project
     */
    public static void onVPLLaunched(@NonNull final Context context,
                                     @NonNull final String analyticsToken)
            throws VPLAppStoreLaunchException {
        new VPLHelper(context, analyticsToken).vplLaunch();
    }

    /**
     * The actual application has launched. This method does nothing if {@link #onVPLLaunched(Context, String)} has been called
     * on this device before. Otherwise reports a VPL upgrade to Analytics. Call from a
     * {@link android.content.BroadcastReceiver} listening on action
     * {@link android.content.Intent#ACTION_MY_PACKAGE_REPLACED} (runs every time your package is updated) or from
     * {@link Application#onCreate()} (runs every time your app is launched).
     *
     * @param context        context
     * @param analyticsToken Analytics service token identifying your project
     */
    public static void onApplicationLaunched(@NonNull final Context context,
                                             @NonNull final String analyticsToken) {
        new VPLHelper(context, analyticsToken).vplUpgrade();
    }

    /**
     * The library was unable to launch an App Store intent to install the actual app (e.g. No Google Play Store on device).
     */
    public static class VPLAppStoreLaunchException extends Exception {
        public VPLAppStoreLaunchException(final String message) {
            super(message);
        }
    }
}
