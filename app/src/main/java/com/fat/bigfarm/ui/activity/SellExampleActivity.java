package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.Retention;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.QRCode;
import com.fat.bigfarm.utils.ToastUtil;
import com.google.zxing.WriterException;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收益转赠
 */
public class SellExampleActivity extends BaseActivity {

    private static final String TAG = "SellExampleActivity";

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
    @BindView(R.id.iv_code)
    ImageView ivCode;
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

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_example);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        String code = "http://www.9fat.com/H5test/farmapp0608/htmls/collectearn.html?id="+id;
        try {
            Bitmap bitmap = QRCode.Create2DCode(code);
            ivCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        GetEarningsDetails();

    }

    //收益详情
    private void GetEarningsDetails() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.INCOME_DETAILS + id);
        request(0, request, earningsDetailsListener, true, true);
    }

    private HttpListener<JSONObject> earningsDetailsListener = new HttpListener<JSONObject>() {


        private Retention.DataBean.FosterBean.IncomeBean income;
        private String content;
        private String thumb;
        private String name;
//        private List<Retention.DataBean.FosterBean.IncomeBean> income;
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
//                        if (income.size() == 1) {
                            rl1.setVisibility(View.VISIBLE);
                            tvCount1.setText(income.getCount());
                            tvUnit1.setText(income.getUnit());
                            tvName1.setText(income.getName());
//                        } else if (income.size() == 2) {
//                            rl1.setVisibility(View.VISIBLE);
//                            rl2.setVisibility(View.VISIBLE);
//                            tvCount1.setText(income.get(0).getCount());
//                            tvUnit1.setText(income.get(0).getUnit());
//                            tvName1.setText(income.get(0).getName());
//                            tvCount2.setText(income.get(1).getCount());
//                            tvUnit2.setText(income.get(1).getUnit());
//                            tvName2.setText(income.get(1).getName());
//                        } else {
//                            rl1.setVisibility(View.VISIBLE);
//                            rl2.setVisibility(View.VISIBLE);
//                            rl3.setVisibility(View.VISIBLE);
//                            tvCount1.setText(income.get(0).getCount());
//                            tvUnit1.setText(income.get(0).getUnit());
//                            tvName1.setText(income.get(0).getName());
//                            tvCount2.setText(income.get(1).getCount());
//                            tvUnit2.setText(income.get(1).getUnit());
//                            tvName2.setText(income.get(1).getName());
//                            tvCount3.setText(income.get(2).getCount());
//                            tvUnit3.setText(income.get(2).getUnit());
//                            tvName3.setText(income.get(2).getName());
//                        }

                        name = foster.getName();
                        tvEarningstitle.setText(name);
                        thumb = foster.getThumb();
                        Glide.with(getBaseContext()).load(thumb).crossFade().into(ivThumb);
                        content = foster.getContent();
                        tvContent.setText("收益：" + content);

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


    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }
}
