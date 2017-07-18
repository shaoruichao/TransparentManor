package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
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
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.DetailsAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.bean.ProductInfo;
import com.fat.bigfarm.entry.Addcart;
import com.fat.bigfarm.entry.Details;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fat.bigfarm.app.TMApplication.mContext;

//物品详情
public class DetailsActivity extends BaseActivity {

    private static final String TAG = "DetailsActivity";

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
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rl_shoppingcat)
    RelativeLayout rlShoppingcat;
    @BindView(R.id.iv_shoppingcat)
    ImageView iv_shoppingcat;
    @BindView(R.id.rl_joinshoppingcat)
    RelativeLayout rlJoinshoppingcat;
    @BindView(R.id.rl_raise)
    RelativeLayout rlRaise;

    @BindView(R.id.tv_price_aid)
    TextView tvPriceAid;
    @BindView(R.id.rl_price)
    RelativeLayout rlPrice;

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.tv_pay)
    TextView tv_pay;
    @BindView(R.id.fl_nomessage)
    FrameLayout fl_nomessage;
    @BindView(R.id.ll_shoppingcat)
    LinearLayout ll_shoppingcat;
    @BindView(R.id.view)
    View view;

    private String id;
    private String typename;

    private List<String> thumb;
    private List<Details.DataBean.GuessBean> guess;
    private Details.DataBean data;

    private String price;
    private String des;
    private String name;

    private BadgeView badgeView;

    private String sid;
    private DetailsAdapter detailsAdapter;
    private String userid;
    private String status;
    private String unit;

    private List<ImageView> imageViews;
    private viewPageAdapter mAdapter;
    private Handler handler;

    //GridLayoutManager列数
    private static final int COLUMN = 2;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        TMApplication.instance.addActivity(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        sid = intent.getStringExtra("sid");
        typename = intent.getStringExtra("typename");
        tvHeadTitle.setText(typename);

        badgeView = new BadgeView(this);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        webView.loadUrl("http://www.9fat.com/H5test/farmapp0608/htmls/shoppingdetailspageapp.html?id="+id);
        Log.e(TAG, "onCreate: "+ "http://www.9fat.com/H5test/farmapp0608/htmls/shoppingdetailspageapp.html?id="+id);
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);// 开启 DOM storage API 功能

//        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
//        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
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

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        getDetails(id);

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
        Log.e(TAG, "onResume: " + userid);

    }

    private void getDetails(String id) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.DETAILS + "&id=" + id);
        request(0, request, detailsListener, true, true);
    }

    private HttpListener<JSONObject> detailsListener = new HttpListener<JSONObject>() {


        private String typeid;
        private String action_price;
        private String aid;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "detailsListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    Details details = JsonUtil.parseJsonToBean(js.toString(), Details.class);

                    data = details.getData();
                    sid = data.getSid();
                    guess = data.getGuess();
                    thumb = data.getThumb();
                    typeid = data.getTypeid();
                    if (typeid.equals("1")){
                        tv_pay.setText("立即代养");
                    }else {
                        tv_pay.setText("立即购买");
                    }
//                    name = data.getShopname();
//                    tvHeadTitle.setText(name);
////                    bannerTop.setImagesUrl(thumb);
//
//                    id_viewpager.setPageMargin(10);//设置页与页之间的间距
//                    id_viewpager.setOffscreenPageLimit(3);//表示三个界面之间来回切换都不会重新加载
//
//                    mAdapter = new viewPageAdapter();
//                    id_viewpager.setAdapter(mAdapter);
//                    id_viewpager.setCurrentItem(1000 * thumb.size());
//                    id_viewpager.setPageTransformer(true, NonPageTransformer.INSTANCE);
//
//                    handler = new Handler();
//                    handler.postDelayed(new TimerRunnable(), 5000);
//
//                    name = data.getName();
//                    tvTitle.setText(name);
//                    des = data.getDes();
//                    tvDes.setText(des);
//                    price = data.getPrice();
//                    action_price = data.getAction_price();
//                    unit = data.getUnit();
//                    //物品详情中活动中aid 不为0  两个价格都显示。 aid为0只显示price
//                    aid = data.getAid();
//                    if (aid.equals("0")) {
//                        tvPrice.setText("¥" + price + "元/" + unit);
//                    }else {
//                        tvPrice.setText("¥" + action_price + "元/" + unit);
//                        tvPriceAid.setText("¥" + price + "元/" + unit);
//                        tvPriceAid.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
//                    }
//
//                    GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), COLUMN, GridLayoutManager.VERTICAL, false);
//                    rv.setLayoutManager(layoutManager);
//                    rv.addItemDecoration(new GridSpacingItemDecoration(COLUMN, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
//                    rv.setHasFixedSize(true);
//
//                    detailsAdapter = new DetailsAdapter(guess);
//
//                    detailsAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int i) {
//                            Details.DataBean.GuessBean guessBean = guess.get(i);
//                            id = guessBean.getId();
//                            String name = guessBean.getName();
////                            Intent intent = new Intent();
////                            intent.putExtra("id",id);
////                            intent.putExtra("typename",name);
////                            intent.setClass(DetailsActivity.this, DetailsActivity.class);
////                            startActivity(intent);
//                            getDetails(id);
//                            tvHeadTitle.setText(name);
//                        }
//                    });
//
//                    detailsAdapter.openLoadAnimation();
//
//                    rv.setAdapter(detailsAdapter);
////
////                    rv.setHasFixedSize(true);
////                    GridLayoutManager mgr = new GridLayoutManager(getBaseContext(), 2) {
////                        @Override
////                        public boolean canScrollVertically() {
////                            return false;
////                        }
////                    };
////                    rv.setLayoutManager(mgr);

                }else {
                    fl_nomessage.setVisibility(View.VISIBLE);
                    webView.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                    rlShoppingcat.setVisibility(View.GONE);
                    ll_shoppingcat.setVisibility(View.GONE);
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


    class viewPageAdapter extends PagerAdapter {


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(DetailsActivity.this);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(thumb.get(position % thumb.size())).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), position % thumb.size() + "", Toast.LENGTH_LONG).show();
                }
            });

            container.addView(imageView);

            return imageView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
