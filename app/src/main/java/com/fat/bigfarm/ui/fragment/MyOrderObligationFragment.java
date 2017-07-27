package com.fat.bigfarm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.fat.bigfarm.entry.OrderEdit;
import com.fat.bigfarm.entry.PushOrder;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.activity.OrderDetailActivity;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 我的订单-待付款
 */
public class MyOrderObligationFragment extends BaseOrderFragment {
    private static final String TAG = "MyOrderObligationFragme";
    @BindView(R.id.rv_order_all)
    RecyclerView rvOrderAll;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;
    @BindView(R.id.fl_nomessage)
    FrameLayout fl_nomessage;
    private View view;
    private String userid;

    private List<MyOrderAll.DataBean> data;
    private MyOrderAll myOrderObligation;
    private OrderObligationAdapter orderAllAdapter;
    private KProgressHUD hud;

    private IWXAPI api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_order_obligation, container, false);

        ButterKnife.bind(this, view);

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        userid = TMApplication.instance.sp.getString("userid", "");
        PostOrderAll();

        iniView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void iniView(){
        //设置刷新的颜色
        swLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

                hud = KProgressHUD.create(getActivity())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

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
            request.add("status",1);

            // 添加到请求队列
            request(0, request, orderlistListener, true, true);
        }
    }

    private HttpListener<JSONObject> orderlistListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            scheduleDismiss();

            try {

                JSONObject js = response.get();
                Log.e(TAG, "orderlistListener: " + js);
                int code = js.getInt("code");
                if (code == 200){
                    myOrderObligation = JsonUtil.parseJsonToBean(js.toString(), MyOrderAll.class);
                    if (myOrderObligation != null){

                        data = myOrderObligation.getData();

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
                                String orderid = data.get(postion).getOrderid();
                                OrderEdit(orderid);
                            }

                            //付款
                            @Override
                            public void payonItemClick(Button bt_pay, int postion) {
//                                String orderid = data.get(postion).getOrderid();
//                                PostSuerOrder(orderid);
                                String orderid = data.get(postion).getOrderid();
                                Log.e(TAG, "rvonItemClick: "+orderid );
                                String orderstatus = data.get(postion).getOrderstatus();
                                Intent intent = new Intent();
                                intent.putExtra("orderid",orderid);
                                intent.putExtra("orderstatus",orderstatus);
                                intent.setClass(getActivity(), OrderDetailActivity.class);
                                startActivity(intent);
                            }

                            //item点击
                            @Override
                            public void rvonItemClick(OrderObligationItemAdapter orderObligationItemAdapter, int postion) {
                                String orderid = data.get(postion).getOrderid();
                                Log.e(TAG, "rvonItemClick: "+orderid );
                                String orderstatus = data.get(postion).getOrderstatus();
                                Intent intent = new Intent();
                                intent.putExtra("orderid",orderid);
                                intent.putExtra("orderstatus",orderstatus);
                                intent.setClass(getActivity(), OrderDetailActivity.class);
                                startActivity(intent);
                            }

                        });



                        orderAllAdapter.notifyDataSetChanged();

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            ToastUtil.showToast(getActivity(),"访问网络失败，请检查您的网络！");

        }
    };

    private void PostSuerOrder(String orderid){

        PostRequest tag = OkGo.post(AllUrl.PUSHORDER).tag(this);
        tag.params("dosubmit",1);
        tag.params("id",orderid);
        Log.e(TAG, "orderid: "+orderid );
        //way  支付方式  wx 微信支付 ali 支付宝支付
        tag.params("way","wx");


        tag.execute(new StringCallback() {

            private PushOrder.OrderBean order;
            private int code;

            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                PushOrder pushOrder = JsonUtil.parseJsonToBean(s.toString(), PushOrder.class);
                code = pushOrder.getCode();

                if (code == 200){
                    order = pushOrder.getOrder();

                    api = WXAPIFactory.createWXAPI(getActivity(), order.getAppid());
                    api.registerApp(order.getAppid());
                    PayReq req = new PayReq();
                    //应用ID
                    req.appId			= order.getAppid();
                    //商户号
                    req.partnerId		= order.getMch_id();
                    //预支付交易会话ID
                    req.prepayId		= order.getPrepay_id();
                    //随机字符串
                    req.nonceStr		= order.getNonce_str();
                    //时间戳
                    req.timeStamp		= String.valueOf(order.getTimestamp());
                    //扩展字段
                    req.packageValue	= "Sign=WXPay";
                    //签名
                    req.sign			= order.getSign();
//                    Toast.makeText(SuerOrderActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);


                }

            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                ToastUtil.showToast(getActivity(),"网络连接失败，请稍后再试");
            }
        });

    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }

    private void OrderEdit(String orderid){
        PostRequest tag = OkGo.post(AllUrl.ORDEREDIT).tag(this);
        tag.params("dosubmit",1);
        tag.params("status",5);
        tag.params("id",orderid);

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);
                OrderEdit orderEdit = JsonUtil.parseJsonToBean(s.toString(), OrderEdit.class);
                int code = orderEdit.getCode();
                if (code == 200){
                    ToastUtil.showToast(getActivity(),"取消订单成功");
                    hud = KProgressHUD.create(getActivity())
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                    hud.show();

                    PostOrderAll();
                }
            }
        });
    }


}
