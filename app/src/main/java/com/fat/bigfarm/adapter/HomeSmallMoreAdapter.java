package com.fat.bigfarm.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.Details;
import com.fat.bigfarm.entry.HomeMore;

import java.util.List;

/**首页更多(物品分类) 详情   小图显示
 * Created by yusheng on 2016/11/28.
 */
public class HomeSmallMoreAdapter extends BaseQuickAdapter<HomeMore.ListBean> {


    public HomeSmallMoreAdapter(List<HomeMore.ListBean> data) {
        super(R.layout.home_small_more,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeMore.ListBean guessBean) {

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);

        Glide.with(mContext).load(guessBean.getThumb()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,guessBean.getName());
        baseViewHolder.setText(R.id.tv_des,guessBean.getDes());
        String unit = guessBean.getUnit();
        String price = guessBean.getPrice();
        baseViewHolder.setText(R.id.tv_price,"¥"+price+"元/"+unit);
    }
}
