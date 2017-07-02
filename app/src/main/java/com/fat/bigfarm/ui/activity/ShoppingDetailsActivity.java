package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.DetailsAdapter;
import com.fat.bigfarm.adapter.ShoppingDetailsAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.bean.ProductInfo;
import com.fat.bigfarm.entry.Addcart;
import com.fat.bigfarm.entry.Details;
import com.fat.bigfarm.entry.ShoppingDetails;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.GridSpacingItemDecoration;
import com.fat.bigfarm.view.banner.NonPageTransformer;
import com.jauker.widget.BadgeView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fat.bigfarm.app.TMApplication.mContext;

//商家详情
public class ShoppingDetailsActivity extends BaseActivity {

    private static final String TAG = "ShoppingDetailsActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
//    @BindView(R.id.bannerTop)
//    FlyBanner bannerTop;
    @BindView(R.id.id_viewpager)
    ViewPager id_viewpager;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.webView)
    WebView webView;

    private String id;
    private String typename;

    private ShoppingDetails.DataBean data;
    private List<String> advertising;

    private List<ShoppingDetails.DataBean.GoodsBean> goods;
    private String des;

    private BadgeView badgeView;

    private ShoppingDetailsAdapter detailsAdapter;
    private String userid;
    private String status;

    private List<ImageView> imageViews ;
    private viewPageAdapter mAdapter;
    private Handler handler;

    //GridLayoutManager列数
    private static final int COLUMN = 2;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingdetails);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e(TAG, "onCreate: "+id );
        typename = intent.getStringExtra("typename");
        tvHeadTitle.setText(typename);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        webView.loadUrl("http://www.9fat.com/H5test/farmapp0608/htmls/index-subpageapp.html?shopid="+id);
        Log.e(TAG, "onCreate: "+ "http://www.9fat.com/H5test/farmapp0608/htmls/index-subpageapp.html?shopid="+id);
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // webView数据缓存分为两种：AppCache和DOM Storage（Web Storage）。
//        settings.setDomStorageEnabled(true);// 开启 DOM storage API 功能
        settings.setAppCacheEnabled(true);// 开启H5(APPCache)缓存功能
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {

            private String weburl;
            private String type;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                scheduleDismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器

                Log.e(TAG, "shouldOverrideUrlLoading123: "+url);
                type = url.substring(0, url.indexOf(":"));
                Log.e(TAG, "shouldOverrideUrlLoadingtype: "+type );
                weburl = url.substring(url.indexOf(":") + 1);

                if (type.equals("pictureurl")){
                    ToastUtil.showToast(getBaseContext(),weburl);
                    return true;
                }

                if (type.equals("goodsid")){

                    Intent intent = new Intent();
                    intent.putExtra("id", weburl);
                    intent.putExtra("typename",typename);
                    intent.setClass(getBaseContext(),DetailsActivity.class);
                    startActivity(intent);

                    return true;
                }

                return false;

            }

        });


//        badgeView = new BadgeView(this);

//        getDetails(id);

    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status = TMApplication.instance.sp.getString("status", "");
        userid = TMApplication.instance.sp.getString("userid", "");
        Log.e(TAG, "onResume: "+userid );

    }

    private void getDetails(String id) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.classify + "&id=" + id);
        request(0, request, detailsListener, true, true);
    }
    private HttpListener<JSONObject> detailsListener = new HttpListener<JSONObject>() {


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "detailsListener: " + js);
                int code = js.getInt("code");
                if (code == 200){
                    ShoppingDetails shoppingDetails = JsonUtil.parseJsonToBean(js.toString(), ShoppingDetails.class);

                    data = shoppingDetails.getData();
                    advertising = data.getAdvertising();
                    //轮播图
                    id_viewpager.setPageMargin(10);//设置页与页之间的间距
                    id_viewpager.setOffscreenPageLimit(3);//表示三个界面之间来回切换都不会重新加载

                    mAdapter = new viewPageAdapter();
                    id_viewpager.setAdapter(mAdapter);
                    id_viewpager.setCurrentItem(1000*advertising.size());
                    id_viewpager.setPageTransformer(true, NonPageTransformer.INSTANCE);

                    handler = new Handler();
                    handler.postDelayed(new TimerRunnable(),5000);

                    des = data.getDes();
                    if (!des.equals("")){
                        tvDes.setText(des);
                    }
                    goods = data.getGoods();

                    GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), COLUMN, GridLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(layoutManager);
                    rv.addItemDecoration(new GridSpacingItemDecoration(COLUMN, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
                    rv.setHasFixedSize(true);


                    detailsAdapter = new ShoppingDetailsAdapter(goods);

                    detailsAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, int i) {
                            ShoppingDetails.DataBean.GoodsBean goodsBean = goods.get(i);
                            String id = goodsBean.getId();
                            String name = goodsBean.getName();
                            Intent intent = new Intent();
                            intent.putExtra("id",id);
                            intent.putExtra("typename",name);
                            intent.setClass(ShoppingDetailsActivity.this, DetailsActivity.class);
                            startActivity(intent);

                        }
                    });

                    detailsAdapter.openLoadAnimation();

                    rv.setAdapter(detailsAdapter);

//                    rv.setHasFixedSize(true);
//                    GridLayoutManager mgr=new GridLayoutManager(getBaseContext(),2){
//                        @Override
//                        public boolean canScrollVertically() {
//                            return false;
//                        }
//                    };
//                    rv.setLayoutManager(mgr);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getApplicationContext(), "请求失败");
        }
    };


    class viewPageAdapter extends PagerAdapter{


            @Override
            public Object instantiateItem(ViewGroup container, final int position)
            {
                ImageView imageView = new ImageView(ShoppingDetailsActivity.this);

                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(mContext).load(advertising.get(position % advertising.size())).into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(),position% advertising.size()+"",Toast.LENGTH_LONG).show();
                    }
                });

                container.addView(imageView);

                return imageView;

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object)
            {
                container.removeView((View) object);
            }

            @Override
            public int getCount()
            {
//                return thumb.size();
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object o)
            {
                return view == o;
            }


    }



    class TimerRunnable implements Runnable{

        @Override
        public void run() {
            int curItem = id_viewpager.getCurrentItem();
            id_viewpager.setCurrentItem(curItem+1);
            if (handler!=null){
                handler.postDelayed(this,10000);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null; //此处在Activity退出时及时 回收
    }

    @OnClick({R.id.bt_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;

        }
    }

}
