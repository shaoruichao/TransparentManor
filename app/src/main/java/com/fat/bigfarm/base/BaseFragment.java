package com.fat.bigfarm.base;


import android.support.v4.app.Fragment;

import com.fat.bigfarm.nohttp.CallServer;
import com.fat.bigfarm.nohttp.HttpListener;
import com.yolanda.nohttp.rest.Request;

public abstract class BaseFragment extends Fragment {


    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        CallServer.getRequestInstance().add(what, request, callback, canCancel, isLoading);
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


//    protected Activity mActivity;// 上下文
//
//    protected boolean isVisible;
//    /**
//     * 在这里实现Fragment数据的缓加载.
//     * @param isVisibleToUser
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(getUserVisibleHint()) {
//            isVisible = true;
//            onVisible();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }
//    protected void onVisible(){
//        lazyLoad();
//    }
//    protected abstract void lazyLoad();
//    protected void onInvisible(){}

}
