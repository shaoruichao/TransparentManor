package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.Earnings;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收益出售
 */
public class SellActivity extends BaseActivity {

    private static final String TAG = "SellActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
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
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.bt_sell)
    EditText btSell;
    @BindView(R.id.bt_retention)
    EditText btRetention;
    @BindView(R.id.rl_operation)
    RelativeLayout rlOperation;
    @BindView(R.id.bt_sure)
    Button btSure;
    private Earnings.DataBean.FosterBean dataBean;
    private String name;
    private String thumb;
    private Earnings.DataBean.FosterBean.IncomeBean income;
    private String count;
    private String shopname;
    private String content;
    private String sellcount;
    private String id;
    private String sellprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        shopname = intent.getStringExtra("shopname");
        thumb = intent.getStringExtra("thumb");
        name = intent.getStringExtra("name");
        content = intent.getStringExtra("content");
        count = intent.getStringExtra("count");
        Log.e(TAG, "onCreate: "+count );
        tvShopname.setText(shopname);
        Glide.with(getBaseContext()).load(thumb).crossFade().into(ivThumb);
        tvEarningstitle.setText(name);
        tvContent.setText("收益："+content);

    }

    @OnClick({R.id.bt_back, R.id.bt_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_sure:

                sellcount = btRetention.getText().toString();
                sellprice = btSell.getText().toString();

                if (TextUtils.isEmpty(sellcount)||TextUtils.isEmpty(sellprice)){
                    ToastUtil.showToast(getBaseContext(),"请输入出售价格和数量");
                    return;
                }

                if (Integer.parseInt(sellcount)>Integer.parseInt(count)){
                    ToastUtil.showToast(getBaseContext(),"出售不可超出收益数量");
                    return;
                }

                PostIncomeEdit(id);
                break;
        }
    }

    private void PostIncomeEdit(String id){


        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.INCOME_EDIT, RequestMethod.POST);
        if (request != null){
            request.add("id", id);
            request.add("status","3");
            request.add("price",sellprice);
            Log.e(TAG, "PostIncomeEdit: "+sellprice );
            request.add("count",sellcount);
            Log.e(TAG, "PostIncomeEdit: "+sellcount );

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
