package com.fat.bigfarm.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.Details;
import com.fat.bigfarm.entry.ShoppingDetails;

import java.util.List;

/**
 * 商家详情
 */
public class ShoppingDetailsAdapter extends BaseQuickAdapter<ShoppingDetails.DataBean.GoodsBean> {


    public ShoppingDetailsAdapter(List<ShoppingDetails.DataBean.GoodsBean> data) {
        super(R.layout.shoppingdetail_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShoppingDetails.DataBean.GoodsBean guessBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);

        Glide.with(mContext).load(guessBean.getThumb()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,guessBean.getName());
        baseViewHolder.setText(R.id.tv_des,guessBean.getDes());
        String unit = guessBean.getUnit();
        String price = guessBean.getPrice();
        baseViewHolder.setText(R.id.tv_price,"¥"+price+"元/"+unit);
    }
}
