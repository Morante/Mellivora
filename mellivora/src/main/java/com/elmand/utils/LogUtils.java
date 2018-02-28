package com.elmand.utils;

import android.util.Log;

/**
 * 功能：日志输出管理类
 * author: elmand6 on 2015/12/14 16:51
 * email：lysa8156@sina.com
 */
public class LogUtils {

    private LogUtils() {
            /* cannot be instantiated  不能被实例化*/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final String TAG = "funshion";
    private static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }


    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }


    /**
     * 获取是否可以打印Log状态，true为打印，false不打印
     *
     * @return
     */
    public static boolean getIsDEBUG() {
        return isDebug;
    }

    /**
     * 设置是否可以打印Log状态，true为打印，false不打印
     *
     * @return
     */
    public static void setDEBUG(boolean debug) {
        isDebug = debug;
    }


}
