package com.fat.bigfarm.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fat.bigfarm.R;
import com.fat.bigfarm.adapter.EarningsDetailAdapter;
import com.fat.bigfarm.adapter.WarehouseEarningsAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.EarningsDetail;
import com.fat.bigfarm.entry.IncomeEdit;
import com.fat.bigfarm.entry.Retention;
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
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.fat.bigfarm.app.TMApplication.mContext;

/**
 * 收益详情
 */
public class EarningsDetailActivity extends BaseActivity {

    private static final String TAG = "EarningsDetailActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.rl_source)
    RelativeLayout rlSource;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_length)
    TextView tvLength;
    @BindView(R.id.rl_length)
    RelativeLayout rlLength;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.rl_status)
    RelativeLayout rlStatus;
    @BindView(R.id.rv_earnings_detail)
    RecyclerView rvEarningsDetail;
    @BindView(R.id.bt_example)
    Button btExample;
    @BindView(R.id.bt_sell)
    Button btSell;
    @BindView(R.id.bt_retention)
    Button btRetention;
    @BindView(R.id.rl_operation)
    RelativeLayout rlOperation;
    @BindView(R.id.bt_cancle_right)
    Button btCancleRight;
    @BindView(R.id.rl_operation2)
    RelativeLayout rlOperation2;
    private String fid;
    private String username;
    private String id;
    private String count;
    private String content;

    private EarningsDetailAdapter earningsDetailAdapter;
    private List<EarningsDetail.DataBean.IncomeBean> income;
    private String fostertime;
    private String name;
    private EarningsDetail.DataBean data;
    private EarningsDetail earningsDetail;
    private String thumb;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        fid = intent.getStringExtra("fid");
        username = TMApplication.instance.sp.getString("username", "");


    }

    @Override
    protected void onResume() {
        super.onResume();
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        hud.show();
        rlOperation.setVisibility(View.GONE);
        rlOperation2.setVisibility(View.GONE);

        GetEarningsDetails(fid);
    }

    //收益详情
    private void GetEarningsDetails(String id) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.MYINCOME_DETAILS + id + "&username=" + username);
        request(0, request, earningsDetailsListener, true, true);
    }

    private HttpListener<JSONObject> earningsDetailsListener = new HttpListener<JSONObject>() {


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            try {

                JSONObject js = response.get();
                Log.e(TAG, "earningsDetailsListener: " + js);
                int code = js.getInt("code");
                if (code == 200) {
                    earningsDetail = JsonUtil.parseJsonToBean(js.toString(), EarningsDetail.class);
                    if (earningsDetail != null) {
                        data = earningsDetail.getData();
                        name = data.getName();
                        tvSource.setText(name);
                        thumb = data.getThumb();
                        fostertime = data.getFostertime();
                        tvLength.setText(fostertime);
                        income = data.getIncome();


                        rvEarningsDetail.setHasFixedSize(true);
                        rvEarningsDetail.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                        earningsDetailAdapter = new EarningsDetailAdapter(income);

                        earningsDetailAdapter.openLoadAnimation();
                        rvEarningsDetail.setAdapter(earningsDetailAdapter);

                        earningsDetailAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {


                            @Override
                            public void onItemClick(View view, int i) {
                                for (int x = 0; x < income.size(); x++) {
                                    income.get(x).setSelect(false);
                                }
                                income.get(i).setSelect(true);
                                earningsDetailAdapter.notifyDataSetChanged();
                                id = income.get(i).getId();
                                content = income.get(i).getContent();
                                count = income.get(i).getCount();

                                String status = income.get(i).getStatus();
                                if (status.equals("1")) {
                                    tvStatus.setText("转赠中");
                                    rlOperation.setVisibility(View.GONE);
                                    rlOperation2.setVisibility(View.VISIBLE);
                                } else if (status.equals("2")) {
                                    tvStatus.setText("已转赠");
                                    rlOperation.setVisibility(View.GONE);
                                    rlOperation2.setVisibility(View.GONE);
                                } else if (status.equals("3")) {
                                    tvStatus.setText("出售中");
                                    rlOperation.setVisibility(View.GONE);
                                    rlOperation2.setVisibility(View.GONE);
                                } else if (status.equals("5")) {
                                    tvStatus.setText("待处理");
                                    rlOperation.setVisibility(View.VISIBLE);
                                    rlOperation2.setVisibility(View.GONE);
                                } else if (status.equals("7")) {
                                    tvStatus.setText("自留中");
                                    rlOperation.setVisibility(View.GONE);
                                    rlOperation2.setVisibility(View.GONE);
                                }

                            }
                        });


                    }
                }
//                if (code == 200) {
//                    retention = JsonUtil.parseJsonToBean(js.toString(), Retention.class);
//                    if (retention != null) {
//
//                        data = retention.getData();
//                        shopname = data.getShopname();
//                        tvShopname.setText(shopname);
//
//                        foster = data.getFoster();
//                        income = foster.getIncome();
////                        if (income.size() == 1) {
//                        rl1.setVisibility(View.VISIBLE);
//                        tvCount1.setText(income.getCount());
//                        tvUnit1.setText(income.getUnit());
//                        tvName1.setText(income.getName());
//
//                        name = foster.getName();
//                        tvEarningstitle.setText(name);
//                        thumb = foster.getThumb();
//                        Glide.with(getBaseContext()).load(thumb).crossFade().into(ivThumb);
//                        content = foster.getContent();
//                        price = foster.getPrice();
//                        unit = foster.getUnit();
//                        //保留两位小数点
//                        String price_point = String .format("%.2f",Double.valueOf(price));
//                        tvContent.setText("¥"+price_point+"元/"+unit);
//
//                    }
//                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {
            scheduleDismiss();
            ToastUtil.showToast(getBaseContext(), "请求网络失败，请稍后重试");
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

    @OnClick({R.id.bt_back, R.id.bt_example, R.id.bt_sell, R.id.bt_retention, R.id.bt_cancle_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_example:
                //收益转赠
                PostIncomeEdit(id);
                break;
            case R.id.bt_sell:
                //收益出售
                SellDialog();
                break;
            case R.id.bt_retention:
                //收益自留
                Intent intent = new Intent();
                intent.putExtra("id", id);
                intent.setClass(mContext, RetentionActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.bt_cancle_right:
                //取消转赠
                PostIncomeEditCancle(id);
                break;
        }
    }

    /**
     * 取消转赠
     * @param id
     */
    private void PostIncomeEditCancle(String id){
        PostRequest tag = OkGo.post(AllUrl.INCOME_EDIT).tag(this);
        tag.params("id",id);
        tag.params("status","5");

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                IncomeEdit incomeEdit = JsonUtil.parseJsonToBean(s.toString(), IncomeEdit.class);
                int code = incomeEdit.getCode();
                String msg = incomeEdit.getMsg();
                if (code == 200){

                    rlOperation.setVisibility(View.GONE);
                    rlOperation2.setVisibility(View.GONE);
                    hud = KProgressHUD.create(EarningsDetailActivity.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                    hud.show();
                    GetEarningsDetails(fid);

                }else {
                    ToastUtil.showToast(mContext,msg);
                }
            }
        });
    }
    /**
     * 收益出售
     */
    private void SellDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this, R.style.MyDialogStyle).create();
        dlg.setCanceledOnTouchOutside(true);
        dlg.show();
        Window window = dlg.getWindow();
        //设置窗口的内容页面
        window.setContentView(R.layout.earnings_sell_dialog);
        //取消
        Button bt_cancel = (Button) window.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });
        //确定
        Button bt_ok = (Button) window.findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
                Intent intent1 = new Intent();
                intent1.putExtra("id", id);
//                intent1.putExtra("shopname", shopname);
                intent1.putExtra("count", count);
                intent1.putExtra("thumb", thumb);
                intent1.putExtra("name", name);
                intent1.putExtra("content", content);
                intent1.setClass(mContext, SellActivity.class);
                mContext.startActivity(intent1);

            }
        });

    }

    /**
     * 收益转赠
     *
     * @param id
     */
    private void PostIncomeEdit(final String id) {
        PostRequest tag = OkGo.post(AllUrl.INCOME_EDIT).tag(this);
        tag.params("id", id);
        tag.params("status", "1");

        tag.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                Log.e(TAG, "onSuccess1: " + s);

                IncomeEdit incomeEdit = JsonUtil.parseJsonToBean(s.toString(), IncomeEdit.class);
                int code = incomeEdit.getCode();
                String msg = incomeEdit.getMsg();
                if (code == 200) {
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    intent.setClass(getBaseContext(), SellExampleActivity.class);
                    startActivity(intent);
                } else {
                    ToastUtil.showToast(getBaseContext(), msg);
                }
            }
        });
    }

}
