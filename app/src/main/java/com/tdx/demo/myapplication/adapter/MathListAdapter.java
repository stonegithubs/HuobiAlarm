package com.tdx.demo.myapplication.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tdx.demo.myapplication.R;
import com.tdx.demo.myapplication.model.MathModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2018/10/4.
 **/
public class MathListAdapter extends BaseAdapter {

    private List<MathModel> mListData;
    private Context mContext;
    private OnItemEditListener mOnItemEditListener;


    public MathListAdapter(Context context, List<MathModel> listData) {
        mContext = context;
        mListData = listData;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_math_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mNo.setText(String.valueOf(position));
            if (!TextUtils.isEmpty(mListData.get(position).getDate())) {
                viewHolder.mDate1.setText(mListData.get(position).getDate());
            }
            if (!TextUtils.isEmpty(mListData.get(position).getDate())) {
                viewHolder.mDate1.setText(mListData.get(position).getDate());
            }
            if (mListData.get(position).getMathData() != null && mListData.get(position).getMathData().size() > 1) {
                viewHolder.mValue1.setText(String.valueOf(mListData.get(position).getMathData().get(0)));
            }
            if (mListData.get(position).getMathData() != null && mListData.get(position).getMathData().size() > 2) {
                viewHolder.mValue2.setText(String.valueOf(mListData.get(position).getMathData().get(1)));
            }
            if (mListData.get(position).getMathData() != null && mListData.get(position).getMathData().size() > 3) {
                viewHolder.mValue3.setText(String.valueOf(mListData.get(position).getMathData().get(2)));
            }
            if (mListData.get(position).getMax() > 0) {
                viewHolder.mMax.setText(String.valueOf(mListData.get(position).getMax()));
            }
            if (mListData.get(position).getMin() > 0) {
                viewHolder.mMin.setText(String.valueOf(mListData.get(position).getMin()));
            }
            viewHolder.mDate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemEditListener != null) {
                        mOnItemEditListener.onItemEditDate1(position);
                    }
                }
            });
            viewHolder.mDate2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemEditListener != null) {
                        mOnItemEditListener.onItemEditDate2(position);
                    }
                }
            });
            viewHolder.mValue1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemEditListener != null) {
                        mOnItemEditListener.onItemEditValue1(position);
                    }
                }
            });
            viewHolder.mValue2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemEditListener != null) {
                        mOnItemEditListener.onItemEditValue2(position);
                    }
                }
            });
            viewHolder.mValue3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemEditListener != null) {
                        mOnItemEditListener.onItemEditValue3(position);
                    }
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.no)
        TextView mNo;
        @BindView(R.id.date1)
        TextView mDate1;
        @BindView(R.id.date2)
        TextView mDate2;
        @BindView(R.id.value1)
        TextView mValue1;
        @BindView(R.id.value2)
        TextView mValue2;
        @BindView(R.id.value3)
        TextView mValue3;
        @BindView(R.id.max)
        TextView mMax;
        @BindView(R.id.min)
        TextView mMin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemEditListener {
        void onItemEditDate1(int pos);

        void onItemEditDate2(int pos);

        void onItemEditValue1(int pos);

        void onItemEditValue2(int pos);

        void onItemEditValue3(int pos);

    }

    public void setOnItemEditListener(OnItemEditListener onItemEditListener) {
        mOnItemEditListener = onItemEditListener;
    }
}
