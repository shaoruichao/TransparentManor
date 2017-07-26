package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.entry.SendPhone;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册
public class RegisterActivity extends BaseActivity {


    private static final String TAG = "RegisterActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.text1)
    TextInputLayout text1;
    @BindView(R.id.iv_next)
    ImageView ivNext;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        TMApplication.instance.addActivity(this);

    }

    @OnClick({R.id.bt_back, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.iv_next:

                phone = tvName.getText().toString();
                Log.e(TAG, "GetSendPhone: "+phone );

                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.showToast(getBaseContext(),"请输入手机号");
                    return;
                } else if (!isMobileNO(phone)) {
                    ToastUtil.showToast(getBaseContext(),"手机格式不对");
                    return;
                }
                GetSendPhone(phone);
                break;
        }
    }

    //发送手机号(注册)
    private void GetSendPhone(String phone){

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SEND_PHONE+ phone);
        request(0, request, sengphonobjectListener, true, true);

    }

    private HttpListener<JSONObject> sengphonobjectListener = new HttpListener<JSONObject>() {

        private int return_id;
        private SendPhone sendPhone;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: "+js );
                int code = js.getInt("code");
                String msg = js.getString("msg");
                if (code == 200 ){
//                    sendPhone = JsonUtil.parseJsonToBean(js.toString(), SendPhone.class);
//                    return_id = sendPhone.getReturn_id();
//                    ToastUtil.showToast(getBaseContext(),String.valueOf(return_id));
//                    Log.e(TAG, "onSuccess110: "+return_id );

                    Intent intent = new Intent();
//                    intent.putExtra("return_id",String.valueOf(return_id));
                    intent.putExtra("phone",phone);
                    intent.setClass(getBaseContext(),CodeActivity.class);
                    startActivity(intent);


                }else {
                    ToastUtil.showToast(getBaseContext(),msg);
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

    /*
         * 手机格式 String的值只读序列
         */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
