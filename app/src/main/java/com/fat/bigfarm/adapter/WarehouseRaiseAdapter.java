package com.fat.bigfarm.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.MyOrderAll;
import com.fat.bigfarm.entry.Raise;

import java.util.List;

/**
 * 我的仓库-代养
 * Created by yusheng on 2016/11/28.
 */
public class WarehouseRaiseAdapter extends BaseQuickAdapter<Raise.DataBean> {


    private WarehouseRaiseitemAdapter warehouseRaiseitemAdapter;

    public WarehouseRaiseAdapter(List<Raise.DataBean> data) {
        super(R.layout.mywarehouse_raise,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Raise.DataBean dataBean) {

        RecyclerView rv_myraise_item = baseViewHolder.getView(R.id.rv_myraise_item);

        baseViewHolder.setText(R.id.tv_name,dataBean.getShopname());


        List<Raise.DataBean.FosterBean> foster = dataBean.getFoster();


        rv_myraise_item.setHasFixedSize(true);
        rv_myraise_item.setLayoutManager(new LinearLayoutManager(mContext));

        warehouseRaiseitemAdapter = new WarehouseRaiseitemAdapter(foster);
        warehouseRaiseitemAdapter.openLoadAnimation();
        rv_myraise_item.setAdapter(warehouseRaiseitemAdapter);


    }

}
