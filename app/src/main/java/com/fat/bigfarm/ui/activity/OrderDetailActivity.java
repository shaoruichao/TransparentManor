package com.fat.bigfarm.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.OrderDetailAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.OrderDetail;
import com.fat.bigfarm.entry.OrderEdit;
import com.fat.bigfarm.entry.PushOrder;
import com.fat.bigfarm.entry.PushOrderAli;
import com.fat.bigfarm.eventbus.MessageEvent;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.DataUtils;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.PayResult;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.JustifyTextView;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.fat.bigfarm.app.TMApplication.mContext;

/**
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity {

    private static final String TAG = "OrderDetailActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.rl_number)
    RelativeLayout rlNumber;
    @BindView(R.id.bt_cancelorder)
    Button btCancelorder;
    @BindView(R.id.bt_pay)
    Button btPay;
    @BindView(R.id.rl_operation)
    RelativeLayout rlOperation;
    @BindView(R.id.rv_order_detail)
    RecyclerView rv_order_detail;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.shippinginfor)
    RelativeLayout shippinginfor;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.rl_pay)
    RelativeLayout rlPay;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv_shoptotal)
    TextView tvShoptotal;
    @BindView(R.id.rl_shoptotal)
    RelativeLayout rlShoptotal;
    @BindView(R.id.tv6)
    JustifyTextView tv6;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.rl_freight)
    RelativeLayout rlFreight;
    @BindView(R.id.tv7)
    JustifyTextView tv7;
    @BindView(R.id.tv_realpay)
    TextView tvRealpay;
    @BindView(R.id.rl_realpay)
    RelativeLayout rlRealpay;
    @BindView(R.id.rl)
    LinearLayout rl;
    @BindView(R.id.tv_merchants)
    TextView tvMerchants;
    @BindView(R.id.bt_refund)
    Button btRefund;
    @BindView(R.id.rl_refund)
    RelativeLayout rlRefund;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv5_colon)
    TextView tv5Colon;
    @BindView(R.id.tv6_colon)
    TextView tv6Colon;
    @BindView(R.id.tv7_colon)
    TextView tv7Colon;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.rl_weixinpay)
    RelativeLayout rlWeixinpay;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv_ali)
    ImageView ivAli;
    @BindView(R.id.tv_ali)
    TextView tvAli;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.bt_sure)
    Button btSure;
    //    private String orderid;
    private String orderstatus;

    private String way = "";
    private String userid;
    private IWXAPI api;
    private String status;
    private static final int SDK_PAY_FLAG = 1;


    private String orderid;
    private String price;
    private String pay_way;
    private String freight;
    private String detail;
    private String province;
    private String country;
    private String city;
    private String telnumber;
    private String linkman;
    private OrderDetail.DataBean.AddressinfoBean addressinfo;
    private OrderDetailAdapter orderDetailAdapter;
    private List<OrderDetail.DataBean.GoodsinfoBean> goodsinfo;
    private String shopname;
    private String number;
    private String dateToString;
    private String creattime;
    private OrderDetail.DataBean.OrderinfoBean orderinfo;
    private OrderDetail.DataBean data;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(OrderDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                        for (int x=0 ; x< listcartid.size();x++){
//                            if (!listcartid.get(x).equals("")){
//                                PostShoppingcartEdit();
//                            }
//                        }
                        Intent intent = new Intent();
                        intent.putExtra("way", way);
                        intent.putExtra("result", price);
                        intent.setClass(getBaseContext(), PaySuccessfulActivity.class);
                        startActivity(intent);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
//                case SDK_AUTH_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
//                    String resultStatus = authResult.getResultStatus();
//
//                    // 判断resultStatus 为“9000”且result_code
//                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
//                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
//                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
//                        // 传入，则支付账户为该授权账户
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
//                                .show();
//                    } else {
//                        // 其他状态值则为授权失败
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
//
//                    }
//                    break;
//                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);

        TMApplication.instance.addActivity(this);

        //注册事件
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        tv6.setTitleWidth(tv5);
        tv7.setTitleWidth(tv5);

        userid = TMApplication.instance.sp.getString("userid", "");

        Intent intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        orderstatus = intent.getStringExtra("orderstatus");
        if (orderstatus.equals("1")) {
            rlRefund.setVisibility(View.GONE);
            tvCancle.setText("待付款");

            rlPay.setVisibility(View.GONE);
            rlShoptotal.setVisibility(View.GONE);
            rlFreight.setVisibility(View.GONE);
            rlRealpay.setVisibility(View.GONE);

        } else if (orderstatus.equals("2")) {
            tvCancle.setText("待发货");
            rlWeixinpay.setVisibility(View.GONE);
            rlAlipay.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            rlOperation.setVisibility(View.GONE);
            tvCancle.setVisibility(View.GONE);
        } else if (orderstatus.equals("3")) {
            tvCancle.setText("已发货");
            rlWeixinpay.setVisibility(View.GONE);
            rlAlipay.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
//            rlOperation.setVisibility(View.GONE);
            btCancelorder.setVisibility(View.GONE);
            btPay.setVisibility(View.GONE);
            btSure.setVisibility(View.VISIBLE);
            rlRefund.setVisibility(View.GONE);
        } else if (orderstatus.equals("4")) {
            tvCancle.setText("交易完成");
            rlWeixinpay.setVisibility(View.GONE);
            rlAlipay.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            rlOperation.setVisibility(View.GONE);
            tvCancle.setVisibility(View.GONE);
        } else if (orderstatus.equals("5")) {
            tvCancle.setText("已取消订单");
            rlWeixinpay.setVisibility(View.GONE);
            rlAlipay.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            rlOperation.setVisibility(View.GONE);
            rlRefund.setVisibility(View.GONE);
        }
        PostOrderDetail();

    }

    private void PostOrderDetail() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ORDERDETAIL, RequestMethod.POST);
        if (request != null) {
            request.add("id", orderid);
            request.add("dosubmit", 1);

            // 添加到请求队列
            request(0, request, orderdetailobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> orderdetailobjectListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "orderdetailobjectListener: " + js);
                int code = js.getInt("code");
                OrderDetail orderDetail = JsonUtil.parseJsonToBean(js.toString(), OrderDetail.class);
                if (code == 200) {
                    data = orderDetail.getData();
                    orderinfo = data.getOrderinfo();//订单状态
                    creattime = orderinfo.getCreattime();//下单时间
                    dateToString = DataUtils.getDateToString(Long.parseLong(creattime));
                    tvTime.setText(dateToString);
                    number = orderinfo.getNumber();//编号
                    tvNumber.setText(number);
                    pay_way = orderinfo.getPay_way();

                    price = orderinfo.getPrice();
                    tvShoptotal.setText("¥" + price);
                    tvTotalPrice.setText("¥" + price);
                    freight = orderinfo.getFreight();
                    tvFreight.setText("¥" + freight);


                    shopname = data.getShopname();
                    tvMerchants.setText(shopname);

                    goodsinfo = data.getGoodsinfo();
                    orderDetailAdapter = new OrderDetailAdapter(goodsinfo);
                    orderDetailAdapter.openLoadAnimation();
                    rv_order_detail.setAdapter(orderDetailAdapter);
                    rv_order_detail.setHasFixedSize(true);
                    rv_order_detail.setLayoutManager(new LinearLayoutManager(mContext));

                    addressinfo = data.getAddressinfo();//订单信息
//                    orderid = addressinfo.getId();
                    linkman = addressinfo.getLinkman();
                    tvName.setText(linkman);
                    telnumber = addressinfo.getTelnumber();
                    tvPhone.setText(telnumber);
                    province = addressinfo.getProvince();
                    city = addressinfo.getCity();
                    country = addressinfo.getCountry();
                    detail = addressinfo.getDetail();
                    tvAddress.setText(province + city + country + detail);


                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(), "访问网络失败，请检查您的网络！");

        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        Intent intent = new Intent();
        intent.putExtra("way", way);
        intent.putExtra("result", price);
        intent.setClass(getBaseContext(), PaySuccessfulActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.bt_back, R.id.rl_weixinpay, R.id.rl_alipay, R.id.bt_cancelorder, R.id.bt_pay, R.id.bt_refund,R.id.bt_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.rl_weixinpay:
                iv.setImageResource(R.drawable.dot_check);
                iv2.setImageResource(R.drawable.dot_uncheck);
                way = "wx";
                break;
            case R.id.rl_alipay:
                iv.setImageResource(R.drawable.dot_uncheck);
                iv2.setImageResource(R.drawable.dot_check);
                way = "ali";
                break;
            case R.id.bt_cancelorder:

                status = "5";
                OrderEdit(orderid,status);
                break;
            case R.id.bt_pay:

                if (way.equals("")) {
                    ToastUtil.showToast(getBaseContext(), "请选择支付方式");
                    return;
                } else if (way.equals("wx")) {
                    PostSuerOrder();
                } else if (way.equals("ali")) {
                    PostSuerOrderAli();
                }

                break;
            case R.id.bt_refund:
                break;
            case R.id.bt_sure:
                status = "4";
                OrderEdit(orderid,status);
                break;
        }
    }

    private void OrderEdit(String orderid,String status) {
        PostRequest tag = OkGo.post(AllUrl.ORDEREDIT).tag(this);
        tag.params("dosubmit", 1);
        tag.params("status", status);
        tag.params("id", orderid);

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);
                OrderEdit orderEdit = JsonUtil.parseJsonToBean(s.toString(), OrderEdit.class);
                int code = orderEdit.getCode();
                String msg = orderEdit.getMsg();
                if (code == 200) {
                    ToastUtil.showToast(getBaseContext(), msg);
                    finish();
                }
            }
        });
    }

    private void PostSuerOrder() {

        PostRequest tag = OkGo.post(AllUrl.PUSHORDER).tag(this);
        tag.params("dosubmit", 1);
        tag.params("id", orderid);
        Log.e(TAG, "orderid: " + orderid);
        //way  支付方式  wx 微信支付 ali 支付宝支付
        tag.params("way", way);


        tag.execute(new StringCallback() {

            private PushOrder.OrderBean order;
            private int code;

            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                PushOrder pushOrder = JsonUtil.parseJsonToBean(s.toString(), PushOrder.class);
                code = pushOrder.getCode();

                if (code == 200) {
                    order = pushOrder.getOrder();

                    api = WXAPIFactory.createWXAPI(getBaseContext(), order.getAppid());
                    api.registerApp(order.getAppid());
                    PayReq req = new PayReq();
                    //应用ID
                    req.appId = order.getAppid();
                    //商户号
                    req.partnerId = order.getMch_id();
                    //预支付交易会话ID
                    req.prepayId = order.getPrepay_id();
                    //随机字符串
                    req.nonceStr = order.getNonce_str();
                    //时间戳
                    req.timeStamp = String.valueOf(order.getTimestamp());
                    //扩展字段
                    req.packageValue = "Sign=WXPay";
                    //签名
                    req.sign = order.getSign();
//                    Toast.makeText(SuerOrderActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);


                }

            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                ToastUtil.showToast(getBaseContext(), "网络连接失败，请稍后再试");
            }
        });

    }

    private void PostSuerOrderAli() {

        PostRequest tag = OkGo.post(AllUrl.PUSHORDER).tag(this);
        tag.params("dosubmit", 1);
        tag.params("id", orderid);
        Log.e(TAG, "orderid: " + orderid);
        //way  支付方式  wx 微信支付 ali 支付宝支付
        tag.params("way", way);
        Log.e(TAG, "PostSuerOrderAli: " + way);

        tag.execute(new StringCallback() {

            private String order;
            private int code;

            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                PushOrderAli pushOrderAli = JsonUtil.parseJsonToBean(s.toString(), PushOrderAli.class);
                code = pushOrderAli.getCode();
                if (code == 200) {
                    order = pushOrderAli.getOrder();

                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(OrderDetailActivity.this);
                            Map<String, String> result = alipay.payV2(order, true);
                            Log.i("msp", result.toString());

                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };

                    Thread payThread = new Thread(payRunnable);
                    payThread.start();

                }

            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                ToastUtil.showToast(getBaseContext(), "网络连接失败，请稍后再试");
            }
        });

    }

}
