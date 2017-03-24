package com.airg.vpl.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.airg.vpl.VPLHelper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            VPLHelper.onVPLLaunched(this, BuildConfig.TOKEN);
        } catch (VPLHelper.VPLAppStoreLaunchException e) {
            Log.e("VPL", "Cannot launch", e);
        }

        finish();
    }
}
