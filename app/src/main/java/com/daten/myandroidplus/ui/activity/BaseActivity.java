package com.daten.myandroidplus.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //ButterKnife的绑定
        ButterKnife.bind(this);
        init();
    }

    //初始化方法，初始化Activity公共的功能
    protected void init() {
    }

    //子类必须实现返回的一个布局id
    public abstract int getLayoutResId();

    public void navigateTo(Class to) {
        Intent intent = new Intent(this, to);
        startActivity(intent);
        finish();

    }

    protected void postDelay(Runnable runnable, long delayMillis) {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(runnable, delayMillis);
    }

    /**
     * 将状态栏背景颜色设置为透明
     */
    protected void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }

    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void navigateForResultTo(Class to, int requestCode) {
        Intent intent = new Intent(this, to);
        startActivityForResult(intent, requestCode);
    }
}
