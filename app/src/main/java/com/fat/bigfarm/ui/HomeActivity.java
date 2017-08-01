package com.fat.bigfarm.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseFragmentActivity;
import com.fat.bigfarm.entry.UserMessage;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.activity.StatusActivity;
import com.fat.bigfarm.ui.fragment.ActivitiesFragment;
import com.fat.bigfarm.ui.fragment.HomeFragment;
import com.fat.bigfarm.ui.fragment.MyFragment;
import com.fat.bigfarm.ui.fragment.MyfarmFragment;
import com.fat.bigfarm.ui.fragment.ShoppingFragment;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ListDataSave;
import com.fat.bigfarm.utils.SharePreferencesUtils;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.MainNavigateTabBar;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseFragmentActivity {

    private static final String TAG = "HomeActivity";

    private static final String TAG_PAGE_HOME = "首页";
    //    private static final String TAG_PAGE_PRODUCT = "新品";
    private static final String TAG_PAGE_ACTIVITIES = "菜园";
    private static final String TAG_PAGE_SHOPPING = " 购物车";
    private static final String TAG_PAGE_MYFARM = "我的农庄";
    private static final String TAG_PAGE_PERSON = "我的";
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.rl_home)
    RelativeLayout rlHome;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.rl_garden)
    RelativeLayout rlGarden;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl_shopping)
    RelativeLayout rlShopping;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.rl_farm)
    RelativeLayout rlFarm;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.rl_my)
    RelativeLayout rlMy;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;

    private long mExitTime;

    @BindView(R.id.main_container)
    FrameLayout mainContainer;
//    @BindView(R.id.mainTabBar)
//    MainNavigateTabBar mainTabBar;
    @BindView(R.id.webView)
    WebView webView;

    private UserMessage.DataBean data;
    private String avatar;
    private String uid;
    private String nickname;
    private String userName;
    private String status;
    private String sex;
    private String birthday;

    ListDataSave dataSave;

    private String cookies;
//    private android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // 添加到Activity集合
        TMApplication.instance.addActivity(this);

        dataSave = new ListDataSave(TMApplication.mContext, "list");

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container,new HomeFragment());
        fragmentTransaction.commit();
        iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home_selected));
        tv1.setTextColor(Color.parseColor("#181818"));

//        mainTabBar.onRestoreInstanceState(savedInstanceState);
//
//        mainTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_home, R.mipmap.comui_tab_home_selected, TAG_PAGE_HOME));
////        mainTabBar.addTab(ProductFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_product, R.mipmap.comui_tab_product_selected, TAG_PAGE_PRODUCT));
//        mainTabBar.addTab(ActivitiesFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_garden, R.mipmap.comui_tab_garden_selected, TAG_PAGE_ACTIVITIES));
//        mainTabBar.addTab(ShoppingFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_shopping, R.mipmap.comui_tab_shopping_selected, TAG_PAGE_SHOPPING));
//        mainTabBar.addTab(MyfarmFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_myfarm, R.mipmap.comui_tab_myfarm_selected, TAG_PAGE_MYFARM));
//        mainTabBar.addTab(MyFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_person, R.mipmap.comui_tab_person_selected, TAG_PAGE_PERSON));

        //假设在首页就要拿登录成功的地址
//        Login login = (Login) SharePreferencesUtils.getBean(
//                this, "login");
//
//        if (login != null){
//            List<Login.DataBean.AddressBean> address = login.getData().getAddress();
//            if (!address.isEmpty()){
//                String city = address.get(0).getCity();
//                Log.e(TAG, "onCreate: "+city );
//            }
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserMessage();

