package com.tdx.demo.myapplication;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tdx.demo.myapplication.model.LastTradeModel;
import com.tdx.fastlib.base.BaseActivity;
import com.tdx.fastlib.okhttp.OkHttpUtils;
import com.tdx.fastlib.okhttp.callback.StringCallback;

import java.io.IOException;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class MainActivity extends BaseActivity {

    @BindView(R.id.eth_select_btn)
    TextView mEthSelectBtn;
    @BindView(R.id.btc_select_btn)
    TextView mBtcSelectBtn;
    @BindView(R.id.this_week_btn)
    TextView mThisWeekBtn;
    @BindView(R.id.next_week_btn)
    TextView mNextWeekBtn;
    @BindView(R.id.quarter_btn)
    TextView mQuarterBtn;
    @BindView(R.id.long_select_btn)
    TextView mLongSelectBtn;
    @BindView(R.id.short_select_btn)
    TextView mShortSelectBtn;
    @BindView(R.id.leverage_1x_btn)
    TextView mLeverage1xBtn;
    @BindView(R.id.leverage_5x_btn)
    TextView mLeverage5xBtn;
    @BindView(R.id.leverage_10x_btn)
    TextView mLeverage10xBtn;
    @BindView(R.id.leverage_20x_btn)
    TextView mLeverage20xBtn;
    @BindView(R.id.buy_price)
    EditText mBuyPrice;
    @BindView(R.id.buy_count)
    EditText mBuyCount;
    @BindView(R.id.warning_price)
    EditText mWarningPrice;
    @BindView(R.id.now_price)
    TextView mNowPrice;
    @BindView(R.id.alarm_start)
    TextView mAlarmStart;
    @BindView(R.id.income_tv)
    TextView mIncomeTv;

    private String mSymbol;//合约品种
    private String mContractType;//合约类型

    private int mMakeType = 0; //0做多 1做空
    private int mLeverage = 1;//杠杆倍数

    private Handler mHandler;

    MediaPlayer mMediaPlayer;
    Vibrator mVibrator;

    @Override
    protected void initView() {
        mHandler = new Handler();
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(this, RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_ALARM));
            mMediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.eth_select_btn, R.id.btc_select_btn, R.id.this_week_btn, R.id.next_week_btn, R.id.quarter_btn, R.id.long_select_btn, R.id.short_select_btn, R.id.leverage_1x_btn, R.id.leverage_5x_btn, R.id.leverage_10x_btn, R.id.leverage_20x_btn, R.id.alarm_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eth_select_btn:
                mSymbol = "ETH";
                mEthSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                mBtcSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                break;
            case R.id.btc_select_btn:
                mSymbol = "BTC";
                mEthSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mBtcSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                break;
            case R.id.this_week_btn:
                mContractType = "CW";
                mThisWeekBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                mNextWeekBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mQuarterBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                break;
            case R.id.next_week_btn:
                mContractType = "NW";
                mThisWeekBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mNextWeekBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                mQuarterBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                break;
            case R.id.quarter_btn:
                mContractType = "CQ";
                mThisWeekBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mNextWeekBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mQuarterBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                break;
            case R.id.long_select_btn:
                mMakeType = 0;
                mLongSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                mShortSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                break;
            case R.id.short_select_btn:
                mMakeType = 1;
                mLongSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mShortSelectBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                break;
            case R.id.leverage_1x_btn:
                mLeverage = 1;
                mLeverage1xBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                mLeverage5xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage10xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage20xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                break;
            case R.id.leverage_5x_btn:
                mLeverage = 5;
                mLeverage1xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage5xBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                mLeverage10xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage20xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                break;
            case R.id.leverage_10x_btn:
                mLeverage = 10;
                mLeverage1xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage5xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage10xBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                mLeverage20xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                break;
            case R.id.leverage_20x_btn:
                mLeverage = 20;
                mLeverage1xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage5xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage10xBtn.setTextColor(ContextCompat.getColor(this, R.color.text_z1));
                mLeverage20xBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                break;
            case R.id.alarm_start:
                getData();
                if (mAlarmStart.getText().toString().equals("关闭响铃")) {
                    mAlarmStart.setText("开始提醒");
                    mMediaPlayer.stop();
                    if (mVibrator != null) {
                        mVibrator.cancel();
                    }
                } else {
                    showToast("拉取数据...");
                }
                break;
        }
    }

    private void getData() {
        mHandler.removeCallbacksAndMessages(null);
        OkHttpUtils.get().url("https://api.hbdm.com/market/trade?symbol=" + mSymbol + "_" + mContractType).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToast("error");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 10000);
            }

            @Override
            public void onResponse(String response, int id) {
                LastTradeModel result = new Gson().fromJson(response, LastTradeModel.class);
                if (result != null && "ok".equals(result.getStatus()) && result.getTick() != null && result.getTick().getData() != null && result.getTick().getData().size() > 0) {
                    mNowPrice.setText("最近成交价:" + result.getTick().getData().get(0).getPrice());
                    makeMath(result);
                }
            }
        });
    }

    private void makeMath(LastTradeModel result) {
        BigDecimal buyPrice = new BigDecimal(mBuyPrice.getText().toString());
        BigDecimal buyCount = new BigDecimal(mBuyCount.getText().toString());
        BigDecimal sellPrice = new BigDecimal(result.getTick().getData().get(0).getPrice());

        BigDecimal heyuePrice = new BigDecimal(10);
        if ("ETH".equals(mSymbol)) {
            heyuePrice = new BigDecimal(10);
        }
        if ("BTC".equals(mSymbol)) {
            heyuePrice = new BigDecimal(100);
        }

        //合约开仓保证金
        BigDecimal myPrice = buyCount.multiply(heyuePrice).divide(buyPrice, 10, ROUND_HALF_UP).divide(new BigDecimal(mLeverage), 10, ROUND_HALF_UP);

        BigDecimal buy = new BigDecimal(1).divide(buyPrice, 10, ROUND_HALF_UP);
        BigDecimal sell = new BigDecimal(1).divide(sellPrice, 10, ROUND_HALF_UP);
        if (mMakeType == 0) {
            //做多
            BigDecimal a = buy.subtract(sell);
            BigDecimal income = a.multiply(buyCount).multiply(heyuePrice).divide(myPrice, 5, ROUND_HALF_UP);
            updateIncome(income);
        }
        if (mMakeType == 1) {
            //做空
            BigDecimal a = sell.subtract(buy);
            BigDecimal income = a.multiply(buyCount).multiply(heyuePrice).divide(myPrice, 5, ROUND_HALF_UP);
            updateIncome(income);
        }

    }

    private void updateIncome(BigDecimal income) {
        mIncomeTv.setText("收益:" + income.multiply(new BigDecimal(100)).toString() + "%");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 10000);
        BigDecimal limit = new BigDecimal(mWarningPrice.getText().toString());
        if (income.abs().compareTo(limit) > 0) {
            callMe();
        }
    }

    private void callMe() {
        if (mAlarmStart.getText().toString().equals("关闭响铃")) {
            return;
        }
        try {
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mAlarmStart.setText("关闭响铃");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mVibrator != null) {
            mVibrator.vibrate(new long[]{1000, 1000}, 0);
        }
    }
}
