package com.fat.bigfarm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.Login;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.SharePreferencesUtils;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.ui.HomeActivity;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登录
public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
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
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.text2)
    TextInputLayout text2;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.tv_forgotpassword)
    TextView tvForgotpassword;
    @BindView(R.id.iv_next)
    ImageView ivNext;

    private KProgressHUD hud;

//    ListDataSave dataSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // 添加到Activity集合
        TMApplication.instance.addActivity(this);

//        dataSave = new ListDataSave(mContext, "list");

    }


    @OnClick({R.id.bt_back, R.id.tv_other, R.id.tv_forgotpassword, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.tv_other:
                startActivity(new Intent(this,OtherActivity.class));
                break;
            case R.id.tv_forgotpassword:
                startActivity(new Intent(this,ForgotPasswordActivity.class));
                break;
            case R.id.iv_next:
                Login();
                break;
        }
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

    //登录判断账号密码
    private void Login(){

        if (TextUtils.isEmpty(tvName.getText().toString()) || TextUtils.isEmpty(tvPassword.getText().toString())) {
            ToastUtil.showToast(getBaseContext(),"请输入帐号密码");
            return;
        } else {

            String name = tvName.getText().toString().trim();
            String password = tvPassword.getText().toString().trim();

            if (password.length()<6){
                ToastUtil.showToast(getBaseContext(),"密码必须超过6个字符");
                return;
            }

            hud = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
            hud.show();

            loging(name, password);
        }
    }

    //账号密码登录
    private void loging(final String name, final String password){

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.LOGIN, RequestMethod.POST);
        if (request != null){
            request.add("username", name);
            request.add("password",password);
            request.add("dosubmit",1);

            // 添加到请求队列
            request(0, request, logingobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> logingobjectListener = new HttpListener<JSONObject>() {

        private String sex;
        private String birthday;
        private String avatar;
        private String nickname;
        private String userid;
        private String username;
        private Login.DataBean data;
        private Login login;
        private String status;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            hud.dismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: "+js );
                int code = js.getInt("code");
                login = JsonUtil.parseJsonToBean(js.toString(), Login.class);

                if (code == 200){

                    if (login != null){
                        data = login.getData();
                        status = String.valueOf(login.getStatus());

                        nickname = data.getNickname();
                        userid = data.getUserid();
                        username = data.getUsername();
                        avatar = data.getAvatar();
                        birthday = data.getBirthday();
                        sex = data.getSex();

                        SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                        edit.putString("status", status);
                        edit.putString("nickname",nickname);
                        edit.putString("userid",userid);
                        edit.putString("username",username);
                        edit.putString("avatar",avatar);
                        edit.putString("birthday",birthday);
                        edit.putString("sex",sex);

                        edit.commit();

                        //保存请求到的数据
                        SharePreferencesUtils.putBean(getBaseContext(), "login",
                                login);
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        LoginActivity.this.finish();
                        TMApplication.instance.exit();
                    }

                }else {
                    ToastUtil.showToast(getBaseContext(),"登录失败");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");
            hud.dismiss();
        }
    };


}
