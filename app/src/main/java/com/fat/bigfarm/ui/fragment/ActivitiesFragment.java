package com.fat.bigfarm.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseFragment;
import com.fat.bigfarm.ui.activity.ActivitiesDetailActivity;
import com.fat.bigfarm.ui.activity.DetailsActivity;
import com.fat.bigfarm.ui.activity.MyOrderActivity;
import com.fat.bigfarm.ui.activity.StatusActivity;
import com.fat.bigfarm.utils.DensityUtils;
import com.fat.bigfarm.view.ObservableWebView;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 活动
 */
public class ActivitiesFragment extends BaseFragment {


    private static final String TAG = "ActivitiesFragment";

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.rl_map)
    RelativeLayout rlMap;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.rl_scan)
    RelativeLayout rlScan;
    @BindView(R.id.rl_top_activities)
    RelativeLayout rl_top_activities;
    @BindView(R.id.webView)
    ObservableWebView webView;

//    @BindView(R.id.rl)
//    RelativeLayout rl;
//    @BindView(R.id.tv_preferential)
//    TextView tvPreferential;
//    @BindView(R.id.tv_halfprice_name)
//    TextView tvHalfpriceName;
//    @BindView(R.id.tv_halfprice_des)
//    TextView tvHalfpriceDes;
//    @BindView(R.id.iv_halfprice_thumb)
//    ImageView ivHalfpriceThumb;
//    @BindView(R.id.rl_halfprice)
//    RelativeLayout rlHalfprice;
//    @BindView(R.id.tv_selective_name)
//    TextView tvSelectiveName;
//    @BindView(R.id.tv_selective_des)
//    TextView tvSelectiveDes;
//    @BindView(R.id.iv_selective_thumb)
//    ImageView ivSelectiveThumb;
//    @BindView(R.id.rl_selective)
//    RelativeLayout rlSelective;
//    @BindView(R.id.tv_discount_name)
//    TextView tvDiscountName;
//    @BindView(R.id.tv_discount_des)
//    TextView tvDiscountDes;
//    @BindView(R.id.iv_discount_thumb)
//    ImageView ivDiscountThumb;
//    @BindView(R.id.rl_discount)
//    RelativeLayout rlDiscount;
//    @BindView(R.id.tv_ranking_name)
//    TextView tvRankingName;
//    @BindView(R.id.tv_ranking_des)
//    TextView tvRankingDes;
//    @BindView(R.id.iv_ranking_thumb)
//    ImageView ivRankingThumb;
//    @BindView(R.id.rl_ranking)
//    RelativeLayout rlRanking;
//    @BindView(R.id.rl_preferential)
//    RelativeLayout rlPreferential;
//    @BindView(R.id.tv_integral)
//    TextView tvIntegral;
//    @BindView(R.id.ll)
//    LinearLayout ll;
//    @BindView(R.id.iv_head)
//    ImageView ivHead;
    private View view;
    Unbinder unbinder;

//    private String rankingthumb;
//    private String rankingname;
//    private String rankingid;
//    private String discountthumb;
//    private String discountname;
//    private String discountid;
//    private String selectivethumb;
//    private String selectivename;
//    private String selectiveid;
//    private String halfpricethumb;
//    private String halfpricename;
//    private String halfpriceid;
//    private String head_pic;
//    private Activities.DataBean data;

    private KProgressHUD hud;

    private CookieManager cookieManager;
    private String status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activities, container, false);
        unbinder = ButterKnife.bind(this, view);

//        rl_top_activities.getBackground().setAlpha(0);
//        rlSearch.getBackground().setAlpha(153);
        tvSearch.setInputType(InputType.TYPE_NULL);

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

//        status = TMApplication.instance.sp.getString("status", "");
//        if (!status.equals("1")){
//            startActivity(new Intent(getActivity(), StatusActivity.class));
//        }


