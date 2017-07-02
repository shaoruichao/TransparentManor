package com.fat.bigfarm.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.bean.ProductInfo;
import com.fat.bigfarm.entry.AddressList;

import java.text.FieldPosition;
import java.util.List;

/**Rv适配器，包含多个头布局
 * Created by yusheng on 2016/11/28.
 */
public class AddressListAdapter extends BaseQuickAdapter<AddressList.DataBean> {


    public AddressListAdapter(List<AddressList.DataBean> data) {
        super(R.layout.addressmanage_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AddressList.DataBean guessBean) {

        int adapterPosition = baseViewHolder.getAdapterPosition();
        if (adapterPosition == 0){
            baseViewHolder.getView(R.id.tv).setVisibility(View.VISIBLE);
        }else {
            baseViewHolder.getView(R.id.tv).setVisibility(View.GONE);
        }

        baseViewHolder.setText(R.id.tv_name,guessBean.getLinkman());
        baseViewHolder.setText(R.id.tv_phone,guessBean.getTelnumber());
        baseViewHolder.setText(R.id.tv_address,guessBean.getProvince()+guessBean.getCity()+guessBean.getCountry()+guessBean.getDetail());

//        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);

//        Glide.with(mContext).load(guessBean.getImageUrl()).crossFade().into(iv_thumb);
//
//        Log.e(TAG, "convert: "+guessBean.getCartid() );
//        Log.e(TAG, "convert: "+guessBean.getId() );
//        Log.e(TAG, "convert: "+guessBean.getSrcname() );
//
//        baseViewHolder.setText(R.id.tv_title,guessBean.getSrcname());
//        baseViewHolder.setText(R.id.tv_count,String.valueOf(guessBean.getCount()));
//        baseViewHolder.setText(R.id.tv_price,guessBean.getPrice());
    }

}
