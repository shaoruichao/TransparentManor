package com.fat.bigfarm.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
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
import com.fat.bigfarm.adapter.RvProductAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseFragment;
import com.fat.bigfarm.entry.NewListClassify;
import com.fat.bigfarm.entry.ProductList;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.activity.DetailsActivity;
import com.fat.bigfarm.ui.activity.HomeMoreActivity;
import com.fat.bigfarm.ui.activity.HomeSmallMoreActivity;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新品
 */
public class ProductFragment extends BaseFragment {

    private static final String TAG = "ProductFragment";

    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;
    @BindView(R.id.rl_top_product)
    RelativeLayout rl_top_product;
    @BindView(R.id.tv_search)
    EditText tv_search;
    private View view;

    private KProgressHUD hud;

    private RvProductAdapter rvProductAdapter;
    private List<ProductList.DataBean> productListData;
    private List<NewListClassify.DataBean> classifyData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_product, container, false);

        ButterKnife.bind(this, view);

        tv_search.setInputType(InputType.TYPE_NULL);

        setinit();

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();


//        rl_top_product.getBackground().setAlpha(0);

        getList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


//        //RecyclerView滑动监听
//        rvProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            private int totalDy = 0;
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                getScollYDistance();
//
//            }
//        });

    }

    private int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rvProduct.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        Log.e(TAG, "getScollYDistance: "+position );
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        int  i = (position) * itemHeight - firstVisiableChildView.getTop();
        Log.e(TAG, "getScollYDistance: "+i );
        rl_top_product.getBackground().setAlpha(i);
        if (i >= 255) {
            rl_top_product.getBackground().setAlpha(255);
        }
        return i;
    }

    private void getList() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.NEWLIST);
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
                    ProductList productList = JsonUtil.parseJsonToBean(js.toString(), ProductList.class);
                    if (productList != null) {
                        productListData = productList.getData();

                        rvProductAdapter = new RvProductAdapter(getActivity(), productListData);

//                        getClassify();
                        rvProduct.setHasFixedSize(true);
                        rvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));

                        rvProduct.setAdapter(rvProductAdapter);
                        initGridMenu(rvProductAdapter);

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

    //分类
    private void getClassify(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.NEWLIST_classify);
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
                    NewListClassify classify = JsonUtil.parseJsonToBean(js.toString(), NewListClassify.class);
                    if (classify!=null){
                        classifyData = classify.getData();

                        rvProduct.setHasFixedSize(true);
                        rvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));

                        rvProduct.setAdapter(rvProductAdapter);
                        initGridMenu(rvProductAdapter);
                        rvProductAdapter.setmOnItemClickLitener(new RvProductAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v, int postion) {
//                                ToastUtil.showToast(getActivity(),"item click postion "+postion);

//                                HomeList.DataBean dataBean = homeListData.get(postion-2);
//                                String id = dataBean.getId();
//                                Log.e(TAG, "onItemClick: "+id );
//
//                                Intent intent = new Intent();
//                                intent.putExtra("id",id);
//                                intent.setClass(getActivity(), DetailsActivity.class);
//                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View v, int postion) {

                            }
                        });

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getActivity(),"请求失败");
        }
    };

    /**
     * 初始化4个子菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initGridMenu(RvProductAdapter rvAdapter) {
        View gridMenu = LayoutInflater.from(getActivity()).inflate(R.layout.grid_menu_product4, rvProduct, false);
        //代养
//        TextView tv_raise_title = (TextView) gridMenu.findViewById(R.id.tv_raise_title);
//        tv_raise_title.setText(classifyData.get(0).getName());
//        ImageView iv1 = (ImageView) gridMenu.findViewById(R.id.iv1);
//        Glide.with(getActivity())
//                .load(classifyData.get(0).getIcon())
//                .into(iv1);
        RelativeLayout rl_raise = (RelativeLayout) gridMenu.findViewById(R.id.rl_raise);
        rl_raise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id","1");
//                intent.putExtra("id",classifyData.get(0).getId());
//                intent.putExtra("typename",classifyData.get(0).getName());
                intent.setClass(getActivity(), HomeMoreActivity.class);
                startActivity(intent);
            }
        });
        //禽蛋
//        TextView tv_eggs_title = (TextView) gridMenu.findViewById(R.id.tv_eggs_title);
//        tv_eggs_title.setText(classifyData.get(1).getName());
//        ImageView iv2 = (ImageView) gridMenu.findViewById(R.id.iv2);
//        Glide.with(getActivity())
//                .load(classifyData.get(1).getIcon())
//                .into(iv2);
        RelativeLayout rl_eggs = (RelativeLayout) gridMenu.findViewById(R.id.rl_eggs);
        rl_eggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id","3");
//                intent.putExtra("id",classifyData.get(1).getId());
//                intent.putExtra("typename",classifyData.get(1).getName());
                intent.setClass(getActivity(), HomeMoreActivity.class);
                startActivity(intent);
            }
        });
        //果蔬
//        TextView tv_fruit_title = (TextView) gridMenu.findViewById(R.id.tv_fruit_title);
//        tv_fruit_title.setText(classifyData.get(2).getName());
//        ImageView iv3 = (ImageView) gridMenu.findViewById(R.id.iv3);
//        Glide.with(getActivity())
//                .load(classifyData.get(2).getIcon())
//                .into(iv3);
        RelativeLayout rl_fruit = (RelativeLayout) gridMenu.findViewById(R.id.rl_fruit);
        rl_fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id","4");
//                intent.putExtra("id",classifyData.get(2).getId());
//                intent.putExtra("typename",classifyData.get(2).getName());
                intent.setClass(getActivity(), HomeSmallMoreActivity.class);
                startActivity(intent);
            }
        });
        //高定
//        TextView tv_highset_title = (TextView) gridMenu.findViewById(R.id.tv_highset_title);
//        tv_highset_title.setText(classifyData.get(3).getName());
//        ImageView iv4 = (ImageView) gridMenu.findViewById(R.id.iv4);
//        Glide.with(getActivity())
//                .load(classifyData.get(3).getIcon())
//                .into(iv4);
        RelativeLayout rl_highset = (RelativeLayout) gridMenu.findViewById(R.id.rl_highset);
        rl_highset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id","2");
//                intent.putExtra("id",classifyData.get(3).getId());
//                intent.putExtra("typename",classifyData.get(3).getName());
                intent.setClass(getActivity(), HomeSmallMoreActivity.class);
                startActivity(intent);
            }
        });

        rvAdapter.addHeaderView1(gridMenu);

    }

    private void setinit() {

        //设置刷新的颜色
        swLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

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

}
