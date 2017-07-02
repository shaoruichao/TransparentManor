package com.fat.bigfarm.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv_about_logo)
    ImageView ivAboutLogo;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_companyname)
    TextView tvCompanyname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_back)
    public void onViewClicked() {
        finish();
    }
}
