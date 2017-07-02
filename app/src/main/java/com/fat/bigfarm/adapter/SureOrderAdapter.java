package com.fat.bigfarm.adapter;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.bean.ProductInfo;

import java.util.List;

/**
 *  确认订单adapter
 */
public class SureOrderAdapter extends BaseQuickAdapter<ProductInfo> {


    public SureOrderAdapter(List<ProductInfo> data) {
        super(R.layout.suerorder_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProductInfo guessBean) {

        String sid = guessBean.getSid();
        Log.e(TAG, "convertsid: "+sid );

        TextView tv_shopname = baseViewHolder.getView(R.id.tv_shopname);
        String shopname = guessBean.getShopname();
        Log.e(TAG, "convert: "+shopname );

        boolean top = guessBean.isTop();
        if (top == true){

            baseViewHolder.setText(R.id.tv_shopname,shopname);
        }else {
            tv_shopname.setVisibility(View.GONE);
        }

        String aid = guessBean.getAid();
        String action_price = guessBean.getAction_price();
        String price = guessBean.getPrice();
        String unit = guessBean.getUnit();
        TextView tv_actionprice = baseViewHolder.getView(R.id.tv_actionprice);
        if (aid.equals("0")){
            baseViewHolder.setText(R.id.tv_actionprice,"");
            baseViewHolder.setText(R.id.tv_price,"¥"+price+"元/"+unit);
        }else {
            baseViewHolder.setText(R.id.tv_actionprice,"¥"+price+"元/"+unit);
            tv_actionprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG ); //中间横线
            baseViewHolder.setText(R.id.tv_price,"¥"+action_price+"元/"+unit);
        }


        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);

        Glide.with(mContext).load(guessBean.getImageUrl()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,guessBean.getSrcname());
        baseViewHolder.setText(R.id.tv_count,"×"+String.valueOf(guessBean.getCount()));


    }
}
