package com.elmand.utils;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.elmand.activity.MyApplication;
import com.elmand.toolpackage.R;

/**
 * 功能：Toast管理类
 * author: elmand6 on 2015/12/14 17:19
 * email：lysa8156@sina.com
 */
public class ToastUtils {

    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    private ToastUtils() {
         /* cannot be instantiated  不能被实例化*/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 获取Toast 是否显示的，true为显示，false不显示
     *
     * @return
     */
    public static boolean isShow() {
        return isShow;
    }

    /**
     * 设置Toast 是否可以显示
     *
     * @param isShow
     */
    public static void setIsShow(boolean isShow) {
        ToastUtils.isShow = isShow;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (isShow)
            show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (isShow)
            show(message, Toast.LENGTH_LONG);
    }


    /**
     * Toast 多次点击只显示最后一次
     *
     * @param message
     * @param duration Toast 的间隔时间
     */
    private static void show(CharSequence message, int duration) {
        View view = View.inflate(MyApplication.getAppContext(), R.layout.toast_layout, null);
        TextView tv_toast_text = (TextView) view.findViewById(R.id.tv_toast_text);
        if (toast == null) {
            toast = new Toast(MyApplication.getAppContext());
            tv_toast_text.setText(message);
            toast.setView(view);
            toast.setDuration(duration);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > duration) {
                    toast.show();
                }
            } else {
                oldMsg = (String) message;
                tv_toast_text.setText(message);
                toast.setView(view);
                toast.setDuration(duration);
                toast.show();
            }
        }
    }
}
