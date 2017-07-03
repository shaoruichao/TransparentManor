package com.fat.bigfarm.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.entry.DeleteCart;
import com.fat.bigfarm.entry.Earnings;
import com.fat.bigfarm.entry.IncomeEdit;
import com.fat.bigfarm.entry.Raise;
import com.fat.bigfarm.eventbus.MessageEvent;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.activity.AddressManageActivity;
import com.fat.bigfarm.ui.activity.RetentionActivity;
import com.fat.bigfarm.ui.activity.SellActivity;
import com.fat.bigfarm.ui.activity.SellExampleActivity;
import com.fat.bigfarm.ui.fragment.MyEarningsFragment;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import okhttp3.Call;

/**
 * 我的仓库-收益-item
 * Created by yusheng on 2016/11/28.
 */
public class WarehouseEarningsitemAdapter extends BaseQuickAdapter<Earnings.DataBean.FosterBean> {

    private String shopname;
    private Earnings.DataBean.FosterBean.IncomeBean income;
    private String count;
    private String name;
    private String thumb;
    private String content;
    private int adapterPosition;
    private int earningsPosition;

    private KProgressHUD hud;

    public WarehouseEarningsitemAdapter(List<Earnings.DataBean.FosterBean> data, String shopname, int earningsPosition) {
        super(R.layout.mywarehouse_earnings_item,data);
        this.shopname = shopname;
        this.earningsPosition = earningsPosition;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Earnings.DataBean.FosterBean dataBean) {

        adapterPosition = baseViewHolder.getAdapterPosition();

        ImageView iv_thumb = baseViewHolder.getView(R.id.iv_thumb);
        Glide.with(mContext).load(dataBean.getThumb()).crossFade().into(iv_thumb);

        baseViewHolder.setText(R.id.tv_title,dataBean.getName());
        // 状态 1 收益转赠中 2 收益已转赠 3 收益出售中 4 收益已出售 5 待处理/取消转赠
        TextView tv_status = baseViewHolder.getView(R.id.tv_status);
        RelativeLayout rl_operation = baseViewHolder.getView(R.id.rl_operation);
        RelativeLayout rl_operation2 = baseViewHolder.getView(R.id.rl_operation2);
        Button bt_example = baseViewHolder.getView(R.id.bt_example);
        final Button bt_cancle_right = baseViewHolder.getView(R.id.bt_cancle_right);
        Button bt_sell = baseViewHolder.getView(R.id.bt_sell);
        Button bt_retention = baseViewHolder.getView(R.id.bt_retention);
        String status = dataBean.getIncome().getStatus();
        final String id = dataBean.getIncome().getId();
        if (status.equals("1")){
            tv_status.setText("收益转赠中");
            rl_operation.setVisibility(View.GONE);
            rl_operation2.setVisibility(View.VISIBLE);
        }else if (status.equals("2")){
            tv_status.setText("收益已转赠");
            tv_status.setTextColor(Color.parseColor("#DE413D"));
        }else if (status.equals("3")){
            tv_status.setText("收益出售中");
            tv_status.setTextColor(Color.parseColor("#FFAC00"));
        }else if (status.equals("4")){
            tv_status.setText("收益已出售");
            tv_status.setTextColor(Color.parseColor("#FFAC00"));
        }else if (status.equals("5")){
            tv_status.setText("待处理");
            rl_operation.setVisibility(View.VISIBLE);
            tv_status.setTextColor(Color.parseColor("#FFAC00"));
        }else if (status.equals("7")){
            tv_status.setText("收益自留");
            tv_status.setTextColor(Color.parseColor("#FFAC00"));
        }

        baseViewHolder.setText(R.id.tv_raisetime,"代养时长："+dataBean.getFostertime());
        baseViewHolder.setText(R.id.tv_raisecycle,"收益周期："+dataBean.getIncome().getCycle());
        baseViewHolder.setText(R.id.tv_content,"收益："+dataBean.getIncome().getContent());
        //收益自留
        bt_retention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", id);
                intent.setClass(mContext, RetentionActivity.class);
                mContext.startActivity(intent);
            }
        });


        income = dataBean.getIncome();
        count = income.getCount();
        content = income.getContent();
        name = dataBean.getName();
        thumb = dataBean.getThumb();
        //收益出售
        bt_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dlg =
                        new AlertDialog.Builder(mContext, R.style.MyDialogStyle).create();
                dlg.setCanceledOnTouchOutside(true);
                dlg.show();
                Window window = dlg.getWindow();
                //设置窗口的内容页面
                window.setContentView(R.layout.earnings_sell_dialog);
                //取消
                Button bt_cancel = (Button) window.findViewById(R.id.bt_cancel);
                bt_cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
                    }
                });
                //确定
                Button bt_ok = (Button) window.findViewById(R.id.bt_ok);
                bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
//                        PostIncomeEdit(id);
                        Intent intent1 = new Intent();
                        intent1.putExtra("id",id);
                        intent1.putExtra("shopname",shopname);
                        intent1.putExtra("count", count);
                        intent1.putExtra("thumb", thumb);
                        intent1.putExtra("name", name);
                        intent1.putExtra("content", content);
                        intent1.setClass(mContext, SellActivity.class);
                        mContext.startActivity(intent1);

                    }
                });
            }
        });
        //收益转赠
        bt_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostIncomeEdit(id);

            }
        });
        //取消转赠
        bt_cancle_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostIncomeEditCancle(id);
            }
        });

    }

    private void PostIncomeEdit(final String id){
        PostRequest tag = OkGo.post(AllUrl.INCOME_EDIT).tag(this);
        tag.params("id",id);
        tag.params("status","1");

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                IncomeEdit incomeEdit = JsonUtil.parseJsonToBean(s.toString(), IncomeEdit.class);
                int code = incomeEdit.getCode();
                String msg = incomeEdit.getMsg();
                if (code == 200){
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    intent.setClass(mContext, SellExampleActivity.class);
                mContext.startActivity(intent);
                }else {
                    ToastUtil.showToast(mContext,msg);
                }
            }
        });
    }


    private void PostIncomeEditCancle(final String id){
        PostRequest tag = OkGo.post(AllUrl.INCOME_EDIT).tag(this);
        tag.params("id",id);
        tag.params("status","5");

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                IncomeEdit incomeEdit = JsonUtil.parseJsonToBean(s.toString(), IncomeEdit.class);
                int code = incomeEdit.getCode();
                String msg = incomeEdit.getMsg();
                if (code == 200){

                    EventBus.getDefault().post(new MessageEvent(""));

                }else {
                    ToastUtil.showToast(mContext,msg);
                }
            }
        });
    }


}
