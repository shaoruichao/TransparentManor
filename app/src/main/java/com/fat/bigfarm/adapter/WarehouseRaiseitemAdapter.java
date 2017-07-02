package com.fat.bigfarm.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.MyOrderAll;
import com.fat.bigfarm.entry.Raise;

import java.util.List;

/**
 * 我的仓库-代养-item
 * Created by yusheng on 2016/11/28.
 */
public class WarehouseRaiseitemAdapter extends BaseQuickAdapter<Raise.DataBean.FosterBean> {


    public WarehouseRaiseitemAdapter(List<Raise.DataBean.FosterBean> data) {
        super(R.layout.mywarehouse_raise_item,data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, Raise.DataBean.FosterBean dataBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);
        Glide.with(mContext).load(dataBean.getThumb()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,dataBean.getName());
        // 状态 0 待付款 1 正在代养 2 代养结束
        TextView tv_status = baseViewHolder.getView(R.id.tv_status);
        String status = dataBean.getStatus();
        if (status.equals("0")){
            tv_status.setText("待付款");
        }else if (status.equals("1")){
            tv_status.setText("正在代养");
            tv_status.setTextColor(Color.parseColor("#DE413D"));
        }else if (status.equals("1")){
            tv_status.setText("代养结束");
            tv_status.setTextColor(Color.parseColor("#FFAC00"));
        }

        baseViewHolder.setText(R.id.tv_raisetime,"代养时长："+dataBean.getFostertime());
        baseViewHolder.setText(R.id.tv_raisecycle,"收益周期："+dataBean.getCycle());
        baseViewHolder.setText(R.id.tv_content,"收益："+dataBean.getContent());


    }
}