//        getActivities();
        return view;
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
            Log.e(TAG, "synCookies: "+arr[i] );
            cookieManager.setCookie("www.9fat.com", arr[i]+"; expires=Sat, 36000; path=/; domain=www.9fat.com");//cookies是在HttpClient中获得的cookie
        }

        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();


        synCookies(getActivity());
        webView.loadUrl("http://www.9fat.com/H5test/farmapp0608/htmls/promotionpageapp.html");
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setTextZoom(100);//字体强制100%
        settings.setJavaScriptEnabled(true);
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {

            private String title;
            private String webTitle;
            private String webid;
            private String substring1;
            private String substring;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

//                CookieManager cookieManager = CookieManager.getInstance();
//                String cookies = cookieManager.getCookie(url);
//                Log.e(TAG, "cookie: " + cookies);
//                if (cookies != "" || cookies != null) {
//                    SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
//                    edit.putString("cookies", cookies);
//                    edit.commit();
//                }
                hud.dismiss();
//                scheduleDismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器

                Log.e(TAG, "shouldOverrideUrlLoading123: "+url);
                if (!url.equals("http://www.9fat.com/H5test/farmapp0608/htmls/promotionpageapp.html")){
//                    substring = url.substring(0,8);
//                    substring1 = url.substring(9);
                    substring = url.substring(0, url.indexOf(":"));
                    Log.e(TAG, "shouldOverrideUrlLoading1: "+substring );
                    substring1 = url.substring(url.indexOf(":") + 1);
                    Log.e(TAG, "shouldOverrideUrlLoading: "+substring1 );
                    if (substring.equals("goodsid")){

                        Intent intent = new Intent();
                        intent.putExtra("id",substring1);
//                        intent.putExtra("typename",title);
                        intent.setClass(getActivity(), DetailsActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    if (substring.equals("activity")){

                        webid = substring1.substring(0, substring1.indexOf(":"));

                        webTitle = substring1.substring(substring1.indexOf(":") + 1);
                        Log.e(TAG, "shouldOverrideUrlLoading: "+webTitle );
                        try {
                            title = URLDecoder.decode(webTitle, "UTF-8");
                            Log.e(TAG, "shouldOverrideUrlLoading: "+title );
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent();
                        intent.putExtra("id", webid);
                        intent.putExtra("title",title);
                        intent.setClass(getActivity(),ActivitiesDetailActivity.class);
                        startActivity(intent);

                        return true;
                    }



                }

                return false;

            }

        });


        webView.setOnScrollChangeListener(new ObservableWebView.OnScrollChangeListener() {

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                //滑动中

                Log.e(TAG, "onScrollChanged: "+oldt );
//                rl_top_activities.getBackground().setAlpha(oldt);
//                if ( oldt >= 255){
//                    rl_top_activities.getBackground().setAlpha(255);
//                }

            }

            @Override
            public void onPageTop(int l, int t, int oldl, int oldt) {
                //滑动到顶部
//                rl_top_activities.getBackground().setAlpha(0);
            }

            @Override
            public void onPageEnd(int l, int t, int oldl, int oldt) {
                //滑动到底部
            }
        });
    }

    //    private void getActivities() {
//        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.ACTIVITIES);
//        request(0, request, activitiesListener, true, true);
//    }
//
//    private HttpListener<JSONObject> activitiesListener = new HttpListener<JSONObject>() {
//
//
//        @RequiresApi(api = Build.VERSION_CODES.M)
//        @Override
//        public void onSucceed(int what, Response<JSONObject> response) {
//
////            scheduleDismiss();
//
//            try {
//                JSONObject js = response.get();
//                Log.e(TAG, "activitiesListener: " + js);
//                int code = js.getInt("code");
//                if (code == 200) {
//                    Activities activities = JsonUtil.parseJsonToBean(js.toString(), Activities.class);
//                    head_pic = activities.getHead_pic();
//                    Glide.with(mContext).load(head_pic).into(ivHead);
//                    data = activities.getData();
//
//                    halfpriceid = data.getAction1().getId();
//                    halfpricename = data.getAction1().getName();
//                    tvHalfpriceName.setText(halfpricename);
//                    halfpricethumb = data.getAction1().getThumb();
//                    Glide.with(mContext).load(halfpricethumb).into(ivHalfpriceThumb);
//
//                    selectiveid = data.getAction2().getId();
//                    selectivename = data.getAction2().getName();
//                    tvSelectiveName.setText(selectivename);
//                    selectivethumb = data.getAction2().getThumb();
//                    Glide.with(mContext).load(selectivethumb).into(ivSelectiveThumb);
//
//                    discountid = data.getAction3().getId();
//                    discountname = data.getAction3().getName();
//                    tvDiscountName.setText(discountname);
//                    discountthumb = data.getAction3().getThumb();
//                    Glide.with(mContext).load(discountthumb).into(ivDiscountThumb);
//
//                    rankingid = data.getAction4().getId();
//                    rankingname = data.getAction4().getName();
//                    tvRankingName.setText(rankingname);
//                    rankingthumb = data.getAction4().getThumb();
//                    Glide.with(mContext).load(rankingthumb).into(ivRankingThumb);
//
//
//                }
//
//            } catch (Exception e) {
//                Log.e(TAG, "Exception: " + "123");
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void onFailed(int what, Response<JSONObject> response) {
//
////            scheduleDismiss();
//            ToastUtil.showToast(getActivity(), "请求失败");
//        }
//    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

//    @OnClick({R.id.rl_halfprice, R.id.rl_selective, R.id.rl_discount, R.id.rl_ranking})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.rl_halfprice:
//                Intent intent = new Intent();
//                intent.putExtra("id", halfpriceid);
//                intent.setClass(getActivity(), ActivitiesDetailActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.rl_selective:
//                Intent intent1 = new Intent();
//                intent1.putExtra("id", selectiveid);
//                intent1.setClass(getActivity(), ActivitiesDetailActivity.class);
//                startActivity(intent1);
//                break;
//            case R.id.rl_discount:
//                Intent intent2 = new Intent();
//                intent2.putExtra("id", discountid);
//                intent2.setClass(getActivity(), ActivitiesDetailActivity.class);
//                startActivity(intent2);
//                break;
//            case R.id.rl_ranking:
//                Intent intent3 = new Intent();
//                intent3.putExtra("id", rankingid);
//                intent3.setClass(getActivity(), ActivitiesDetailActivity.class);
//                startActivity(intent3);
//                break;
//        }
//    }
}
