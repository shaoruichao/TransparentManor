package com.fat.bigfarm.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.DaizhongAdapter;
import com.fat.bigfarm.adapter.WarehouseRaiseAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseFragment;
import com.fat.bigfarm.entry.Daizhong;
import com.fat.bigfarm.entry.Raise;
import com.fat.bigfarm.nohttp.HttpListener;
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
import butterknife.Unbinder;

/**
 * 我的农庄 - 代种
 */
public class MyDaizhongFragment extends BaseFragment {

    private static final String TAG = "MyDaizhongFragment";
    @BindView(R.id.rv_mydaizhong)
    RecyclerView rvMydaizhong;
    @BindView(R.id.sw)
    SwipeRefreshLayout sw;
    private View view;

    @BindView(R.id.im_nomessgae)
    ImageView imNomessgae;
    @BindView(R.id.fl_nomessage)
    FrameLayout fl_nomessage;

    private KProgressHUD hud;
    private String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_daizhong, container, false);
        ButterKnife.bind(this, view);

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();
        userid = TMApplication.instance.sp.getString("userid", "");
        GetData();
        initView();

        return view;
    }

    private void initView(){
        //设置刷新的颜色
        sw.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候
                hud = KProgressHUD.create(getActivity())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();

                GetData();
                //停止刷新
                sw.setRefreshing(false);
            }
        });
    }


    private void GetData(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.FOSTER+userid+"&type="+1);
        request(0, request, fosterListener, true, true);
    }

    private HttpListener<JSONObject> fosterListener = new HttpListener<JSONObject>() {

        private DaizhongAdapter daizhongAdapter;
        private List<Daizhong.DataBean> data;
        private Daizhong daizhong;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "fosterListener: "+js );
                int code = js.getInt("code");
                if (code == 200){
                    daizhong = JsonUtil.parseJsonToBean(js.toString(), Daizhong.class);
                    if (daizhong != null){

                        data = daizhong.getData();

                        if (data.size()==0){
                            fl_nomessage.setVisibility(View.VISIBLE);
                        }else {
                            fl_nomessage.setVisibility(View.GONE);
                        }

                        rvMydaizhong.setHasFixedSize(true);
                        rvMydaizhong.setLayoutManager(new LinearLayoutManager(getActivity()));

                        daizhongAdapter = new DaizhongAdapter(data);

                        daizhongAdapter.openLoadAnimation();
                        rvMydaizhong.setAdapter(daizhongAdapter);


                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(getActivity(),"请求网络失败，请稍后重试");
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



}
