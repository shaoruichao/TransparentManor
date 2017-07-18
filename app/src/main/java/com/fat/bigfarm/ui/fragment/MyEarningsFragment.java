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
import android.widget.Button;

import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.WarehouseEarningsAdapter;
import com.fat.bigfarm.adapter.WarehouseEarningsitemAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseOrderFragment;
import com.fat.bigfarm.entry.Earnings;
import com.fat.bigfarm.entry.IncomeEdit;
import com.fat.bigfarm.entry.Raise;
import com.fat.bigfarm.eventbus.MessageEvent;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * 我的仓库-收益
 */
public class MyEarningsFragment extends BaseOrderFragment{

    private static final String TAG = "MyEarningsFragment";

    @BindView(R.id.rv_myraise)
    RecyclerView rvMyraise;
    @BindView(R.id.sw)
    SwipeRefreshLayout sw;
    private View view;

    private KProgressHUD hud;
    private String userid;

    private WarehouseEarningsAdapter warehouseEarningsAdapter;
    private List<Earnings.DataBean> data;
    private Earnings earnings;
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_earnings, container, false);
        ButterKnife.bind(this, view);

        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();

        userid = TMApplication.instance.sp.getString("userid", "");
        username = TMApplication.instance.sp.getString("username", "");

        //注册事件
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        initView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        GetData();
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
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.INCOME+userid+"&username="+username);
        request(0, request, incomeListener, true, true);
    }

    private HttpListener<JSONObject> incomeListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            try {
                JSONObject js = response.get();
                Log.e(TAG, "incomeListener: "+js );
                int code = js.getInt("code");
                if (code == 200){
                    earnings = JsonUtil.parseJsonToBean(js.toString(), Earnings.class);
                    if (earnings != null){

                        data = earnings.getData();

                        rvMyraise.setHasFixedSize(true);
                        rvMyraise.setLayoutManager(new LinearLayoutManager(getActivity()));

                        warehouseEarningsAdapter = new WarehouseEarningsAdapter(data);

                        warehouseEarningsAdapter.openLoadAnimation();
                        rvMyraise.setAdapter(warehouseEarningsAdapter);



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

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(getActivity());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();
        GetData();
    }

    //    @Override
//    public void onItemClick(Button bt_cancle_right, int earningsPos, int postion) {
//        Log.e(TAG, "onItemClick: "+11111 );
//        String id = data.get(earningsPos).getFoster().get(postion).getIncome().getId();
//        PostIncomeEditCancle(id);
//    }
//
//    private void PostIncomeEditCancle(final String id){
//        PostRequest tag = OkGo.post(AllUrl.INCOME_EDIT).tag(this);
//        tag.params("id",id);
//        tag.params("status","5");
//
//        tag.execute(new StringCallback() {
//            @Override
//            public void onSuccess(String s, Call call, okhttp3.Response response) {
//                Log.e(TAG, "onSuccess1: " + s);
//
//                IncomeEdit incomeEdit = JsonUtil.parseJsonToBean(s.toString(), IncomeEdit.class);
//                int code = incomeEdit.getCode();
//                String msg = incomeEdit.getMsg();
//                if (code == 200){
//
//                    hud = KProgressHUD.create(getActivity())
//                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
//                    hud.show();
//                    GetData();
//
//                }else {
//                    ToastUtil.showToast(getActivity(),msg);
//                }
//            }
//        });
//    }

}
