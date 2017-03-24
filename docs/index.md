 [ ![Download](https://api.bintray.com/packages/airgoss/airGOss/vpl/images/download.svg) ](https://bintray.com/airgoss/airGOss/vpl/_latestVersion)

# Android VPL
A VPL Helper library. A Virtual PreLoad (VPL) is a tiny APK that serves a single purpose: to launch the Play Store (or another app store) with your __actual__ app. This helps with preloading your app on devices as it takes much less space in the user's precious `/system` directory.

# VPL
From your VPL app, call `VPLHelper.onVPLLaunched (context, analyticsToken)` to register a VPL launch. `VPLHelper` takes care of launching the Play Store. The 'VPL Launched' even is reported every time the VPL is launched regardless of whether the user installs your full APK or not.

# Actual APK
There is more than one way to record an upgrade: Via a `BroadcastReceiver`, through your launcher activity's `onCreate(Bundle)` method, and from your Application's `onCreate` method.
## BroadcastReceiver
Using a `BroadcastReceiver` is the most efficient way as the code only runs once when your application is updated on the device. This method does not require the user to ever launch your application. Create a `BroadcastReceiver` extending `VPLUpgradeReceiver` and declare it in your manifest. Use the following intent filter:

    <receiver
      android:name="com.airg.vpl.sample.UpgradeReceiver"
      android:enabled="true"
      android:exported="true">
        <intent-filter>
          <action android:name="android.intent.action.MY_PACKAGE_REPLACED" />
        </intent-filter>
    </receiver>
 
## On Activity Create
The next best method is to call `VPLHelper.onApplicationLaunched (Context, String)` in your launcher Activity's onCreate() method. This is less efficient as it is

1. Executed every time your application is launched (as opposed to only on update)
2. Requires the application to be launched at least once after update.

## On Application Create
The least efficient method is to call `VPLHelper.onApplicationLaunched (Context, String)` in your Application's onCreate() method. This is less efficient than the previous method because aside from the points above, it is also launched every time a component of your application is activated (e.g. you receive a push message).

Regardless of which method you use and how many times you call the methods, the upgrade event is only reported once.

# Usage
To use the _android-vpl_ library in your product, add the following line to your Gradle build script:

`compile 'com.airg.android:vpl:+@aar'`

Or download the library from the download link at the top of this page.

## Contributions
Please refer to the [contribution instructions](https://airg.github.io/#contribute).
