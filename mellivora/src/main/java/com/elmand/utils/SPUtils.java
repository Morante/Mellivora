package com.elmand.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.elmand.activity.MyApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 功能:SP工具管理类
 * author: elmand6 on 2015/12/14 17:28
 * email：lysa8156@sina.com
 */
public class SPUtils {

    private static SharedPreferences sp;
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "config";

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        if (sp == null) {
            sp = MyApplication.getAppContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(String key, Object defaultObject) {
        if (sp == null) {
            sp = MyApplication.getAppContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {

        if (sp == null) {
            sp = MyApplication.getAppContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        if (sp == null) {
            sp = MyApplication.getAppContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains( String key) {
        if (sp == null) {
            sp = MyApplication.getAppContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        if (sp == null) {
            sp = MyApplication.getAppContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        }
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     * 因为commit方法是同步的，并且我们很多时候的commit操作都是UI线程中，毕竟是IO操作，尽可能异步；
     * 所以我们使用apply进行替代，apply异步的进行写入；
     * 但是apply相当于commit来说是new API呢，为了更好的兼容，我们做了适配；
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }
}
