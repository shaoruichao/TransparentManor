package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.Retention;
import com.fat.bigfarm.entry.UserMessage;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.SharePreferencesUtils;
import com.fat.bigfarm.utils.ToastUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收益自留
 */
public class RetentionActivity extends BaseActivity {

    private static final String TAG = "RetentionActivity";
    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_count1)
    TextView tvCount1;
    @BindView(R.id.tv_unit1)
    TextView tvUnit1;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv_count2)
    TextView tvCount2;
    @BindView(R.id.tv_unit2)
    TextView tvUnit2;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv_count3)
    TextView tvCount3;
    @BindView(R.id.tv_unit3)
    TextView tvUnit3;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv)
    TextView tv;
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
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_newname)
    EditText tvNewname;
    @BindView(R.id.tv_newphone)
    EditText tvNewphone;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_newaddress)
    EditText tvNewaddress;
    @BindView(R.id.rl_goodsinfor)
    RelativeLayout rlGoodsinfor;
    @BindView(R.id.tv_shopname)
    TextView tvShopname;
    @BindView(R.id.iv_thumb)
    ImageView ivThumb;
    @BindView(R.id.tv_earningstitle)
    TextView tvEarningstitle;
    @BindView(R.id.tv_meal)
    TextView tvMeal;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bt_sure)
    Button btSure;

    private String id;

    private UserMessage userMessage;
    private List<UserMessage.DataBean.AddressBean> address;
    private String addrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retention);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        GetEarningsDetails();
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

                rlGoodsinfor.setVisibility(View.GONE);
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
                rlGoodsinfor.setVisibility(View.VISIBLE);
                shippinginfor.setVisibility(View.GONE);
            }
        }

    }

    //收益详情
    private void GetEarningsDetails() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.INCOME_DETAILS + id);
        request(0, request, earningsDetailsListener, true, true);
    }

    private HttpListener<JSONObject> earningsDetailsListener = new HttpListener<JSONObject>() {


        private String unit;
        private String price;
        private String content;
        private String thumb;
        private String name;
        private List<Retention.DataBean.FosterBean.IncomeBean> income;
        private Retention.DataBean.FosterBean foster;
        private String shopname;
        private Retention.DataBean data;
        private Retention retention;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {

                JSONObject js = response.get();
                Log.e(TAG, "earningsDetailsListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    retention = JsonUtil.parseJsonToBean(js.toString(), Retention.class);
                    if (retention != null) {

                        data = retention.getData();
                        shopname = data.getShopname();
                        tvShopname.setText(shopname);

                        foster = data.getFoster();
                        income = foster.getIncome();
                        if (income.size() == 1) {
                            rl1.setVisibility(View.VISIBLE);
                            tvCount1.setText(income.get(0).getCount());
                            tvUnit1.setText(income.get(0).getUnit());
                            tvName1.setText(income.get(0).getName());
                        } else if (income.size() == 2) {
                            rl1.setVisibility(View.VISIBLE);
                            rl2.setVisibility(View.VISIBLE);
                            tvCount1.setText(income.get(0).getCount());
                            tvUnit1.setText(income.get(0).getUnit());
                            tvName1.setText(income.get(0).getName());
                            tvCount2.setText(income.get(1).getCount());
                            tvUnit2.setText(income.get(1).getUnit());
                            tvName2.setText(income.get(1).getName());
                        } else {
                            rl1.setVisibility(View.VISIBLE);
                            rl2.setVisibility(View.VISIBLE);
                            rl3.setVisibility(View.VISIBLE);
                            tvCount1.setText(income.get(0).getCount());
                            tvUnit1.setText(income.get(0).getUnit());
                            tvName1.setText(income.get(0).getName());
                            tvCount2.setText(income.get(1).getCount());
                            tvUnit2.setText(income.get(1).getUnit());
                            tvName2.setText(income.get(1).getName());
                            tvCount3.setText(income.get(2).getCount());
                            tvUnit3.setText(income.get(2).getUnit());
                            tvName3.setText(income.get(2).getName());
                        }

                        name = foster.getName();
                        tvEarningstitle.setText(name);
                        thumb = foster.getThumb();
                        Glide.with(getBaseContext()).load(thumb).crossFade().into(ivThumb);
                        content = foster.getContent();
                        price = foster.getPrice();
                        unit = foster.getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        tvContent.setText("¥"+price_point+"元/"+unit);

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(getBaseContext(), "请求网络失败，请稍后重试");
        }
    };

    @OnClick({R.id.bt_back, R.id.bt_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_sure:

                PostIncomeEdit();
                break;
        }
    }

    private void PostIncomeEdit(){


        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.INCOME_EDIT, RequestMethod.POST);
        if (request != null){
            request.add("id",id);
            request.add("status","7");
            request.add("addrid",addrid);

            // 添加到请求队列
            request(0, request, incomeEditListener, true, true);
        }
    }

    private HttpListener<JSONObject> incomeEditListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {

                JSONObject js = response.get();
                Log.e(TAG, "incomeEditListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {

                    finish();
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
