package com.fat.bigfarm.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.ActivitiesDetail;
import com.fat.bigfarm.entry.ShoppingDetails;

import java.util.List;

/**
 * 活动详情
 */
public class ActivitiesDetailsAdapter extends BaseQuickAdapter<ActivitiesDetail.DataBean.GoodsBean> {


    public ActivitiesDetailsAdapter(List<ActivitiesDetail.DataBean.GoodsBean> data) {
        super(R.layout.activitiesdetail_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ActivitiesDetail.DataBean.GoodsBean guessBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);


        Glide.with(mContext).load(guessBean.getThumb()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,guessBean.getName());
        baseViewHolder.setText(R.id.tv_des,guessBean.getDes());
        baseViewHolder.setText(R.id.tv_actionprice,guessBean.getAction_price());
        baseViewHolder.setText(R.id.tv_price,guessBean.getPrice());

    }
}
