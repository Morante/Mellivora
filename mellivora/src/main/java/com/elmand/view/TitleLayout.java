package com.elmand.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.elmand.toolpackage.R;

/**
 * author: elmand6 on 2015/12/26 23:51
 * email：lysa8156@sina.com
 */
public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context) {
        super(context);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        ImageView img_title_back = (ImageView) findViewById(R.id.img_title_back);
        img_title_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
                ((Activity) getContext()).overridePendingTransition(R.anim.tran_finish_act_in,
                        R.anim.tran_finish_act_out);
            }
        });
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
