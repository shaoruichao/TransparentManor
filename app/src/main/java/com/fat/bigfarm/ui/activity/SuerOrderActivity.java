package com.fat.bigfarm.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.SureOrderAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.bean.GroupInfo;
import com.fat.bigfarm.bean.ProductInfo;
import com.fat.bigfarm.entry.PushOrder;
import com.fat.bigfarm.entry.PushOrderAli;
import com.fat.bigfarm.entry.UserMessage;
import com.fat.bigfarm.entry.WX_order;
import com.fat.bigfarm.eventbus.MessageEvent;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.DataUtils;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.PayResult;
import com.fat.bigfarm.utils.SharePreferencesUtils;
import com.fat.bigfarm.utils.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.GetRequest;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

//确认订单
public class SuerOrderActivity extends BaseActivity {

    private static final String TAG = "SuerOrderActivity";

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
    @BindView(R.id.rv_homemore)
    RecyclerView rv_homemore;
    @BindView(R.id.rl_goodes)
    RelativeLayout rlGoodes;
    @BindView(R.id.tv_pastage)
    TextView tvPastage;
    @BindView(R.id.tv_pastage_price)
    TextView tvPastagePrice;
    @BindView(R.id.tv_pay)
    TextView tvPay;
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
    @BindView(R.id.rl_postage)
    RelativeLayout rlPostage;
    @BindView(R.id.rl_pay)
    RelativeLayout rlPay;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.iv_myorder)
    ImageView ivMyorder;
    @BindView(R.id.rl_shippingadress)
    RelativeLayout rlShippingadress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.shippinginfor)
    RelativeLayout shippinginfor;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rl_noaddress)
    RelativeLayout rlNoaddress;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;

    private boolean mark = true;


    private List<ProductInfo> productLists = new ArrayList<>();
    private SureOrderAdapter sureOrderAdapter;

    private UserMessage userMessage;
    private List<UserMessage.DataBean.AddressBean> address;
    private String result;
    private String userid;
    private String addrid;

    private String ss = "[";
    private JSONArray myJsonArray;
    private JSONObject myjObject;
    private JSONObject jsonArray;

    private IWXAPI api;

    private String way = "";

    private ArrayList<ProductInfo> listforAdapter;
    private ArrayList<String> listcartid;

    private static final int SDK_PAY_FLAG = 1;


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
                        Toast.makeText(SuerOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("way",way);
                        intent.putExtra("result",result);
                        intent.setClass(getBaseContext(),PaySuccessfulActivity.class);
                        startActivity(intent);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(SuerOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

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
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suer_order);
        ButterKnife.bind(this);

        //注册事件
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        TMApplication.instance.addActivity(this);

        productLists = (List<ProductInfo>) getIntent().getSerializableExtra("productLists");

        ArrayList<ProductInfo> shopEntriesForDel = new ArrayList<>();
        shopEntriesForDel.addAll(productLists);//将原有资源加进去 和赋值一样 直接==也行

        ArrayList<ProductInfo> listforAdd = new ArrayList<>();//专门用来中转的集合
        ArrayList<ArrayList<ProductInfo>> arrayLists = new ArrayList<>();//装载数据的集合数据的集合

        listcartid = new ArrayList<>();//存放cartid删除id

        Log.e(TAG, "productLists: "+productLists );
        for (int y=0 ; y< productLists.size();y++){
            String sid = productLists.get(y).getSid();
            //删除id
            String cartid = productLists.get(y).getCartid();
            listcartid.add(cartid);


            listforAdd = new ArrayList<>();//集合每次进来都要初始化
            for (int i = 0; i < shopEntriesForDel.size(); i++) {//内部循环 只要找到商品id一样的就加载进到listforadd
                String shopIDDel = shopEntriesForDel.get(i).getSid();//对比商品id
                if (shopIDDel.equals(sid)) {
                    ProductInfo remove = shopEntriesForDel.remove(i);//直接删除同样商品id的商品会返回删除的对象 加入listforadd
                    listforAdd.add(remove);//加入集合
                    --i;//角标--为了不角标越界
                }
            }
            arrayLists.add(listforAdd);//将listfotadd加入最大集合


            String s = "[";
            for (int x=0 ; x< productLists.size();x++){
                String sids = productLists.get(x).getSid();
                String ids = productLists.get(x).getId();
                int counts = productLists.get(x).getCount();

                if (sid.equals(sids)){
                    s+="{\""+ids+"\":\""+counts+"\"},";

                }
            }
            s = s.substring(0, s.length()-1);
            s +="]";

            ss+="{\"goodsinfo\":"+s+",\"sid\":"+sid+",\"freight\":" + 10 + "},";

        }

        listforAdapter = new ArrayList<>();//为适配器准备的集合

        for (int index = 0; index < arrayLists.size(); index++) {//循环资源集合

            ArrayList<ProductInfo> list = arrayLists.get(index);//取出集合中的
            for (int z = 0; z < list.size(); z++) {//循环小集合 将第一个设置为top为true
                ProductInfo shopEntry = list.get(z);
                if (z == 0) {  //每个小集合第一个
                    shopEntry.setTop(true);//设置为true
                }
                listforAdapter.add(shopEntry);//加入大集合
            }
        }

        ss = ss.substring(0, ss.length()-1);
        ss += "]";
        Log.e(TAG, "onCreate: "+ss );

        try {
            myJsonArray = new JSONArray(ss);
            Log.e(TAG, "onCreatemyJsonArray "+myJsonArray.length() );


            for(int i=0 ; i < myJsonArray.length() ;i++)
            {
//获取每一个JsonObject对象
                myjObject = myJsonArray.getJSONObject(i);
                jsonArray = myJsonArray.getJSONObject(i);

                JSONArray goodsinfo = myjObject.getJSONArray("goodsinfo");

                Log.e(TAG, "onCreate: "+goodsinfo.length() );

                if (myjObject.getString("sid").equals(jsonArray.getString("sid"))){
                    if (goodsinfo.length() != 1){
                        myJsonArray.remove(i);
                    }

                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "onCreate: "+myJsonArray );

        Log.e(TAG, "onCreate: "+listforAdapter );

        sureOrderAdapter = new SureOrderAdapter(listforAdapter);

        sureOrderAdapter.openLoadAnimation();

        rv_homemore.setAdapter(sureOrderAdapter);

        rv_homemore.setHasFixedSize(true);
        rv_homemore.setLayoutManager(new LinearLayoutManager(this));

        result = getIntent().getStringExtra("result");
        double v = Double.parseDouble(result);
        String totalPrice = String.format("%.2f", v);
        tvTotalPrice.setText("¥" + totalPrice);

        userid = TMApplication.instance.sp.getString("userid", "");

        userMessage = (UserMessage) SharePreferencesUtils.getBean(
                getBaseContext(), "userMessage");
        Log.e(TAG, "onCreate: "+userMessage );

    }


    @Override
    protected void onResume() {
        super.onResume();

        userMessage = (UserMessage) SharePreferencesUtils.getBean(
                getBaseContext(), "userMessage");
        Log.e(TAG, "onResume: " + userMessage);
        if (userMessage != null) {
            address = userMessage.getData().getAddress();
            if (!address.isEmpty()) {

                rlNoaddress.setVisibility(View.GONE);
                shippinginfor.setVisibility(View.VISIBLE);

                addrid = address.get(0).getId();
                String linkman = address.get(0).getLinkman();
                tvName.setText(linkman);
                String telnumber = address.get(0).getTelnumber();
                tvPhone.setText(telnumber);
                String province = address.get(0).getProvince();
                String city = address.get(0).getCity();
                String country = address.get(0).getCountry();
                String detail = address.get(0).getDetail();
                tvAddress.setText(province + city + country + detail);


            } else {
                rlNoaddress.setVisibility(View.VISIBLE);
                shippinginfor.setVisibility(View.GONE);
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        Intent intent = new Intent();
        intent.putExtra("way",way);
        intent.putExtra("result",result);
        intent.setClass(getBaseContext(),PaySuccessfulActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.bt_back, R.id.rl_weixinpay, R.id.rl_alipay, R.id.rl_pay, R.id.rl_shippingadress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_shippingadress:

                if (address.isEmpty()) {
                    startActivity(new Intent(this, AddressActivity.class));
                } else {
                    startActivity(new Intent(this, AddressManageActivity.class));
                }

                break;
            case R.id.bt_back:
                productLists.clear();
                rv_homemore.removeAllViews();
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
            case R.id.rl_pay:

                if (!address.isEmpty()){
                    if (way.equals("")){
                        ToastUtil.showToast(getBaseContext(),"请选择支付方式");
                        return;
                    }else if (way.equals("wx")){
                        PostSuerOrder();
                    }else if (way.equals("ali")){
                        PostSuerOrderAli();
                    }

                }else {
                    ToastUtil.showToast(getBaseContext(),"请填写收货地址");
                }


                break;
        }
    }

    private void PostSuerOrder(){

        PostRequest tag = OkGo.post(AllUrl.PUSHORDER).tag(this);
        tag.params("dosubmit",1);
        tag.params("uid", userid);
        Log.e(TAG, "uid: "+userid );
        tag.params("addrid",addrid);
        Log.e(TAG, "addrid: "+addrid );
        tag.params("way",way);

        for (int x=0 ; x< myJsonArray.length();x++){

            try {
                JSONObject myjObject = myJsonArray.getJSONObject(x);

                Log.e(TAG, "x: "+x );
                tag.params("orderinfo"+"["+x+"]"+"["+"sid"+"]", myjObject.getString("sid"));
                Log.e(TAG, "orderinfosid: "+myjObject.getString("sid") );
                tag.params("orderinfo"+"["+x+"]"+"["+"freight"+"]", myjObject.getString("freight"));

                JSONArray goodsinfo = myjObject.getJSONArray("goodsinfo");

                for (int q = 0; q < goodsinfo.length();q++){
                    Log.e(TAG, "q: "+q );
                    String goodsinfoid = goodsinfo.getString(q);
                    String subgoodsinfoid = goodsinfoid.substring(2, goodsinfoid.indexOf(":") - 1);
                    String subsubgoodsinfocount = goodsinfoid.substring(goodsinfoid.indexOf(":") + 2, goodsinfoid.indexOf("}") - 1);

                    tag.params("orderinfo"+"["+x+"]"+"["+"goodsinfo"+"]"+"["+subgoodsinfoid+"]", subsubgoodsinfocount);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

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

                    api = WXAPIFactory.createWXAPI(getBaseContext(), order.getAppid());
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

//                    EventBus.getDefault().post(new MessageEvent(result));
//                    PostShoppingcartEdit();

                }


            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                ToastUtil.showToast(getBaseContext(),"网络连接失败，请稍后再试");
            }
        });

    }

    private void PostSuerOrderAli(){

        PostRequest tag = OkGo.post(AllUrl.PUSHORDER).tag(this);
        tag.params("dosubmit",1);
        tag.params("uid", userid);
        Log.e(TAG, "uid: "+userid );
        tag.params("addrid",addrid);
        Log.e(TAG, "addrid: "+addrid );
        tag.params("way",way);

        for (int x=0 ; x< myJsonArray.length();x++){

            try {
                JSONObject myjObject = myJsonArray.getJSONObject(x);

                Log.e(TAG, "x: "+x );
                tag.params("orderinfo"+"["+x+"]"+"["+"sid"+"]", myjObject.getString("sid"));
                Log.e(TAG, "orderinfosid: "+myjObject.getString("sid") );
                tag.params("orderinfo"+"["+x+"]"+"["+"freight"+"]", myjObject.getString("freight"));

                JSONArray goodsinfo = myjObject.getJSONArray("goodsinfo");

                for (int q = 0; q < goodsinfo.length();q++){
                    Log.e(TAG, "q: "+q );
                    String goodsinfoid = goodsinfo.getString(q);
                    String subgoodsinfoid = goodsinfoid.substring(2, goodsinfoid.indexOf(":") - 1);
                    String subsubgoodsinfocount = goodsinfoid.substring(goodsinfoid.indexOf(":") + 2, goodsinfoid.indexOf("}") - 1);

                    tag.params("orderinfo"+"["+x+"]"+"["+"goodsinfo"+"]"+"["+subgoodsinfoid+"]", subsubgoodsinfocount);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        tag.execute(new StringCallback() {

            private String order;
            private int code;

            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                PushOrderAli pushOrderAli = JsonUtil.parseJsonToBean(s.toString(), PushOrderAli.class);
                code = pushOrderAli.getCode();
                if (code == 200){
                    order = pushOrderAli.getOrder();

                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(SuerOrderActivity.this);
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
//                PushOrder pushOrder = JsonUtil.parseJsonToBean(s.toString(), PushOrder.class);
//                code = pushOrder.getCode();
//
//                if (code == 200){
//                    order = pushOrder.getOrder();
//
//                    api = WXAPIFactory.createWXAPI(getBaseContext(), order.getAppid());
//                    api.registerApp(order.getAppid());
//                    PayReq req = new PayReq();
//                    //应用ID
//                    req.appId			= order.getAppid();
//                    //商户号
//                    req.partnerId		= order.getMch_id();
//                    //预支付交易会话ID
//                    req.prepayId		= order.getPrepay_id();
//                    //随机字符串
//                    req.nonceStr		= order.getNonce_str();
//                    //时间戳
//                    req.timeStamp		= String.valueOf(order.getTimestamp());
//                    //扩展字段
//                    req.packageValue	= "Sign=WXPay";
//                    //签名
//                    req.sign			= order.getSign();
////                    Toast.makeText(SuerOrderActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//                    api.sendReq(req);
//
//                    PostShoppingcartEdit();
//
//                }

            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                ToastUtil.showToast(getBaseContext(),"网络连接失败，请稍后再试");
            }
        });

    }

    //购物车编辑
    private void PostShoppingcartEdit(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SHOPPINGCATEDIT, RequestMethod.POST);
        if (request != null) {

            for (int x=0 ; x< listcartid.size();x++){
                request.add("delete"+"["+x+"]", listcartid.get(x));
                Log.e(TAG, "onActivityResult2222222: "+listcartid.get(x) );
            }

            request.add("dosubmit", 1);

            // 添加到请求队列
            request(0, request, carteditListener, true, true);
        }
    }

    private HttpListener<JSONObject> carteditListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "carteditListener: " + js);
                int code = js.getInt("code");
                String mgs = js.getString("msg");
                if (code == 200){
                    ToastUtil.showToast(getBaseContext(),mgs);
                }else {
                    ToastUtil.showToast(getBaseContext(),mgs);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");

        }
    };

}