//        //从webview中获取cookies
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//                CookieManager cookieManager = CookieManager.getInstance();
//                cookies = cookieManager.getCookie(url);
//                Log.e(TAG, "cookieonPageFinished: " + cookies);
//                if (cookies != "" || cookies != null) {
//                    SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
//                    edit.putString("cookies", cookies);
//                    edit.commit();
//                }
//
//                getUserMessage();
//
//                super.onPageFinished(view, url);
//            }
//        });
//        webView.loadUrl(AllUrl.USERMESSAGE);

    }


    //获取用户信息
    private void getUserMessage() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.USERMESSAGE);
        request(0, request, userMessageListener, true, true);
    }

    private HttpListener<JSONObject> userMessageListener = new HttpListener<JSONObject>() {


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                //获取cookies
                Headers headers = response.getHeaders();
//                String cookies_login = String.valueOf(headers.getCookies());
//                Log.e(TAG, "cookies_loginonSucceed: "+cookies_login );

                CookieManager cookieManager = NoHttp.getCookieManager();
                List<HttpCookie> cookies1 = cookieManager.getCookieStore().getCookies();
                String cookies_login = String.valueOf(cookies1);
                Log.e(TAG, "cookies_loginonSucceed: " + cookies_login);
                String cookies = cookies_login.substring(1, cookies_login.indexOf("]"));
                Log.e(TAG, "cookies_loginon: " + cookies);


                JSONObject js = response.get();
                Log.e(TAG, "usermessage: " + js);

                status = String.valueOf(js.getInt("status"));
                UserMessage userMessage = JsonUtil.parseJsonToBean(js.toString(), UserMessage.class);

                if (status.equals("1")) {

                    if (userMessage != null) {
                        data = userMessage.getData();
                        avatar = data.getAvatar();
                        userName = data.getUserName();
                        nickname = data.getNickname();
                        uid = data.getUid();
                        birthday = data.getBirthday();
                        sex = data.getSex();

                        SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                        edit.putString("cookies", cookies);
                        edit.putString("status", status);
                        edit.putString("userid", uid);
                        edit.putString("avatar", avatar);
                        edit.putString("nickname", nickname);
                        edit.putString("userName", userName);
                        edit.putString("birthday", birthday);
                        edit.putString("sex", sex);
                        edit.commit();

                        //保存请求到的数据
                        SharePreferencesUtils.putBean(getBaseContext(), "userMessage",
                                userMessage);

                    }

                } else {
//                    ToastUtil.showToast(getBaseContext(),"用户未登录");
                    SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                    edit.putString("status", status);
                    edit.commit();
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


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mainTabBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @OnClick({R.id.rl_home, R.id.rl_garden, R.id.rl_shopping, R.id.rl_farm, R.id.rl_my})
    public void onViewClicked(View view) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.rl_home:
                fragmentTransaction.replace(R.id.main_container,new HomeFragment());
                iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home_selected));
                tv1.setTextColor(Color.parseColor("#181818"));
                iv2.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_garden));
                tv2.setTextColor(Color.parseColor("#606060"));
                iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping));
                tv3.setTextColor(Color.parseColor("#606060"));
                iv4.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_myfarm));
                tv4.setTextColor(Color.parseColor("#606060"));
                iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person));
                tv5.setTextColor(Color.parseColor("#606060"));

                break;
            case R.id.rl_garden:
                if (status.equals("1")){
                    fragmentTransaction.replace(R.id.main_container,new ActivitiesFragment());
                    iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home));
                    tv1.setTextColor(Color.parseColor("#606060"));
                    iv2.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_garden_selected));
                    tv2.setTextColor(Color.parseColor("#181818"));
                    iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping));
                    tv3.setTextColor(Color.parseColor("#606060"));
                    iv4.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_myfarm));
                    tv4.setTextColor(Color.parseColor("#606060"));
                    iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person));
                    tv5.setTextColor(Color.parseColor("#606060"));
                }else {
                    startActivity(new Intent(getBaseContext(), StatusActivity.class));
                }

                break;
            case R.id.rl_shopping:
                if (status.equals("1")){
                    fragmentTransaction.replace(R.id.main_container,new ShoppingFragment());
                    iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home));
                    tv1.setTextColor(Color.parseColor("#606060"));
                    iv2.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_garden));
                    tv2.setTextColor(Color.parseColor("#606060"));
                    iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping_selected));
                    tv3.setTextColor(Color.parseColor("#181818"));
                    iv4.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_myfarm));
                    tv4.setTextColor(Color.parseColor("#606060"));
                    iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person));
                    tv5.setTextColor(Color.parseColor("#606060"));
                }else {
                    startActivity(new Intent(getBaseContext(), StatusActivity.class));
                }

                break;
            case R.id.rl_farm:
                fragmentTransaction.replace(R.id.main_container,new MyfarmFragment());
                iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home));
                tv1.setTextColor(Color.parseColor("#606060"));
                iv2.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_garden));
                tv2.setTextColor(Color.parseColor("#606060"));
                iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping));
                tv3.setTextColor(Color.parseColor("#606060"));
                iv4.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_myfarm_selected));
                tv4.setTextColor(Color.parseColor("#181818"));
                iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person));
                tv5.setTextColor(Color.parseColor("#606060"));
                break;
            case R.id.rl_my:
                fragmentTransaction.replace(R.id.main_container,new MyFragment());
                iv1.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_home));
                tv1.setTextColor(Color.parseColor("#606060"));
                iv2.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_garden));
                tv2.setTextColor(Color.parseColor("#606060"));
                iv3.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_shopping));
                tv3.setTextColor(Color.parseColor("#606060"));
                iv4.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_myfarm));
                tv4.setTextColor(Color.parseColor("#606060"));
                iv5.setImageDrawable(getResources().getDrawable(R.mipmap.comui_tab_person_selected));
                tv5.setTextColor(Color.parseColor("#181818"));

                break;
        }
        fragmentTransaction.commit();
    }
}
