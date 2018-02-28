package com.elmand.activity;

import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elmand.toolpackage.R;
import com.elmand.utils.FunctionUtils;
import com.elmand.utils.LogUtils;
import com.elmand.utils.SPUtils;
import com.elmand.utils.ToastUtils;
import com.elmand.view.LoadingDialog;

import java.util.Calendar;

public class MainActivity extends BaseActivity {


    private Button btn;
    private EditText et;


    @Override
    protected void processLogic() {
    }

    @Override
    protected void setListener() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }

    @Override
    protected void findViews() {
        btn = (Button) findViewById(R.id.btn);
        et = (EditText) findViewById(R.id.et);
    }


    @Override
    protected void getSaveData(Bundle savedInstanceState) {

    }

    @Override
    public int getResLayoutId() {
        return R.layout.activity_main;
    }

    private class MyWebViewClient extends WebViewClient {

    }
}
