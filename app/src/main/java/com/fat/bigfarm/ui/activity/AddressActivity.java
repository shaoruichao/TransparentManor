package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.AddressList;
import com.fat.bigfarm.entry.Login;
import com.fat.bigfarm.entry.UserMessage;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.HomeActivity;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.SharePreferencesUtils;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.addresspick.AddressPickTask;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;

//新建地址
public class AddressActivity extends BaseActivity {

    private static final String TAG = "AddressActivity";

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
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.rl_goodsinfor)
    RelativeLayout rlGoodsinfor;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.rl_default)
    RelativeLayout rlDefault;
    @BindView(R.id.bt_save)
    Button btSave;

    private boolean mark = true;
    private String status = "1";
    private String userid;
    private String addprovince;
    private String addcity;
    private String addcountry;
    private AddressList.DataBean dataBean;
    private String id;//收货id
    private String dataBeanstatus;//是否为默认地址的判断值

//    private String province;
//    private String city;
//    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);

        userid = TMApplication.instance.sp.getString("userid", "");

        Intent intent = getIntent();
        dataBean = (AddressList.DataBean) intent.getSerializableExtra("dataBean");
        Log.e(TAG, "onCreate: "+dataBean );
        if (dataBean != null){
            String linkman = dataBean.getLinkman();
            tvName.setText(linkman);
            String telnumber = dataBean.getTelnumber();
            tvPhone.setText(telnumber);
            addprovince = dataBean.getProvince();
            addcity = dataBean.getCity();
            addcountry = dataBean.getCountry();
            tvArea.setText(addprovince + addcity + addcountry);
            String detail = dataBean.getDetail();
            tvAddress.setText(detail);
            dataBeanstatus = dataBean.getStatus();
            if (dataBeanstatus.equals("2")){
                iv.setImageResource(R.drawable.dot_check);
                status = "2";
                mark = false;
                rlDefault.setClickable(false);
            }else {
                iv.setImageResource(R.drawable.dot_uncheck);
                status = "1";
                mark = true;
            }
            id = dataBean.getId();
        }

    }

    @OnClick({R.id.bt_back, R.id.tv_area, R.id.rl_default, R.id.bt_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.tv_area:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
//                        showToast("数据初始化失败");
                        ToastUtil.showToast(getBaseContext(),"数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            ToastUtil.showToast(getBaseContext(),province.getAreaName() + city.getAreaName());
                        } else {
//                            ToastUtil.showToast(getBaseContext(),province.getAreaName() + city.getAreaName() + county.getAreaName());
                            addprovince = province.getAreaName();
                            addcity = city.getAreaName();
                            addcountry = county.getAreaName();
                            tvArea.setText(addprovince+addcity+addcountry);

                        }
                    }
                });
                task.execute("北京", "北京", "东城区");

                break;
            case R.id.rl_default:

                if (mark){
                    iv.setImageResource(R.drawable.dot_check);
                    status = "2";
                    mark = false;
                }else {
                    iv.setImageResource(R.drawable.dot_uncheck);
                    status = "1";
                    mark = true;
                }

                break;
            case R.id.bt_save:

                if (TextUtils.isEmpty(tvName.getText().toString())){
                    ToastUtil.showToast(getBaseContext(),"请输入您的姓名");
                    return;
                }else if (TextUtils.isEmpty(tvPhone.getText().toString())){
                    ToastUtil.showToast(getBaseContext(),"请输入您的手机号");
                    return;
                }else if (TextUtils.isEmpty(tvAddress.getText().toString())){
                    ToastUtil.showToast(getBaseContext(),"请输入您的详细地址");
                    return;
                }else {

                    if (id != null && !id.equals("")){
                        PostAddressManage(id);
                    }else {
                        PostAddAddress();
                    }

                }

                break;
        }
    }

    private void PostAddressManage(String id){
        String linkman = tvName.getText().toString();
        String detail = tvAddress.getText().toString();
        String telnumber = tvPhone.getText().toString();

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADRESSEDIT, RequestMethod.POST);
        if (request != null){
            request.add("dosubmit",1);
            request.add("uid",userid);
            request.add("id",id);
            request.add("province",addprovince);
            request.add("city",addcity);
            request.add("country",addcountry);
            request.add("detail",detail);
            request.add("linkman",linkman);
            request.add("telnumber",telnumber);
            request.add("status",status);


            // 添加到请求队列
            request(0, request, addressshanobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> addressshanobjectListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            getUserMessage();

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");

        }
    };

    private void PostAddAddress(){

        String linkman = tvName.getText().toString();
        String detail = tvAddress.getText().toString();
        String telnumber = tvPhone.getText().toString();

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADDADRESS, RequestMethod.POST);
        if (request != null){
            request.add("dosubmit",1);
            request.add("uid",userid);
            request.add("province",addprovince);
            request.add("city",addcity);
            request.add("country",addcountry);
            request.add("detail",detail);
            request.add("linkman",linkman);
            request.add("telnumber",telnumber);
            request.add("status",status);

            // 添加到请求队列
            request(0, request, addressobjectListener, true, true);
        }

    }

    private HttpListener<JSONObject> addressobjectListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            getUserMessage();

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");

        }
    };

    //获取用户信息
    private void getUserMessage(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.USERMESSAGE);
        request(0, request, userMessageListener, true, true);
    }
    private HttpListener<JSONObject> userMessageListener = new HttpListener<JSONObject>() {

        private UserMessage.DataBean data;
        private String avatar;
        private String uid;
        private String nickname;
        private String userName;
        private String status;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {

                JSONObject js = response.get();
                Log.e(TAG, "usermessage: "+js );
                status = String.valueOf(js.getInt("status"));
                UserMessage userMessage = JsonUtil.parseJsonToBean(js.toString(), UserMessage.class);

                if (status.equals("1")){

                    if (userMessage != null){
                        data = userMessage.getData();
                        avatar = data.getAvatar();
                        userName = data.getUserName();
                        nickname = data.getNickname();
                        uid = data.getUid();

                        SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                        edit.putString("status", status);
                        edit.putString("userid", uid);
                        edit.putString("avatar", avatar);
                        edit.putString("nickname", nickname);
                        edit.putString("userName", userName);
                        edit.commit();

                        //保存请求到的数据
                        SharePreferencesUtils.putBean(getBaseContext(), "userMessage",
                                userMessage);

                        finish();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(getBaseContext(),"请求网络失败，请稍后重试");
        }
    };

}
