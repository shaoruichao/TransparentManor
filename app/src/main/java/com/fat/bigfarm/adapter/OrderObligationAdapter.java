package com.fat.bigfarm.adapter;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.entry.DeleteCart;
import com.fat.bigfarm.entry.MyOrderAll;
import com.fat.bigfarm.entry.MyOrderObligation;
import com.fat.bigfarm.entry.OrderEdit;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 我的订单
 * Created by yusheng on 2016/11/28.
 */
public class OrderObligationAdapter extends BaseQuickAdapter<MyOrderAll.DataBean> {


    private OrderObligationItemAdapter orderObligationItemAdapter;

    public OrderObligationAdapter(List<MyOrderAll.DataBean> data) {
        super(R.layout.myorder_oblitation_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyOrderAll.DataBean dataBean) {

        final int adapterPosition = baseViewHolder.getAdapterPosition();
        RecyclerView rv_order_all_item = baseViewHolder.getView(R.id.rv_order_all_item);
        RelativeLayout rl_operation = baseViewHolder.getView(R.id.rl_operation);

        baseViewHolder.setText(R.id.tv_number,dataBean.getOrdernumber());
        String orderstatus = dataBean.getOrderstatus();
        if (orderstatus.equals("0")){
            baseViewHolder.setText(R.id.tv_status,"已删除订单");
            rl_operation.setVisibility(View.GONE);
        }else if (orderstatus.equals("1")){
            baseViewHolder.setText(R.id.tv_status,"待付款");
            rl_operation.setVisibility(View.VISIBLE);
        }else if (orderstatus.equals("2")){
            baseViewHolder.setText(R.id.tv_status,"待发货");
            rl_operation.setVisibility(View.GONE);
        }else if (orderstatus.equals("3")){
            baseViewHolder.setText(R.id.tv_status,"已发货");
            rl_operation.setVisibility(View.GONE);
        }else if (orderstatus.equals("4")){
            baseViewHolder.setText(R.id.tv_status,"交易完成");
            rl_operation.setVisibility(View.GONE);
        }else if (orderstatus.equals("5")){
            baseViewHolder.setText(R.id.tv_status,"已取消订单");
            rl_operation.setVisibility(View.GONE);
        }else if (orderstatus.equals("6")){
            baseViewHolder.setText(R.id.tv_status,"申请退款");
            rl_operation.setVisibility(View.GONE);
        }else if (orderstatus.equals("7")){
            baseViewHolder.setText(R.id.tv_status,"退款中");
            rl_operation.setVisibility(View.GONE);
        }else if (orderstatus.equals("8")){
            baseViewHolder.setText(R.id.tv_status,"已退款");
            rl_operation.setVisibility(View.GONE);
        }

        List<MyOrderAll.DataBean.GoodsinfoBean> goodsinfo = dataBean.getGoodsinfo();

        orderObligationItemAdapter = new OrderObligationItemAdapter(goodsinfo);

        orderObligationItemAdapter.openLoadAnimation();

        rv_order_all_item.setAdapter(orderObligationItemAdapter);

        rv_order_all_item.setHasFixedSize(true);
        rv_order_all_item.setLayoutManager(new LinearLayoutManager(mContext));

//        final String orderid = dataBean.getOrderid();
        final Button bt_cancelorder = baseViewHolder.getView(R.id.bt_cancelorder);
        final Button bt_pay = baseViewHolder.getView(R.id.bt_pay);
        if (mOnItemClickLitener != null){
            bt_cancelorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(bt_cancelorder,adapterPosition);

                }
            });
            bt_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.payonItemClick(bt_pay,adapterPosition);
                }
            });

            orderObligationItemAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    mOnItemClickLitener.rvonItemClick(orderObligationItemAdapter,adapterPosition);
                }
            });
        }


    }

    public interface OnItemClickListener{
        void onItemClick(Button bt_cancelorder,int postion);
        void payonItemClick(Button bt_pay,int postion);
        void rvonItemClick(OrderObligationItemAdapter orderObligationItemAdapter, int postion);
    }
    /**自定义条目点击监听*/
    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void remove(int position) {
        super.remove(position);
        notifyItemRemoved(position);
    }
}
