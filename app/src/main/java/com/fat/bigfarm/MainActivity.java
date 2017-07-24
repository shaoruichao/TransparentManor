package com.fat.bigfarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.ui.HomeActivity;
import com.fat.bigfarm.ui.activity.Navigation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 启动页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.tv)
    TextView tv;
    private Boolean user_first;
    private SharedPreferences setting;
    private static final String SHARE_APP_TAG = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Glide.with(getBaseContext())
                .load(R.drawable.a)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);

        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);   //设置透明度动画效果
        alphaAnimation.setDuration(1000);                  //设置持续时间
//        alphaAnimation.setStartOffset(500);
        image1.setAnimation(alphaAnimation); //设置动画
        alphaAnimation.start();

        Animation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);   //设置透明度动画效果
        alphaAnimation1.setDuration(2000);                  //设置持续时间
//        alphaAnimation1.setStartOffset(1000);               //设置启动时间
//        tv.setVisibility(View.VISIBLE);
        tv.setAnimation(alphaAnimation1); //设置动画
        alphaAnimation1.start();

        setting = getSharedPreferences(SHARE_APP_TAG, 0);
        user_first = setting.getBoolean("FIRST", true);

        handler.sendEmptyMessageDelayed(0, 2000);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //进入主页面
            if (user_first) {
                setting.edit().putBoolean("FIRST", false).commit();
                startActivity(new Intent(MainActivity.this, Navigation.class));
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                //淡入淡出效果
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
            } else {

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
            }
            super.handleMessage(msg);
        }
    };

}
