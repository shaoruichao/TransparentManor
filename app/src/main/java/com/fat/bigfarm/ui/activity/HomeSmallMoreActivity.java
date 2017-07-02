package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.HomeSmallMoreAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.HomeMore;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.GridSpacingItemDecoration;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//首页更多(物品分类) 详情   小图显示
public class HomeSmallMoreActivity extends BaseActivity {

    private static final String TAG = "HomeMoreActivity";

    @BindView(R.id.rv_homemore)
    RecyclerView rvHomemore;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;
    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv)
    TextView tv;
    private String id;
    private KProgressHUD hud;

    private HomeSmallMoreAdapter homeMoreAdapter;
    private List<HomeMore.ListBean> list;

    private String typethumb;
    private String typename;

    //GridLayoutManager列数
    private static final int COLUMN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_more);
        ButterKnife.bind(this);

        setinit();

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        getHomeMore(id);

    }

    //物品分类
    private void getHomeMore(String id) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.NEWLIST_classify + "&id=" + id);
        request(0, request, HomeMoreListener, true, true);
    }

    private HttpListener<JSONObject> HomeMoreListener = new HttpListener<JSONObject>() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            scheduleDismiss();

            try {
                JSONObject js = response.get();
                Log.e(TAG, "classifyListener: " + js);
//                int code = js.getInt("code");
//                if (code == 200){
                HomeMore homeMore = JsonUtil.parseJsonToBean(js.toString(), HomeMore.class);
                if (homeMore != null) {
                    list = homeMore.getList();
                    typename = homeMore.getTypename();
                    typethumb = homeMore.getTypethumb();
                    Glide.with(HomeSmallMoreActivity.this)
                            .load(typethumb)
                            .into(ivHead);

                    tvTitle.setText(typename);
                    tv.setText(typename);

//                    GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), COLUMN, GridLayoutManager.VERTICAL, false);
//                    rvHomemore.setLayoutManager(layoutManager);
//                    rvHomemore.addItemDecoration(new GridSpacingItemDecoration(COLUMN, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
//                    rvHomemore.setHasFixedSize(true);

                    homeMoreAdapter = new HomeSmallMoreAdapter(list);

                    homeMoreAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, int i) {
                            HomeMore.ListBean listBean = list.get(i);
                            String id = listBean.getId();//物品id
                            String sid = listBean.getSid();//商家id
                            String name = listBean.getName();


                            Intent intent = new Intent();
                            intent.putExtra("id", id);
                            intent.putExtra("sid", sid);
                            intent.putExtra("typename", typename);
                            intent.setClass(getBaseContext(), DetailsActivity.class);
                            startActivity(intent);
                        }
                    });

                    homeMoreAdapter.openLoadAnimation();

                    rvHomemore.setAdapter(homeMoreAdapter);

                    rvHomemore.setHasFixedSize(true);
                    GridLayoutManager mgr = new GridLayoutManager(getBaseContext(), 2) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    rvHomemore.setLayoutManager(mgr);

//                    rvHomemore.setHasFixedSize(true);
//                    rvHomemore.setLayoutManager(new LinearLayoutManager(HomeSmallMoreActivity.this));
//
//                    rvHomemore.setAdapter(homeMoreAdapter);

//                    homeMoreAdapter.setmOnItemClickLitener(new HomeMoreAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(View v, int postion) {
//                            HomeMore.ListBean listBean = list.get(postion - 1);
//                            String id = listBean.getId();//物品id
//                            String name = listBean.getName();
//
//                            Intent intent = new Intent();
//                            intent.putExtra("id", id);
//                            intent.putExtra("typename", name);
//                            intent.setClass(getBaseContext(), DetailsActivity.class);
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void onItemLongClick(View v, int postion) {
//
//                        }
//                    });
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + "123");
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            scheduleDismiss();
            ToastUtil.showToast(getApplicationContext(), "请求失败");
        }
    };

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }


    private void setinit() {

        //设置刷新的颜色
        swLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

                hud = KProgressHUD.create(HomeSmallMoreActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

                getHomeMore(id);

                //停止刷新
                swLayout.setRefreshing(false);
            }
        });

    }

    @OnClick(R.id.bt_back)
    public void onClick() {
        finish();
    }
}
