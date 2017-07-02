package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.OrderDetailAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.OrderDetail;
import com.fat.bigfarm.entry.OrderEdit;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.DataUtils;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.JustifyTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

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
    private String orderid;
    private String orderstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);

        tv6.setTitleWidth(tv5);
        tv7.setTitleWidth(tv5);

        Intent intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        orderstatus = intent.getStringExtra("orderstatus");
        if (orderstatus.equals("1")) {
            rlRefund.setVisibility(View.GONE);
            tvCancle.setVisibility(View.GONE);
        } else if (orderstatus.equals("2")) {
            rlOperation.setVisibility(View.GONE);
            tvCancle.setVisibility(View.GONE);
        } else if (orderstatus.equals("5")) {
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
                    orderid = addressinfo.getId();
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

    @OnClick({R.id.bt_back, R.id.bt_cancelorder, R.id.bt_pay, R.id.bt_refund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_cancelorder:

                OrderEdit(orderid);
                break;
            case R.id.bt_pay:
                break;
            case R.id.bt_refund:
                break;
        }
    }

    private void OrderEdit(String orderid) {
        PostRequest tag = OkGo.post(AllUrl.ORDEREDIT).tag(this);
        tag.params("dosubmit", 1);
        tag.params("status", 5);
        tag.params("id", orderid);

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);
                OrderEdit orderEdit = JsonUtil.parseJsonToBean(s.toString(), OrderEdit.class);
                int code = orderEdit.getCode();
                if (code == 200) {
                    ToastUtil.showToast(getBaseContext(), "取消订单成功");
                    finish();
                }
            }
        });
    }


}
