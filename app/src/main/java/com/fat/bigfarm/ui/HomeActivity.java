package com.fat.bigfarm.ui;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.fragment.ActivitiesFragment;
import com.fat.bigfarm.ui.fragment.HomeFragment;
import com.fat.bigfarm.ui.fragment.MyfarmFragment;
import com.fat.bigfarm.ui.fragment.ShoppingFragment;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ListDataSave;
import com.fat.bigfarm.utils.SharePreferencesUtils;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.MainNavigateTabBar;
import com.fat.bigfarm.base.BaseFragmentActivity;
import com.fat.bigfarm.entry.UserMessage;
import com.fat.bigfarm.ui.fragment.MyFragment;
import com.fat.bigfarm.ui.fragment.ProductFragment;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.net.HttpCookie;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yolanda.nohttp.NoHttp.getCookieManager;

public class HomeActivity extends BaseFragmentActivity {

    private static final String TAG = "HomeActivity";

    private static final String TAG_PAGE_HOME = "首页";
//    private static final String TAG_PAGE_PRODUCT = "新品";
    private static final String TAG_PAGE_ACTIVITIES = "活动";
    private static final String TAG_PAGE_SHOPPING = " 购物车";
    private static final String TAG_PAGE_MYFARM = "我的农庄";
    private static final String TAG_PAGE_PERSON = "我的";

    private long mExitTime;

    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.mainTabBar)
    MainNavigateTabBar mainTabBar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // 添加到Activity集合
        TMApplication.instance.addActivity(this);

        dataSave = new ListDataSave(TMApplication.mContext, "list");

        mainTabBar.onRestoreInstanceState(savedInstanceState);

        mainTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_home, R.mipmap.comui_tab_home_selected, TAG_PAGE_HOME));
//        mainTabBar.addTab(ProductFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_product, R.mipmap.comui_tab_product_selected, TAG_PAGE_PRODUCT));
        mainTabBar.addTab(ActivitiesFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_activities, R.mipmap.comui_tab_activities_selected, TAG_PAGE_ACTIVITIES));
        mainTabBar.addTab(ShoppingFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_shopping, R.mipmap.comui_tab_shopping_selected, TAG_PAGE_SHOPPING));
        mainTabBar.addTab(MyfarmFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_myfarm, R.mipmap.comui_tab_myfarm_selected, TAG_PAGE_MYFARM));
        mainTabBar.addTab(MyFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_person, R.mipmap.comui_tab_person_selected, TAG_PAGE_PERSON));

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
    private void getUserMessage(){
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

                java.net.CookieManager cookieManager = NoHttp.getCookieManager();
                List<HttpCookie> cookies1 = cookieManager.getCookieStore().getCookies();
                String cookies_login = String.valueOf(cookies1);
                Log.e(TAG, "cookies_loginonSucceed: "+cookies_login );
                String cookies = cookies_login.substring(1,cookies_login.indexOf("]"));
                Log.e(TAG, "cookies_loginon: "+cookies );


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

                }else {
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
            ToastUtil.showToast(getBaseContext(),"请求网络失败，请稍后重试");
        }
    };


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mainTabBar.onSaveInstanceState(outState);
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

}
