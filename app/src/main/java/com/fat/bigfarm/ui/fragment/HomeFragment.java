package com.fat.bigfarm.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.ViewPageAdapter;
import com.fat.bigfarm.entry.Classify;
import com.fat.bigfarm.ui.activity.DetailsActivity;
import com.fat.bigfarm.ui.activity.ShoppingDetailsActivity;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.AutoPlayViewPager;
import com.fat.bigfarm.view.FlyBanner;
import com.fat.bigfarm.adapter.RvAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseFragment;
import com.fat.bigfarm.entry.Banner;
import com.fat.bigfarm.entry.HomeList;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.view.banner.NonPageTransformer;
import com.fat.bigfarm.view.indicator.TabPageIndicator;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;
    @BindView(R.id.rl_top_home)
    RelativeLayout rl_top_home;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;
    @BindView(R.id.tv_search)
    EditText tv_search;
    private View view;

    //banner图片集合
    private List<String> bigPics;
    private List<String> bigPicsid;

    private RvAdapter rvAdapter;
    private KProgressHUD hud;

    private ArrayList<HomeList.DataBean> homeListData;
    private List<Classify.DataBean> classifyData;

    private int mDistanceY;
    private ViewPageAdapter mAdapter;

    private  AutoPlayViewPager pager;
    LinearLayout mLayout;

    private ArrayList<View> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        tv_search.setInputType(InputType.TYPE_NULL);

        setinit();

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

//        rl_top_home.getBackground().setAlpha(0);
//        rl_search.getBackground().setAlpha(153);


        getList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //RecyclerView滑动监听
