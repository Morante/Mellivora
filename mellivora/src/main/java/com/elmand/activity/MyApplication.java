package com.elmand.activity;

import android.app.Application;
import android.content.Context;
import android.util.SparseArray;

import com.elmand.handler.CrashHandler;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

/**
 * Function: 我的应用类
 * author: elmand6 on  2016/1/29 17:22.
 * email：lysa8156@sina.com
 */
public class MyApplication extends Application {
    private static Context context;

    public static Context getAppContext() {
        return MyApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        MobclickAgent.openActivityDurationTrack(false);

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
