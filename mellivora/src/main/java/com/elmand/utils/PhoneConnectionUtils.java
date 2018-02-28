package com.elmand.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * 功能：手机连接网络状态工具类
 * author: elmand6 on 2015/12/15 17:21
 * email：lysa8156@sina.com
 */
public class PhoneConnectionUtils {
    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断网络连接的类型(
     *
     * @param context
     * @return
     */
    public static String getNetworkType(Context context) {
        ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String typeName = info.getTypeName().toLowerCase(); // WIFI/MOBILE
            if (typeName.equals("wifi")) {
                typeName = "wifi";
            } else if (TelephonyManager.NETWORK_TYPE_LTE == info.getSubtype()) {
                typeName = "4G网络";
            } else {
                    typeName = info.getExtraInfo().toLowerCase(); // 3gnet/3gwap/uninet/uniwap/cmnet/cmwap/ctnet/ctwap
            }
            return typeName;
        } else {
            return "wifi not available";
        }
    }

    /**
     * 返回WifiInfo 信息
     *
     * @param context
     * @return
     */
    public static WifiInfo getWifiStatus(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo;
    }


    /**
     * 打开设置界面,用户设置网络连接
     *
     * @param activity
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        activity.startActivity(intent);
    }
}
