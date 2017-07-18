package com.fat.bigfarm.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.EarningsDetail;
import com.fat.bigfarm.entry.Raise;
import com.fat.bigfarm.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的仓库-收益详情
 * Created by yusheng on 2016/11/28.
 */
public class EarningsDetailAdapter extends BaseQuickAdapter<EarningsDetail.DataBean.IncomeBean> {


    public EarningsDetailAdapter(List<EarningsDetail.DataBean.IncomeBean> data) {
        super(R.layout.earningsdetail_item,data);

    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final EarningsDetail.DataBean.IncomeBean dataBean) {


        final int adapterPosition = baseViewHolder.getAdapterPosition();
        if (adapterPosition == 0){
            baseViewHolder.getView(R.id.ll_titlebar).setVisibility(View.VISIBLE);
        }else {
            baseViewHolder.getView(R.id.ll_titlebar).setVisibility(View.GONE);
        }

        final TextView tv_name = baseViewHolder.getView(R.id.tv_name);
        tv_name.setText(dataBean.getContent());
        final TextView tv_num = baseViewHolder.getView(R.id.tv_num);
        tv_num.setText(dataBean.getCount());
        final TextView tv_time = baseViewHolder.getView(R.id.tv_time);
        String dateToString = DataUtils.getDateToString(Long.parseLong(dataBean.getCreattime()));
        tv_time.setText(dateToString);
        final TextView tv_status = baseViewHolder.getView(R.id.tv_status);
        String status = dataBean.getStatus();
        if (status.equals("1")){
            tv_status.setText("转赠中");
//            rl_operation.setVisibility(View.GONE);
//            rl_operation2.setVisibility(View.VISIBLE);
        }else if (status.equals("2")){
            tv_status.setText("已转赠");
        }else if (status.equals("3")){
            tv_status.setText("出售中");
        }else if (status.equals("5")){
            tv_status.setText("待处理");
//            rl_operation.setVisibility(View.GONE);
//            rl_operation2.setVisibility(View.VISIBLE);
        }else if (status.equals("7")){
            tv_status.setText("自留中");
        }

        if (dataBean.isSelect()){
            tv_name.setTextColor(Color.parseColor("#DE413D"));
            tv_num.setTextColor(Color.parseColor("#DE413D"));
            tv_time.setTextColor(Color.parseColor("#DE413D"));
            tv_status.setTextColor(Color.parseColor("#DE413D"));
        }else {
            tv_name.setTextColor(Color.parseColor("#000000"));
            tv_num.setTextColor(Color.parseColor("#000000"));
            tv_time.setTextColor(Color.parseColor("#000000"));
            tv_status.setTextColor(Color.parseColor("#000000"));
        }


    }
}
