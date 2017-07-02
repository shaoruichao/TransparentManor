package com.fat.bigfarm.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.Details;

import java.util.List;

/**物品详情
 * Created by yusheng on 2016/11/28.
 */
public class DetailsAdapter extends BaseQuickAdapter<Details.DataBean.GuessBean> {


    public DetailsAdapter(List<Details.DataBean.GuessBean> data) {
        super(R.layout.rv_item_normal,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Details.DataBean.GuessBean guessBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);

        Glide.with(mContext).load(guessBean.getThumb()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,guessBean.getName());
        baseViewHolder.setText(R.id.tv_des,guessBean.getDes());
        String price = guessBean.getPrice();
        String unit = guessBean.getUnit();
        baseViewHolder.setText(R.id.tv_price,"¥"+price+"元/"+unit);
    }
}
