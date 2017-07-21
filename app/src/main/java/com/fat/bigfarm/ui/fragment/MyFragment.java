package com.fat.bigfarm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseFragment;
import com.fat.bigfarm.ui.activity.AddressManageActivity;
import com.fat.bigfarm.ui.activity.MyOrderActivity;
import com.fat.bigfarm.ui.activity.MyWarehouseActivity;
import com.fat.bigfarm.ui.activity.ProblemActivity;
import com.fat.bigfarm.ui.activity.SetActivity;
import com.fat.bigfarm.ui.activity.StatusActivity;
import com.fat.bigfarm.view.CircleImageView;
import com.fat.bigfarm.ui.activity.DataActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {


    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.iv_myorder)
    ImageView ivMyorder;
    @BindView(R.id.rl_myorder)
    RelativeLayout rlMyorder;
    @BindView(R.id.iv_obligtion)
    ImageView ivObligtion;
    @BindView(R.id.rl_obligation)
    RelativeLayout rlObligation;
    @BindView(R.id.iv_sendgoods)
    ImageView ivSendgoods;
    @BindView(R.id.rl_sendgoods)
    RelativeLayout rlSendgoods;
    @BindView(R.id.iv_beenshipped)
    ImageView ivBeenshipped;
    @BindView(R.id.rl_beenshipped)
    RelativeLayout rlBeenshipped;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv_warehouse)
    ImageView ivWarehouse;
    @BindView(R.id.rl_warehouse)
    RelativeLayout rlWarehouse;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv_integral)
    ImageView ivIntegral;
    @BindView(R.id.rl_integral)
    RelativeLayout rlIntegral;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv_goodsshelves)
    ImageView ivGoodsshelves;
    @BindView(R.id.rl_openshop)
    RelativeLayout rlOpenshop;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.rl_set)
    RelativeLayout rlSet;
    private View view;
    private String nickname;
    private String avatar;
    private String status;
    private String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        nickname = TMApplication.instance.sp.getString("nickname", "");
        avatar = TMApplication.instance.sp.getString("avatar", "");
        status = TMApplication.instance.sp.getString("status", "");
        userid = TMApplication.instance.sp.getString("userid", "");

        if (nickname != null && !nickname.equals("")){
            tvName.setText(nickname);
        }
        if (avatar != null && !avatar.equals("")){
            Picasso.with(getActivity()).load(avatar).into(ivHead);
        }

    }

    @OnClick({R.id.iv_head, R.id.rl_myorder, R.id.rl_obligation, R.id.rl_sendgoods, R.id.rl_beenshipped, R.id.rl_warehouse, R.id.rl_integral, R.id.rl_address, R.id.rl_openshop, R.id.rl_set})
    public void onClick(View view) {
        switch (view.getId()) {
            //头像
            case R.id.iv_head:
                if (status.equals("1")){
                    startActivity(new Intent(getActivity(),DataActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }

                break;
            //我的订单
            case R.id.rl_myorder:
                if (status.equals("1")){
                    startActivity(new Intent(getActivity(), MyOrderActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //待付款
            case R.id.rl_obligation:
                if (status.equals("1")){
                    Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                    intent.putExtra("userloginflag", 1);
                    startActivity(intent);
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //待发货
            case R.id.rl_sendgoods:
                if (status.equals("1")){
                    Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                    intent.putExtra("userloginflag", 2);
                    startActivity(intent);
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //已发货
            case R.id.rl_beenshipped:
                if (status.equals("1")){
                    Intent intent = new Intent(getActivity(),MyOrderActivity.class);
                    intent.putExtra("userloginflag", 3);
                    startActivity(intent);
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //我的仓库
            case R.id.rl_warehouse:
                if (status.equals("1")){
                    startActivity(new Intent(getActivity(), MyWarehouseActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //我的积分
            case R.id.rl_integral:
                if (status.equals("1")){

                }else {
//                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //地址管理
            case R.id.rl_address:
                if (status.equals("1")){
                    startActivity(new Intent(getActivity(), AddressManageActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //常见问题
            case R.id.rl_openshop:
                if (status.equals("1")){
                    startActivity(new Intent(getActivity(), ProblemActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
            //系统设置
            case R.id.rl_set:
                if (status.equals("1")){
                    startActivity(new Intent(getActivity(), SetActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), StatusActivity.class));
                }
                break;
        }
    }
}
