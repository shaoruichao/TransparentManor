package com.fat.bigfarm.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.OrderObligationAdapter;
import com.fat.bigfarm.adapter.OrderObligationItemAdapter;
import com.fat.bigfarm.adapter.WarehouseRaiseAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseOrderFragment;
import com.fat.bigfarm.entry.MyOrderAll;
import com.fat.bigfarm.entry.Raise;
import com.fat.bigfarm.entry.UserMessage;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.activity.OrderDetailActivity;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.SharePreferencesUtils;
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
 * 我的仓库-代养
 */
public class MyRaiseFragment extends BaseOrderFragment {

    private static final String TAG = "MyRaiseFragment";

    @BindView(R.id.rv_myraise)
    RecyclerView rvMyraise;
    @BindView(R.id.sw)
    SwipeRefreshLayout sw;
    //    Unbinder unbinder;
    @BindView(R.id.im_nomessgae)
    ImageView imNomessgae;
    @BindView(R.id.fl_nomessage)
    FrameLayout fl_nomessage;
    private View view;


    private String userid;

    private WarehouseRaiseAdapter warehouseRaiseAdapter;
    private List<Raise.DataBean> data;
    private Raise raise;
    private KProgressHUD hud;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_raise, container, false);
        ButterKnife.bind(this, view);

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        userid = TMApplication.instance.sp.getString("userid", "");
        GetData();

        initView();
        return view;
    }

    private void initView() {
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


    private void GetData() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.FOSTER + userid + "&type=" + 1);
        Log.e(TAG, "GetData: " + AllUrl.FOSTER + userid + "&type=" + 1);
        request(0, request, fosterListener, true, true);
    }

    private HttpListener<JSONObject> fosterListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "usermessage: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    raise = JsonUtil.parseJsonToBean(js.toString(), Raise.class);
                    if (raise != null) {

                        data = raise.getData();

                        if (data.size()==0){
                            fl_nomessage.setVisibility(View.VISIBLE);
                        }else {
                            fl_nomessage.setVisibility(View.GONE);
                        }

                        rvMyraise.setHasFixedSize(true);
                        rvMyraise.setLayoutManager(new LinearLayoutManager(getActivity()));

                        warehouseRaiseAdapter = new WarehouseRaiseAdapter(data);

                        warehouseRaiseAdapter.openLoadAnimation();
                        rvMyraise.setAdapter(warehouseRaiseAdapter);


                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            ToastUtil.showToast(getActivity(), "请求网络失败，请稍后重试");
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

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
}
