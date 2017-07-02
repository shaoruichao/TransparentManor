package com.fat.bigfarm.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.nohttp.CallServer;
import com.fat.bigfarm.nohttp.HttpListener;
import com.yolanda.nohttp.rest.Request;

public class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        // 添加到Activity集合
        TMApplication.instance.addActivity(this);

    }

    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        CallServer.getRequestInstance().adds(this, what, request, callback, canCancel, isLoading);
    }

    /**
     * 返回键判断 走finish();
     */
    public void onBackPressed() {
        super.onBackPressed();
        onDestroy();
    }


	/*
	 * private long exitTime = 0;
	 *
	 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) { if
	 * (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() ==
	 * KeyEvent.ACTION_DOWN) { if ((System.currentTimeMillis() - exitTime) >
	 * 2000) { Toast.makeText(getApplicationContext(), "再按一次退出程序",
	 * Toast.LENGTH_SHORT).show(); exitTime = System.currentTimeMillis(); } else
	 * { finish(); System.exit(0); } return true; } return
	 * super.onKeyDown(keyCode, event); }
	 */

    @Override
    protected void onDestroy() {

        super.onDestroy();
        // 结束Activity&从集合中移除
//        TMApplication.instance.finishActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
