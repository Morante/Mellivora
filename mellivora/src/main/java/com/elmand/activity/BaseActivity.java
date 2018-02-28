package com.elmand.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.elmand.handler.CrashHandler;
import com.elmand.toolpackage.R;
import com.elmand.utils.ActivityStack;
import com.elmand.utils.FunctionUtils;
import com.elmand.utils.LogUtils;
import com.elmand.utils.PhoneConnectionUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * 功能：activity的底层实现类
 * author: elmand6 on 2015/12/16 15:18
 * email：lysa8156@sina.com
 */
public abstract class BaseActivity extends FragmentActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //可通过Log 查找到具体运行的Activity，看别人代码时使用
        LogUtils.e("BaseActivity", getClass().getSimpleName());
        context = this;
        if (!PhoneConnectionUtils.isNetworkAvailable(this)) {
//TODO
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int resLayoutId = getResLayoutId();
        if (resLayoutId == -1) {
            View view = getResLayoutView();
            if (view == null) {
                throw new RuntimeException(
                        "getResLayoutId返回-1的情况下getResLayoutView不能返回null");
            }
            setContentView(view);
        } else {
            setContentView(resLayoutId);
        }
        ActivityStack.getInstance().addActivity(this);
        getSaveData(savedInstanceState);
        findViews();
        setListener();
        processLogic();
    }

    /**
     * 功能 获取界面所需要的控件
     */
    protected abstract void findViews();

    /**
     * 功能 为控件设置事件监听
     */
    protected abstract void setListener();

    /**
     * 功能 处理业务逻辑
     */
    protected abstract void processLogic();

    /**
     * 功能 获取界面销毁时保存的数据
     *
     * @param savedInstanceState
     */
    protected abstract void getSaveData(Bundle savedInstanceState);

    /**
     * 只有getResLayoutId返回-1该方法才起作用
     *
     * @return
     */
    protected View getResLayoutView() {
        return null;
    }

    /**
     * 如果是XML文件的话在这里返回ID;如果是View对象的话返回-1，要重写getResLayoutView方法
     *
     * @return
     */
    public abstract int getResLayoutId();

    @Override
    protected void onDestroy() {
        ActivityStack.getInstance().removeActivity(this);
        super.onDestroy();
    }

    /**
     * 功能 开启Activity的动画
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        boolean startActAnim = intent.getBooleanExtra("startActAnim", true);
        if (startActAnim) {
            overridePendingTransition(R.anim.tran_start_act_in,
                    R.anim.tran_start_act_out);
        }
    }

    /**
     * 功能 关闭Activity的动画
     */
    @Override
    public void finish() {
        super.finish();
        boolean finishActAnim = this.getIntent().getBooleanExtra(
                "finishActAnim", true);
        if (finishActAnim) {
            overridePendingTransition(R.anim.tran_finish_act_in,
                    R.anim.tran_finish_act_out);
        }
    }

    /**
     * 在AndroidManifest.xml中的Activity添加android:configChanges=
     * "orientation|keyboard|keyboardHidden"属性，表示在改变屏幕方向、弹出软件
     * 盘和隐藏软键盘时，不再去执行onCreate()方法，而是直接执行onConfigurationChanged()
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 功能 开启Activity的动画
     */
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        boolean startActAnim = intent.getBooleanExtra("startActAnim", true);
        if (startActAnim) {
            overridePendingTransition(R.anim.tran_start_act_in,
                    R.anim.tran_start_act_out);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
