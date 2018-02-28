package com.elmand.utils;

/**
 * Function: 其他工具类
 * author: elmand6 on  2016/3/13 18:36.
 * email：lysa8156@sina.com
 */
public class FunctionUtils {

    private FunctionUtils() {
            /* cannot be instantiated  不能被实例化*/
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    private static long lastClickTime;

    /**
     * 判断是否是连续多次点击
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        return isFastClick(1000);
    }

    /**
     * 判断是否是连续多次点击
     *
     * @param duration 单位毫秒值
     * @return
     */
    public synchronized static boolean isFastClick(int duration) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < duration) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
