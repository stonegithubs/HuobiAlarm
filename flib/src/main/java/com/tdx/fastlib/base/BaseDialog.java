package com.tdx.fastlib.base;

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

import com.tdx.fastlib.R;

/**
 * Created by tongdexin on 2018/3/17.
 **/

public class BaseDialog extends DialogFragment {

    public static final String DIALOG_TITLE = "DIALOG_TITLE";
    public static final String DIALOG_MESSAGE = "DIALOG_MESSAGE";
    public static final String DIALOG_RES_LAYOUT_ID = "DIALOG_RES_LAYOUT_ID";

    public BaseDialog() {

    }

    public static BaseDialog newInstance(Bundle bundle) {
        BaseDialog frag = new BaseDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_base, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        Bundle bundle = getArguments();
    }

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
                windowParams.dimAmount = 0.0f;
                window.setAttributes(windowParams);
            }
        }
    }

}