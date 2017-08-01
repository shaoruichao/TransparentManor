package com.fat.bigfarm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//活动详情
public class ActivitiesDetailActivity extends BaseActivity {

    private static final String TAG = "ActivitiesDetailActivit";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    //    @BindView(R.id.iv_head)
//    ImageView ivHead;
//    @BindView(R.id.iv_pic)
//    ImageView ivPic;
//    @BindView(R.id.tv)
//    TextView tv;
//    @BindView(R.id.rv_homemore)
//    RecyclerView rvHomemore;
    @BindView(R.id.webView)
    WebView webView;
//    @BindView(R.id.sw_layout)
//    SwipeRefreshLayout swLayout;
    private String id;

    private String url;
    private String title;

    private KProgressHUD hud;

    private CookieManager cookieManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_detail);
        ButterKnife.bind(this);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        if (!title.equals("")){
            tvTitle.setText(title);
        }

        // 设置setWebChromeClient对象
//        webView.setWebChromeClient(wvcc);

        synCookies(getBaseContext());

        url = "www.9fat.com/H5test/farmapp0608/htmls/activityapp.html?id="+id;
        webView.loadUrl("http://www.9fat.com/H5test/farmapp0608/htmls/activityapp.html?id="+id);
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setTextZoom(100);//字体强制100%
        settings.setJavaScriptEnabled(true);
        //不缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDefaultTextEncodingName("utf-8");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {


            private String goodsid;
            private String substring;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

//                scheduleDismiss();
                hud.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器

                Log.e(TAG, "shouldOverrideUrlLoading: "+url);
                substring = url.substring(0, 7);
                goodsid = url.substring(8);
                if (substring.equals("goodsid")){

                    Intent intent = new Intent();
                    intent.putExtra("id",goodsid);
                    intent.putExtra("typename",title);
                    intent.setClass(ActivitiesDetailActivity.this, DetailsActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;

            }

        });


//        getDetails(id);

    }

    /**
     * 同步cookie
     *
     * @param context
     * @param
     */
    public void synCookies(Context context) {

        String cookies = TMApplication.instance.sp.getString("cookies", "");
        Log.e(TAG, "cookie1111111111: " + cookies);


        CookieSyncManager.createInstance(context);
        cookieManager = CookieManager.getInstance();
        if(Build.VERSION.SDK_INT>=21){
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
//        cookieManager.removeSessionCookie();//移除
//        SystemClock.sleep(500);
        cookieManager.setAcceptCookie(true);

//        String[] arr = new String[] {cookies};
//        List list = Arrays.asList(arr);
//        for (int i = 0 ; i < list.size();i++){
//            cookieManager.setCookie("http://www.kpano.com/", (String) list.get(i));
//
//        }

        String[] arr;
        arr=cookies.split(",");

        for (int i = 0 ; i < arr.length;i++){
            Log.e(TAG, "synCookies: "+arr[i] );//setCookie(String url, String value);url必须是根目录
            cookieManager.setCookie("www.9fat.com", arr[i]+"; expires=Sat, 36000; path=/; domain=www.9fat.com");//cookies是在HttpClient中获得的cookie
        }

        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

//    WebChromeClient wvcc = new WebChromeClient() {
//        @Override
//        public void onReceivedTitle(WebView view, String title) {
//            super.onReceivedTitle(view, title);
//            Log.e(TAG, "onReceivedTitle: "+title );
//            if (!url.equals(title)){
//                tvTitle.setText(title);
//            }
//
//        }
//
//    };


    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }

    //    private void getDetails(String id) {
//        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ACTIVITIESDETAIL + id);
//        request(0, request, detailsListener, true, true);
//    }
//    private HttpListener<JSONObject> detailsListener = new HttpListener<JSONObject>() {
//
//
//        private ActivitiesDetailsAdapter activitiesDetailsAdapter;
//        private List<ActivitiesDetail.DataBean.GoodsBean> goods;
//        private String action_thumb;
//        private String picture;
//        private String action_name;
//        private ActivitiesDetail.DataBean data;
//        private ActivitiesDetail activitiesDetail;
//
//        @RequiresApi(api = Build.VERSION_CODES.M)
//        @Override
//        public void onSucceed(int what, Response<JSONObject> response) {
//
//            try {
//                JSONObject js = response.get();
//                Log.e(TAG, "detailsListener: " + js);
//                int code = js.getInt("code");
//                if (code == 200){
//                    activitiesDetail = JsonUtil.parseJsonToBean(js.toString(), ActivitiesDetail.class);
//
//                    data = activitiesDetail.getData();
//                    action_name = data.getAction_name();
//                    if (!action_name.equals("")){
//                        tvTitle.setText(action_name);
//                    }
//                    picture = data.getPicture();
//                    Glide.with(mContext).load(picture).into(ivHead);
//                    action_thumb = data.getAction_thumb();
//                    Glide.with(mContext).load(action_thumb).into(ivPic);
//
//                    goods = data.getGoods();
//
//                    activitiesDetailsAdapter = new ActivitiesDetailsAdapter(goods);
//
//                    activitiesDetailsAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int i) {
//                            ActivitiesDetail.DataBean.GoodsBean goodsBean = goods.get(i);
//                            String id = goodsBean.getId();
//                            String name = goodsBean.getName();
//                            Intent intent = new Intent();
//                            intent.putExtra("id",id);
//                            intent.putExtra("typename",name);
//                            intent.setClass(ActivitiesDetailActivity.this, DetailsActivity.class);
//                            startActivity(intent);
//
//                        }
//                    });
//
//                    activitiesDetailsAdapter.openLoadAnimation();
//
//                    rvHomemore.setAdapter(activitiesDetailsAdapter);
//
//                    rvHomemore.setHasFixedSize(true);
//                    GridLayoutManager mgr=new GridLayoutManager(getBaseContext(),2){
//                        @Override
//                        public boolean canScrollVertically() {
//                            return false;
//                        }
//                    };
//                    rvHomemore.setLayoutManager(mgr);
//
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void onFailed(int what, Response<JSONObject> response) {
//
//            ToastUtil.showToast(getApplicationContext(), "请求失败");
//        }
//    };


    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }
}
