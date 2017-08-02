package com.fat.bigfarm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.SendPhone;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//忘记密码
public class ForgotPasswordActivity extends BaseActivity {

    private static final String TAG = "ForgotPasswordActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.text1)
    TextInputLayout text1;
    @BindView(R.id.iv_next)
    ImageView ivNext;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        // 添加到Activity集合
        TMApplication.instance.addActivity(this);

        tvTitle.setText("请输入您的手机号码以查找您的账号");

    }

    //隐藏软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @OnClick({R.id.bt_back, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.iv_next:

                phone =  tvPhone.getText().toString();
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
