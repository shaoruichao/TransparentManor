package com.fat.bigfarm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//验证码
public class CodeActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.text1)
    TextInputLayout text1;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    private String return_id;
    private String phone;

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        return_id = intent.getStringExtra("return_id");
        tvCode.setText(return_id);
        phone = intent.getStringExtra("phone");
        tvPhone.setText("我们向"+phone+"发送了一个验证码，请在下方输入验证码");

    }

    @OnClick({R.id.bt_back, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.iv_next:

                code = tvCode.getText().toString();

                if (TextUtils.isEmpty(code)) {
                    ToastUtil.showToast(getBaseContext(),"请输入验证码");
                    return;
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("code",code);
                    intent.putExtra("phone",phone);
                    intent.setClass(getBaseContext(),PassWordActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }
}
