package com.fat.bigfarm.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//其他方式登录
public class OtherActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.bt_wx)
    Button btWx;
    @BindView(R.id.bt_wb)
    Button btWb;
    @BindView(R.id.bt_qq)
    Button btQq;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_back, R.id.bt_wx, R.id.bt_wb, R.id.bt_qq, R.id.bt_login, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_wx:
                break;
            case R.id.bt_wb:
                break;
            case R.id.bt_qq:
                break;
            case R.id.bt_login:
                break;
            case R.id.bt_register:
                break;
        }
    }
}
