package com.elmand.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;

/**
 * fragment 封装基类
 * author: elmand6 on 2015/12/16 15:33
 * email：lysa8156@sina.com
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            getSaveData(savedInstanceState);
        }
        View view = setContentView(inflater, container, savedInstanceState);
        findViews(view);
        setListener();
        processLogic();
        return view;
    }

    protected abstract void getSaveData(Bundle savedInstanceState);

    protected abstract View setContentView(LayoutInflater inflater,
                                           ViewGroup container, Bundle savedInstanceState);

    protected abstract void findViews(View view);

    protected abstract void setListener();

    protected abstract void processLogic();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            // 参数是固定写法
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
}