//                return thumb.size();
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

    }


    class TimerRunnable implements Runnable {

        @Override
        public void run() {
            int curItem = id_viewpager.getCurrentItem();
            id_viewpager.setCurrentItem(curItem + 1);
            if (handler != null) {
                handler.postDelayed(this, 10000);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null; //此处在Activity退出时及时 回收
    }

    private List<ProductInfo> productLists = new ArrayList<>();
    List<ProductInfo> products = new ArrayList<>();

    @OnClick({R.id.bt_back, R.id.rl_shoppingcat, R.id.rl_joinshoppingcat, R.id.rl_raise})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            //购物车
            case R.id.rl_shoppingcat:

                if (status.equals("1")) {
                    Intent intent = new Intent();
//                intent.putExtra("id",id);
                    intent.setClass(getBaseContext(), ShoppingActivity.class);
                    startActivity(intent);

                } else {
                    ToastUtil.showToast(getBaseContext(), "请先登录");
                }

                break;
            case R.id.rl_joinshoppingcat:
                //加入购物车
                if (status.equals("1")) {
                    PostShoppingCart();

                } else {
                    ToastUtil.showToast(getBaseContext(), "请先登录");
                }

                break;
            case R.id.rl_raise:
                //确认订单

                if (status.equals("1")) {
                    String aid = data.getAid();

                    if (aid.equals("0")){
                        price = data.getPrice();
                    }else {
                        price = data.getAction_price();
                    }
//                    String price = data.getPrice();
                    products.add(new ProductInfo(data.getId(), data.getName(),
                            data.getThumb().get(0), data.getName(), data.getAid(),data.getAction_price(),data.getPrice()
                            , 1, "", data.getSid(),data.getShopname(),data.getUnit(),data.getFreight()));


                    Intent intent1 = new Intent();
                    intent1.putExtra("productLists", (Serializable) products);
                    intent1.putExtra("result", price);
                    intent1.setClass(getBaseContext(), SuerOrderActivity.class);

                    startActivity(intent1);
                    products.clear();

                } else {
                    ToastUtil.showToast(getBaseContext(), "请先登录");
                }


                break;
        }
    }

    //添加购物车
    private void PostShoppingCart() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ADDSHOPPINGCAT, RequestMethod.POST);
        if (request != null) {
            request.add("dosubmit", 1);
            request.add("uid", userid);//用户id
            Log.e(TAG, "PostShoppingCart: "+userid );
            request.add("gid", id);//物品id
            Log.e(TAG, "PostShoppingCartgid: "+id );
            request.add("sid", sid);//商家id
            Log.e(TAG, "PostShoppingCartsid: "+sid );
            request.add("count", 1);//数量

            // 添加到请求队列
            request(0, request, addcartListener, true, true);
        }
    }

    private HttpListener<JSONObject> addcartListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "addcartListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    Addcart addcart = JsonUtil.parseJsonToBean(js.toString(), Addcart.class);
                    if (addcart != null) {
                        Addcart.DataBean data = addcart.getData();
                        int count = data.getCount();

                        badgeView.setTargetView(iv_shoppingcat);
                        badgeView.setBadgeCount(count);
                        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);


                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(), "访问网络失败，请检查您的网络！");

        }
    };
}