//        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            private int totalDy = 0;
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////                这种方法在插入/删除/移动 Item 的时候，totalDy 就变得不精确了
////                totalDy += dy;
////                Log.e(TAG, "onScrolled: "+totalDy );
////                topview.getBackground().setAlpha(totalDy);
////                if (totalDy >= 255) {
////                    topview.getBackground().setAlpha(255);
////                }
//                getScollYDistance();
//
//            }
//        });

    }


    private int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        int  i = (position) * itemHeight - firstVisiableChildView.getTop();
        Log.e(TAG, "getScollYDistance: "+i );
        rl_top_home.getBackground().setAlpha(i);
        if (i >= 255) {
            rl_top_home.getBackground().setAlpha(255);
        }
        return i;
    }

    /*设置刷新的效果*/

    private void setinit() {


//设置刷新的颜色
        swLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候
                mList.clear();
                pager.stop();

                hud = KProgressHUD.create(getActivity())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

                getList();

                //停止刷新
                swLayout.setRefreshing(false);
            }
        });

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

    private void getList() {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.list);
        request(0, request, listListener, true, true);
    }

    private HttpListener<JSONObject> listListener = new HttpListener<JSONObject>() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            scheduleDismiss();

            try {
                JSONObject js = response.get();
                Log.e(TAG, "list: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    HomeList homeList = JsonUtil.parseJsonToBean(js.toString(), HomeList.class);
                    if (homeList != null) {
                        homeListData = homeList.getData();

                        rvAdapter = new RvAdapter(getActivity(), homeListData);

                        getBanner();

                    }
                }

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + "123");
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            scheduleDismiss();
            ToastUtil.showToast(getActivity(), "请求失败");
        }
    };

    //请求轮播图
    private void getBanner() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.banner);
        request(0, request, bannerListener, true, true);
    }

    private HttpListener<JSONObject> bannerListener = new HttpListener<JSONObject>() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {

                JSONObject js = response.get();
                Log.e(TAG, "bannerListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    Banner banner = JsonUtil.parseJsonToBean(js.toString(), Banner.class);
                    if (banner != null) {
                        final List<Banner.DataBean> data = banner.getData();
                        if (data.size()!=0){
                            bigPics = new ArrayList<>();
                            bigPicsid = new ArrayList<>();
                            for (int i = 0; i < data.size(); i++) {
                                String thumb = data.get(i).getAddress();
                                String id = data.get(i).getId();
                                Log.e(TAG, "onSucceed: " + thumb);

                                bigPics.add(thumb);
                                bigPicsid.add(id);

                            }

                            getClassify();
                        }


                    }
                }

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + "123");
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

        }
    };

    //分类
    private void getClassify(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.classify);
        request(0, request, classifyListener, true, true);
    }
    private HttpListener<JSONObject> classifyListener = new HttpListener<JSONObject>() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {
                JSONObject js = response.get();
                Log.e(TAG, "classifyListener: "+js );
                int code = js.getInt("code");
                if (code == 200){
                    Classify classify = JsonUtil.parseJsonToBean(js.toString(), Classify.class);
                    if (classify!=null){
                        classifyData = classify.getData();

                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

                        initBannerTop(rvAdapter);
                        initGridMenu(rvAdapter);
                        rv.setAdapter(rvAdapter);

                        rvAdapter.setmOnItemClickLitener(new RvAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v, int postion) {
//                                ToastUtil.showToast(getActivity(),"item click postion "+postion);
                                HomeList.DataBean dataBean = homeListData.get(postion-2);
                                String typename = dataBean.getTypename();
                                Log.e(TAG, "onItemClick: "+typename );

                            }

                            @Override
                            public void onItemLongClick(View v, int postion) {

                            }
                        });

                    }
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception: "+"123");
                e.printStackTrace();
            }

        }
        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getActivity(),"请求失败");
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initBannerTop(RvAdapter rvAdapter) {
//        View bannerBigView = LayoutInflater.from(getActivity()).inflate(R.layout.banner_top, rv, false);
//        FlyBanner bannerTop = (FlyBanner) bannerBigView.findViewById(R.id.bannerTop);
//        rvAdapter.addHeadView0(bannerBigView);
//        bannerTop.setImagesUrl(bigPics);
//        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                ToastUtil.showToast(getActivity(),"position--->"+bigPics.get(position).toString());
//            }
//        });
        View bannerBigView = LayoutInflater.from(getActivity()).inflate(R.layout.banner, rv, false);
        pager = (AutoPlayViewPager) bannerBigView.findViewById(R.id.pager);
        mLayout= (LinearLayout) bannerBigView.findViewById(R.id.ll_hottest_indicator);
        rvAdapter.addHeadView0(bannerBigView);
        //设置页与页之间的间距
//        pager.setPageMargin(40);
        //设置item的缓存数目
//        pager.setOffscreenPageLimit(4);

        mAdapter = new ViewPageAdapter(getActivity(),bigPics,bigPicsid);
        pager.setAdapter(mAdapter);
        pager.setCurrentItem(1000*bigPics.size());
        pager.setPageTransformer(true, NonPageTransformer.INSTANCE);

        mList.clear();
        for (int i = 0; i < bigPics.size(); i++) {
            View view=new View(getActivity());
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
            view.setLayoutParams(layoutParams);
            if(i==0){
                view.setBackgroundColor(Color.parseColor("#F3D05A"));
            }

            mList.add(view);
            mLayout.addView(view);
        }

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mList.size(); i++) {
                    if (i == position% mList.size()) {
                        mList.get(i).setBackgroundColor(Color.parseColor("#F3D05A"));
                    } else {
                        mList.get(i).setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        //自动轮播
        pager.start();

    }


    /**
     * 初始化4个子菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initGridMenu(RvAdapter rvAdapter) {
        View gridMenu = LayoutInflater.from(getActivity()).inflate(R.layout.grid_menu_new4, rv, false);
        //云农场
        TextView tv_Cloudfarm_title = (TextView) gridMenu.findViewById(R.id.tv_Cloudfarm_title);
        tv_Cloudfarm_title.setText(classifyData.get(0).getName());
        String logo = classifyData.get(0).getLogo();
        ImageView iv1 = (ImageView) gridMenu.findViewById(R.id.iv1);
        Glide.with(getActivity())
                .load(logo)
                .into(iv1);
        RelativeLayout rl_Cloudfarm = (RelativeLayout) gridMenu.findViewById(R.id.rl_Cloudfarm);
        rl_Cloudfarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showToast(getActivity(),"云农场");

                Intent intent = new Intent();
                intent.putExtra("id",classifyData.get(0).getId());
                intent.putExtra("typename",classifyData.get(0).getName());
                intent.setClass(getActivity(), ShoppingDetailsActivity.class);
                startActivity(intent);

            }
        });
        //纯草农庄
        TextView tv_grassfarm_title = (TextView) gridMenu.findViewById(R.id.tv_grassfarm_title);
        tv_grassfarm_title.setText(classifyData.get(1).getName());
        ImageView iv2 = (ImageView) gridMenu.findViewById(R.id.iv2);
        Glide.with(getActivity())
                .load(classifyData.get(1).getLogo())
                .into(iv2);
        RelativeLayout rl_grassfarm = (RelativeLayout) gridMenu.findViewById(R.id.rl_grassfarm);
        rl_grassfarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showToast(getActivity(),"纯草农庄");

                Intent intent = new Intent();
                intent.putExtra("id",classifyData.get(1).getId());
                intent.putExtra("typename",classifyData.get(1).getName());
                intent.setClass(getActivity(), ShoppingDetailsActivity.class);
                startActivity(intent);

            }
        });
        //农机农场
        TextView tv_machineryfarm_title = (TextView) gridMenu.findViewById(R.id.tv_machineryfarm_title);
        tv_machineryfarm_title.setText(classifyData.get(2).getName());
        ImageView iv3 = (ImageView) gridMenu.findViewById(R.id.iv3);
        Glide.with(getActivity())
                .load(classifyData.get(2).getLogo())
                .into(iv3);
        RelativeLayout rl_machineryfarm = (RelativeLayout) gridMenu.findViewById(R.id.rl_machineryfarm);
        rl_machineryfarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showToast(getActivity(),"农机农场");

                Intent intent = new Intent();
                intent.putExtra("id",classifyData.get(2).getId());
                intent.putExtra("typename",classifyData.get(2).getName());
                intent.setClass(getActivity(), ShoppingDetailsActivity.class);
                startActivity(intent);

            }
        });
        //农家乐
//        TextView tv_Generated_title = (TextView) gridMenu.findViewById(R.id.tv_Generated_title);
//        tv_Generated_title.setText(classifyData.get(3).getName());
//        ImageView iv4 = (ImageView) gridMenu.findViewById(R.id.iv4);
//        Glide.with(getActivity())
//                .load(classifyData.get(3).getLogo())
//                .into(iv4);
//        RelativeLayout rl_Generated = (RelativeLayout) gridMenu.findViewById(R.id.rl_Generated);
//        rl_Generated.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ToastUtil.showToast(getActivity(),"农家乐");
//
//                Intent intent = new Intent();
//                intent.putExtra("id",classifyData.get(3).getId());
//                intent.putExtra("typename",classifyData.get(3).getName());
//                intent.setClass(getActivity(), ShoppingDetailsActivity.class);
//                startActivity(intent);
//
//            }
//        });

        rvAdapter.addHeaderView1(gridMenu);

    }

}
