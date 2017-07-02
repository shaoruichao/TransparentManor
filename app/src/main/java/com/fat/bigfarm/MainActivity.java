package com.fat.bigfarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.ui.HomeActivity;
import com.fat.bigfarm.ui.activity.Navigation;

/**
 * 启动页
 */
public class MainActivity extends BaseActivity {

    private Boolean user_first;
    private SharedPreferences setting;
    private static final String SHARE_APP_TAG = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting = getSharedPreferences(SHARE_APP_TAG, 0);
        user_first = setting.getBoolean("FIRST", true);

        handler.sendEmptyMessageDelayed(0,1000);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //进入主页面
            if (user_first){
                setting.edit().putBoolean("FIRST", false).commit();
                startActivity(new Intent(MainActivity.this, Navigation.class));
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                //淡入淡出效果
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
            }else {

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
            }
            super.handleMessage(msg);
        }
    };

}
