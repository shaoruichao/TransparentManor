package com.fat.bigfarm.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.MyOrderAll;
import com.fat.bigfarm.entry.MyOrderObligation;

import java.util.List;

/**
 * 我的订单-待付款-item
 * Created by yusheng on 2016/11/28.
 */
public class OrderObligationItemAdapter extends BaseQuickAdapter<MyOrderAll.DataBean.GoodsinfoBean> {


    public OrderObligationItemAdapter(List<MyOrderAll.DataBean.GoodsinfoBean> data) {
        super(R.layout.myorder_all_item_item,data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyOrderAll.DataBean.GoodsinfoBean dataBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);
        Glide.with(mContext).load(dataBean.getThumb()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,dataBean.getName());
//        baseViewHolder.setText(R.id.tv_countintroduce,dataBean);
        String price = dataBean.getPrice();
        String unit = dataBean.getUnit();
        baseViewHolder.setText(R.id.tv_price,"¥"+price+"元/"+unit);
        baseViewHolder.setText(R.id.tv_count,"×"+dataBean.getCount());
    }
}
