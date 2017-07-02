package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登录状态
public class StatusActivity extends BaseActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.iv_close)
    ImageView iv_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        TMApplication.instance.addActivity(this);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login, R.id.bt_register,R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:

                btLogin.setBackgroundResource(R.drawable.bt_shape_status);
                btLogin.setTextColor(Color.parseColor("#FFAC00"));
                btRegister.setBackgroundResource(R.drawable.bt_shape_nostatus);
                btRegister.setTextColor(Color.parseColor("#ffffff"));
                startActivity(new Intent(this,LoginActivity.class));

                break;
            case R.id.bt_register:

                btLogin.setBackgroundResource(R.drawable.bt_shape_nostatus);
                btLogin.setTextColor(Color.parseColor("#ffffff"));
                btRegister.setBackgroundResource(R.drawable.bt_shape_status);
                btRegister.setTextColor(Color.parseColor("#FFAC00"));
                startActivity(new Intent(this,RegisterActivity.class));

                break;

            case R.id.iv_close:
                finish();
                break;
        }
    }
}
