package com.fat.bigfarm.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.Daizhong;
import com.fat.bigfarm.entry.Earnings;
import com.fat.bigfarm.entry.Raise;
import com.fat.bigfarm.ui.activity.DetailsActivity;
import com.fat.bigfarm.ui.activity.EarningsDetailActivity;

import java.util.List;

/**
 * 我的仓库-代种
 * Created by yusheng on 2016/11/28.
 */
public class DaizhongAdapter extends BaseQuickAdapter<Daizhong.DataBean> {


    private WarehouseRaiseitemAdapter warehouseRaiseitemAdapter;
    private DaizhongitemAdapter daizhongitemAdapter;

    public DaizhongAdapter(List<Daizhong.DataBean> data) {
        super(R.layout.mywarehouse_raise,data);
    }



    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Daizhong.DataBean dataBean) {

        RecyclerView rv_myraise_item = baseViewHolder.getView(R.id.rv_myraise_item);

        baseViewHolder.setText(R.id.tv_name,dataBean.getShopname());


        List<Daizhong.DataBean.FosterBean> foster = dataBean.getFoster();


        rv_myraise_item.setHasFixedSize(true);
        rv_myraise_item.setLayoutManager(new LinearLayoutManager(mContext));

        daizhongitemAdapter = new DaizhongitemAdapter(foster);
        daizhongitemAdapter.openLoadAnimation();
        rv_myraise_item.setAdapter(daizhongitemAdapter);

        daizhongitemAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Daizhong.DataBean.FosterBean fosterBean = dataBean.getFoster().get(i);
                String gid = fosterBean.getGid();
                String shopid = dataBean.getShopid();
                String shopname = dataBean.getShopname();
                Intent intent = new Intent();
                intent.putExtra("id",gid);
                intent.putExtra("sid",shopid);
                intent.putExtra("typename",shopname);
                intent.setClass(mContext, DetailsActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

}
