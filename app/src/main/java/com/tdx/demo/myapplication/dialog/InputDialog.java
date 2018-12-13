package com.tdx.demo.myapplication.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tdx.demo.myapplication.R;

import butterknife.BindView;

/**
 * Created by tongdexin on 2018/5/20.
 **/

public class InputDialog extends BaseDialog {

    public static final String DIALOG_INPUT_HINT1 = "DIALOG_INPUT_HINT1";
    public static final String DIALOG_INPUT_HINT2 = "DIALOG_INPUT_HINT2";

    @BindView(R.id.input_title)
    TextView mInputTitle;
    @BindView(R.id.input_edt_1)
    EditText mInputEdt1;
    @BindView(R.id.input_edt_2)
    EditText mInputEdt2;
    @BindView(R.id.input_btn_left)
    TextView mInputBtnLeft;
    @BindView(R.id.input_btn_right)
    TextView mInputBtnRight;

    private OnDialogInputListener mOnDialogInputListener;

    public static InputDialog newInstanceDouble(String title, String hint1, String hint2, String leftBtn, String rightBtn, OnDialogInputListener listener) {
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TITLE, title);
        bundle.putString(DIALOG_LEFT_BTN, leftBtn);
        bundle.putString(DIALOG_RIGHT_BTN, rightBtn);
        bundle.putString(DIALOG_INPUT_HINT1, hint1);
        bundle.putString(DIALOG_INPUT_HINT2, hint2);
        InputDialog dialog = new InputDialog();
        dialog.setOnDialogInputListener(listener);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static InputDialog newInstance(String title, String hint1, String leftBtn, String rightBtn, OnDialogInputListener listener) {
        return newInstanceDouble(title, hint1, "", leftBtn, rightBtn, listener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_input;
    }

    @Override
    public void initView(View view) {
        Bundle bundle = getArguments();
        mInputTitle.setText(bundle.getString(DIALOG_TITLE));
        mInputEdt1.setHint(bundle.getString(DIALOG_INPUT_HINT1));
        if (TextUtils.isEmpty(bundle.getString(DIALOG_INPUT_HINT2))) {
            mInputEdt2.setVisibility(View.GONE);
        } else {
            mInputEdt2.setHint(bundle.getString(DIALOG_INPUT_HINT2));
        }

        mInputBtnLeft.setText(bundle.getString(DIALOG_LEFT_BTN));
        mInputBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDialogClickListener != null) {
                    mOnDialogInputListener.onLeftClick(getDialog());
                }
            }
        });
        mInputBtnRight.setText(bundle.getString(DIALOG_RIGHT_BTN));
        mInputBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDialogClickListener != null) {
                    mOnDialogInputListener.onRightClick(getDialog(),getInputEdt1());
                }
            }
        });
    }

    public String getInputEdt1() {
        return mInputEdt1.getText().toString();
    }

    public String getInputEdt2() {
        return mInputEdt2.getText().toString();
    }

    public interface OnDialogInputListener {
        void onLeftClick(Dialog dialog);

        void onRightClick(Dialog dialog, String input);
    }

    public void setOnDialogInputListener(OnDialogInputListener onDialogInputListener) {
        mOnDialogInputListener = onDialogInputListener;
    }
}
