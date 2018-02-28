package com.elmand.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能 Activity管理类
 * author: elmand6 on 2015/12/16 14:35
 * email：lysa8156@sina.com
 */
public class ActivityStack {
    private List<Activity> activityList = new ArrayList<>();
    private static ActivityStack instance;

    private ActivityStack() {
    }

    public static synchronized ActivityStack getInstance() {
        if (instance == null) {
            instance = new ActivityStack();
        }
        return instance;
    }

    /**
     * 添加 activity在集合的末端
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            if (activity != null && activityList != null) {
                int size = activityList.size();
                if (checkActivityIsExist(activity)) {
                    removeActivity(activity);
                    activityList.add(activityList.size(), activity);
                } else {
                    activityList.add(activity);
                }
                size = activityList.size();
                for (int i = 0; i < size; i++) {
                    LogUtils.i("function", "addActivity ==[" + i + "]" + " "
                            + activityList.get(i));
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 功能 关闭集合中的所有activity,从上往下关闭
     */
    public void finishAllActivity() {

        if (activityList != null) {
            int size = activityList.size();
            for (int i = size - 1; i >= 0; i--) {
                Activity activity = activityList.get(i);
                if (activity != null) {
                    activity.finish();
                }
                LogUtils.i("function", "finishAllActivity ==[" + i + "]" + " "
                        + activity);
                activityList.remove(activity);
            }
        }
    }

    /**
     * 功能 将activity从ActivityList中移除
     *
     * @param activity 要移除的Activity
     */
    public void removeActivity(Activity activity) {
        try {
            if (activityList != null) {
                activityList.remove(activity);
                LogUtils.i("function", "removeActivity==" + " " + activity
                        + "activityList.size===" + activityList.size());
            }
        } catch (Exception e) {
            LogUtils.e("function", "removeActivity" + e.getMessage());
        }
    }

    /**
     * 功能 ，检查activity是否存于ActivityList中
     *
     * @param activity 要判断的Activity
     * @return 是否存在
     */
    public boolean checkActivityIsExist(Activity activity) {
        LogUtils.i("function", " " + activityList.contains(activity));
        return activityList.contains(activity);

    }
}
