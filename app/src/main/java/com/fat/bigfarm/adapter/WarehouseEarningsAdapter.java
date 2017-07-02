package com.fat.bigfarm.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.Earnings;
import com.fat.bigfarm.entry.Raise;

import java.util.List;

/**
 * 我的仓库-收益
 * Created by yusheng on 2016/11/28.
 */
public class WarehouseEarningsAdapter extends BaseQuickAdapter<Earnings.DataBean> {


    private WarehouseEarningsitemAdapter warehouseEarningsitemAdapter;
    private int earningsPosition;

    public WarehouseEarningsAdapter(List<Earnings.DataBean> data) {
        super(R.layout.mywarehouse_earnings,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Earnings.DataBean dataBean) {

        earningsPosition = baseViewHolder.getAdapterPosition();

        RecyclerView rv_myraise_item = baseViewHolder.getView(R.id.rv_myraise_item);

        baseViewHolder.setText(R.id.tv_name,dataBean.getShopname());

        List<Earnings.DataBean.FosterBean> foster = dataBean.getFoster();


        rv_myraise_item.setHasFixedSize(true);
        rv_myraise_item.setLayoutManager(new LinearLayoutManager(mContext));

        warehouseEarningsitemAdapter = new WarehouseEarningsitemAdapter(foster,dataBean.getShopname(),earningsPosition);
        warehouseEarningsitemAdapter.openLoadAnimation();
        rv_myraise_item.setAdapter(warehouseEarningsitemAdapter);


    }

}
