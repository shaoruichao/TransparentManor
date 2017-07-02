package com.fat.bigfarm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.OrderObligationAdapter;
import com.fat.bigfarm.adapter.OrderObligationItemAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseOrderFragment;
import com.fat.bigfarm.entry.MyOrderAll;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.activity.OrderDetailActivity;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单-待发货
 */
public class MyOrderSendgoodsFragment extends BaseOrderFragment {

    private static final String TAG = "MyOrderSendgoodsFragmen";

    @BindView(R.id.rv_order_all)
    RecyclerView rvOrderAll;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;
    @BindView(R.id.fl_nomessage)
    FrameLayout fl_nomessage;
    private View view;
    private String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_order_sendgoods, container, false);

        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        userid = TMApplication.instance.sp.getString("userid", "");
        PostOrderAll();
    }

    private void initView(){
        //设置刷新的颜色
        swLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

//                hud = KProgressHUD.create(getActivity())
//                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
//                hud.show();

                PostOrderAll();

                //停止刷新
                swLayout.setRefreshing(false);
            }
        });
    }

    private void PostOrderAll(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ORDERLIST, RequestMethod.POST);
        if (request != null) {
            request.add("dosubmit", 1);
            request.add("uid",userid);//用户id
            request.add("status",2);

            // 添加到请求队列
            request(0, request, orderlistListener, true, true);
        }
    }

    private HttpListener<JSONObject> orderlistListener = new HttpListener<JSONObject>() {

        private OrderObligationAdapter orderAllAdapter;
        private MyOrderAll myOrderAll;
        private List<MyOrderAll.DataBean> data;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "orderlistListener: " + js);
                int code = js.getInt("code");
                if (code == 200){
                    myOrderAll = JsonUtil.parseJsonToBean(js.toString(), MyOrderAll.class);
                    if (myOrderAll != null){

                        data = myOrderAll.getData();
                        if (data.size()==0){
                            fl_nomessage.setVisibility(View.VISIBLE);
                        }else {
                            fl_nomessage.setVisibility(View.GONE);
                        }

                        orderAllAdapter = new OrderObligationAdapter(data);

                        orderAllAdapter.openLoadAnimation();

                        rvOrderAll.setAdapter(orderAllAdapter);

                        rvOrderAll.setHasFixedSize(true);
                        rvOrderAll.setLayoutManager(new LinearLayoutManager(getActivity()));

                        orderAllAdapter.setmOnItemClickLitener(new OrderObligationAdapter.OnItemClickListener() {
                            //取消订单
                            @Override
                            public void onItemClick(Button bt_cancelorder, int postion) {
//                                String orderid = data.get(postion).getOrderid();
//                                OrderEdit(orderid);
                            }

                            //付款
                            @Override
                            public void payonItemClick(Button bt_pay, int postion) {
                                String orderid = data.get(postion).getOrderid();
                                ToastUtil.showToast(getActivity(),orderid);
                            }
                            //item点击
                            @Override
                            public void rvonItemClick(OrderObligationItemAdapter orderObligationItemAdapter, int postion) {
                                String orderid = data.get(postion).getOrderid();
                                String orderstatus = data.get(postion).getOrderstatus();
                                Intent intent = new Intent();
                                intent.putExtra("orderid",orderid);
                                intent.putExtra("orderstatus",orderstatus);
                                intent.setClass(getActivity(), OrderDetailActivity.class);
                                startActivity(intent);
                            }
                        });

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getActivity(),"访问网络失败，请检查您的网络！");

        }
    };


}
