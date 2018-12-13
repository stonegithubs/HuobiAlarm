package com.tdx.demo.myapplication.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tongdexin on 2018/3/17.
 **/

public abstract class BaseDialog extends DialogFragment {

    public static final String DIALOG_TITLE = "DIALOG_TITLE";
    public static final String DIALOG_MESSAGE = "DIALOG_MESSAGE";
    public static final String DIALOG_LEFT_BTN = "DIALOG_LEFT_BTN";
    public static final String DIALOG_RIGHT_BTN = "DIALOG_RIGHT_BTN";
    public static final String DIALOG_RES_LAYOUT_ID = "DIALOG_RES_LAYOUT_ID";

    Unbinder unbinder;

    private float mDimAmount = 0;
    public OnDialogClickListener mOnDialogClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, contentView);
        initView(contentView);
        return contentView;
    }

    public abstract int getLayoutId();

    public abstract void initView(View view);

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = getDialog().getWindow();
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                if (dialog instanceof ProgressDialog || dialog instanceof DatePickerDialog) {
                    if (window != null) {
                        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    }
                }
            }
            if (window != null) {
                WindowManager.LayoutParams windowParams = window.getAttributes();
                windowParams.dimAmount = mDimAmount;
                window.setAttributes(windowParams);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setDimAmount(float dimAmount) {
        mDimAmount = dimAmount;
    }

    public interface OnDialogClickListener {
        void onLeftClick(Dialog dialog);

        void onRightClick(Dialog dialog);

        void onOtherBusiness(Dialog dialog);
    }

    public static class OnDialogClickListenerImp implements OnDialogClickListener {

        @Override
        public void onLeftClick(Dialog dialog) {

        }

        @Override
        public void onRightClick(Dialog dialog) {

        }

        @Override
        public void onOtherBusiness(Dialog dialog) {

        }
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
    }
}