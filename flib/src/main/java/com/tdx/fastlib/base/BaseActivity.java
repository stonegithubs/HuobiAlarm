package com.tdx.fastlib.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tdx.fastlib.R;
import com.tdx.fastlib.refresh.api.RefreshLayout;

public class BaseActivity extends AppCompatActivity {

//    protected BaseApplication mBaseApplication;
    private RelativeLayout mBaseLayout;
    private FrameLayout mFraLayoutContent;
    private FrameLayout mFraLayoutHeadView;
    private FrameLayout mErrorLayout;
    private FrameLayout mEmptyLayout;
    protected RefreshLayout mRefreshLayout;
    private Toast mToast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mBaseApplication = (BaseApplication) getApplication();
//        mBaseApplication.addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mBaseLayout = findViewById(R.id.relLayoutBase);
        mFraLayoutContent = findViewById(R.id.fraLayoutContent);
        mFraLayoutHeadView = findViewById(R.id.fraLayoutHeadView);
        mErrorLayout = findViewById(R.id.errorLayout);
        mEmptyLayout = findViewById(R.id.emptyLayout);

        mRefreshLayout = findViewById(R.id.refreshLayout);

        if (layoutResID != -1) {
            LayoutInflater.from(this).inflate(layoutResID, mFraLayoutContent, true);
        }

        initView();
        initListener();
        initData();
    }

    protected void jumpActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            startActivity(intent, bundle);
        }
    }

    protected void jumpActivity(Class<?> clz) {
        jumpActivity(clz, null);
    }

    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initListener() {

    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.ACTION_DOWN == event.getAction() && KeyEvent.KEYCODE_BACK == keyCode) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mBaseApplication.removeActivity(this);
    }

    private void exitApp() {
//        mBaseApplication.removeAllActivity();
    }

    protected int getResColor(@ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.getColor(colorId);
        } else {
            return getResources().getColor(colorId);
        }
    }

    public void setHeadViewResId(int headViewResId) {
        if (headViewResId != -1) {
            LayoutInflater.from(this).inflate(headViewResId, mFraLayoutHeadView, true);
        }
    }

    public void setErrorLayoutResId(int errorLayoutResId) {
        if (errorLayoutResId != -1) {
            LayoutInflater.from(this).inflate(errorLayoutResId, mErrorLayout, true);
        }
    }

    public void setEmptyLayoutResId(int mEmptyLayoutResId) {
        if (mEmptyLayoutResId != -1) {
            LayoutInflater.from(this).inflate(mEmptyLayoutResId, mEmptyLayout, true);
        }
    }
}
