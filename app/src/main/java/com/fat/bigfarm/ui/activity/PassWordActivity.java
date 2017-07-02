package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.fat.bigfarm.entry.Login;
import com.fat.bigfarm.utils.SharePreferencesUtils;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.Register;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.HomeActivity;
import com.fat.bigfarm.utils.JsonUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//创建密码
public class PassWordActivity extends BaseActivity {

    private static final String TAG = "PassWordActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.text1)
    TextInputLayout text1;
    @BindView(R.id.iv_next)
    ImageView ivNext;

    private String code;
    private String phone;
    private String password;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        phone = intent.getStringExtra("phone");

        tvPhone.setText("密码必须包含至少一个符号，且长度至少在八到十六个字符之间。");

    }

    @OnClick({R.id.bt_back, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.iv_next:

                password = tvPassword.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showToast(getBaseContext(),"请输入密码");
                    return;
                }else if (!PassWord(password)) {
                    ToastUtil.showToast(getBaseContext(),"密码只能是数字、英文或者常用特殊字符");
                }else {
                    hud = KProgressHUD.create(this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                    hud.show();
                    getRegister();
                }

                break;
        }
    }

    private void getRegister(){

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.REGISTER, RequestMethod.POST);
        if (request != null){
            request.add("mobile", phone);
            request.add("password",password);
            request.add("dosubmit",1);
            request.add("mobile_verity",code);

            // 添加到请求队列
            request(0, request, registerobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> registerobjectListener = new HttpListener<JSONObject>() {

        private String avatar;
        private String status;
        private String userid;
        private String nickname;
        private String username;
        private String msg;
        private Register.DataBean data;
        private Register register;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            hud.dismiss();

            try {
                JSONObject js = response.get();
                Log.e(TAG, "registerobjectListener: "+js );
                int code = js.getInt("code");
                register = JsonUtil.parseJsonToBean(js.toString(), Register.class);

                if (code == 200){

                    if (register != null){
                        data = register.getData();
                        status = String.valueOf(register.getStatus());

                        nickname = data.getNickname();
                        userid = data.getUserid();
                        username = data.getUsername();
                        avatar = data.getAvatar();

                        SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                        edit.putString("status", status);
                        edit.putString("nickname",nickname);
                        edit.putString("userid",userid);
                        edit.putString("username",username);
                        edit.putString("avatar",avatar);

                        edit.commit();

                        startActivity(new Intent(PassWordActivity.this, HomeActivity.class));

                        PassWordActivity.this.finish();
                        TMApplication.instance.exit();
                    }

                }else if (code == -1){
//                    ToastUtil.showToast(getBaseContext(),"已经注册");
                    getFindPassword();
                }else {
                    ToastUtil.showToast(getBaseContext(),"注册失败");
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

    //找回密码
    private void getFindPassword(){

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.FIND_PASSWORD, RequestMethod.POST);
        if (request != null){
            request.add("mobile", phone);
            request.add("newpassword",password);
            request.add("dosubmit",1);
            request.add("mobile_verity",code);

            // 添加到请求队列
            request(0, request, findpasswordobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> findpasswordobjectListener = new HttpListener<JSONObject>() {

        private String sex;
        private String birthday;
        private String avatar;
        private String nickname;
        private String userid;
        private String username;
        private String msg;
        private Login.DataBean data;
        private Login login;
        private String status;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {
                JSONObject js = response.get();
                Log.e(TAG, "findpasswordobjectListener: "+js );
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
                        edit.putString("status",status);
                        edit.putString("nickname",nickname);
                        edit.putString("userid",userid);
                        edit.putString("username",username);
                        edit.putString("avatar",avatar);
                        edit.putString("birthday",birthday);
                        edit.putString("sex",sex);

                        edit.commit();
                        //保存请求到的数据
//                        SharePreferencesUtils.putBean(getBaseContext(), "login",
//                                login);
                        startActivity(new Intent(PassWordActivity.this, HomeActivity.class));
                        PassWordActivity.this.finish();
                        TMApplication.instance.exit();
                    }

                }else {
                    ToastUtil.showToast(getBaseContext(),"找回密码失败，请重新找回");
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



    /**
     * 密码格式
     *
     * @param password
     * @return
     */
    public boolean PassWord(String password) {
        Pattern p = Pattern.compile("[0-9a-zA-Z.]{6,20}");
        Matcher m = p.matcher(password);
        return m.matches();
    }

}
