package com.elmand.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elmand.toolpackage.R;

/**
 * 功能:加载进度条
 * author: elmand6 on 2015/12/16 16:37
 * email：lysa8156@sina.com
 */
public class LoadingDialog {
    private Dialog pd;
    private Activity mActivity;
    private TextView loadingText;
    private AnimationDrawable anim;
    private ImageView loadingImg;

    public LoadingDialog(Activity context) {
        mActivity = context;
        pd = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        loadingImg = (ImageView) view.findViewById(R.id.loading_img);
        loadingImg.setBackgroundResource(R.drawable.loading);
        anim = (AnimationDrawable) loadingImg.getBackground();
        anim.start();
        pd.setContentView(view);
    }

    public LoadingDialog setLoadingText(String text) {
        loadingText.setText(text);
        return this;
    }

    public void show() {
        if (!mActivity.isFinishing() && !pd.isShowing()) {
            pd.show();
        }
    }

    public void close() {
        if (!mActivity.isFinishing() && pd.isShowing()) {
            pd.dismiss();
        }
    }

    public boolean isShow() {
        return pd.isShowing();
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener l) {
        pd.setOnCancelListener(l);
    }
}
