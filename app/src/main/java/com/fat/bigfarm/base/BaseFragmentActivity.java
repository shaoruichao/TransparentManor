package com.fat.bigfarm.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.nohttp.CallServer;
import com.fat.bigfarm.nohttp.HttpListener;
import com.yolanda.nohttp.rest.Request;


/**
 *
 */
public class BaseFragmentActivity extends FragmentActivity {

    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        CallServer.getRequestInstance().add(what, request, callback, canCancel, isLoading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TMApplication.instance.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TMApplication.instance.finishActivity(this);
    }
}
