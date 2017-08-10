package com.fat.bigfarm.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.utils.DataCleanManager;
import com.fat.bigfarm.utils.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_wifi)
    TextView tvWifi;
    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tv_remove)
    TextView tvRemove;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.bt_remove)
    Button btRemove;
    @BindView(R.id.rl_remove)
    RelativeLayout rlRemove;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.iv_switch1)
    ImageView ivSwitch1;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.iv_about)
    ImageView ivAbout;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.iv_recommended)
    ImageView ivRecommended;
    @BindView(R.id.rl_recommended)
    RelativeLayout rlRecommended;
    @BindView(R.id.iv_agreement)
    ImageView ivAgreement;
    @BindView(R.id.rl_agreement)
    RelativeLayout rlAgreement;
    @BindView(R.id.iv_update)
    ImageView ivUpdate;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.tv_update)
    TextView tvUpdate;

    private String mark_switch;
    private String mark_switch1;

    private int versionCode;
    private String versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);


        mark_switch = TMApplication.instance.sp.getString("mark_switch", mark_switch);
        if (mark_switch != null && !mark_switch.equals("")) {
            if (mark_switch.equals("1")) {
                ivSwitch.setImageResource(R.drawable.guan);
            } else {
                ivSwitch.setImageResource(R.drawable.kai);
            }
        }

        mark_switch1 = TMApplication.instance.sp.getString("mark_switch1", mark_switch1);
        if (mark_switch1 != null && !mark_switch1.equals("")) {
            if (mark_switch1.equals("1")) {
                ivSwitch1.setImageResource(R.drawable.guan);
            } else {
                ivSwitch1.setImageResource(R.drawable.kai);
            }
        }

        getNowVresion();
//        getVersion();

        //计算缓存
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
            tvCache.setText("(" + totalCacheSize + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*获取xml文件当前的vresion 版本号和版本name*/
    private void getNowVresion() {

        PackageManager packageManager =
                this.getPackageManager();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
            versionName = packageInfo.versionName;

            tvUpdate.setText("透明农庄  V"+versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.bt_back, R.id.iv_switch, R.id.bt_remove, R.id.iv_switch1, R.id.rl_about, R.id.rl_recommended, R.id.rl_agreement, R.id.rl_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.iv_switch:

                if (mark_switch != null && !mark_switch.equals("")) {
                    if (mark_switch.equals("0")) {
                        ivSwitch.setImageResource(R.drawable.guan);
                        mark_switch = "1";

                    } else {
                        ivSwitch.setImageResource(R.drawable.kai);
                        mark_switch = "0";
                    }
                } else {
                    ivSwitch.setImageResource(R.drawable.kai);
                    mark_switch = "0";
                }

                SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                edit.putString("mark_switch", mark_switch);
                edit.commit();
                break;
            case R.id.bt_remove:

                DataCleanManager.clearAllCache(getApplicationContext());
                tvCache.setText("");
                ToastUtil.showToast(getBaseContext(),"清除完毕");

                break;
            case R.id.iv_switch1:

                if (mark_switch1 != null && !mark_switch1.equals("")) {
                    if (mark_switch1.equals("0")) {
                        ivSwitch1.setImageResource(R.drawable.guan);
                        mark_switch1 = "1";

                    } else {
                        ivSwitch1.setImageResource(R.drawable.kai);
                        mark_switch1 = "0";
                    }
                } else {
                    ivSwitch1.setImageResource(R.drawable.kai);
                    mark_switch1 = "0";
                }

                SharedPreferences.Editor edit1 = TMApplication.instance.sp.edit();
                edit1.putString("mark_switch1", mark_switch1);
                edit1.commit();

                break;
            case R.id.rl_about:

                startActivity(new Intent(this,AboutActivity.class));
                break;
            case R.id.rl_recommended:

                ShareWe();
                break;
            case R.id.rl_agreement:
                startActivity(new Intent(this,AgreementActivity.class));
                break;
            case R.id.rl_update:
                ToastUtil.showToast(getBaseContext(),"当前已经是最新版本");
                break;
        }
    }

    //推荐我们
    private void ShareWe(){
        final AlertDialog dlg = new AlertDialog.Builder(this, R.style.MyDialogStyle).create();
        //点击空白区域消失
        dlg.setCanceledOnTouchOutside(true);
        dlg.show();
        Window window = dlg.getWindow();
        // 可以在此设置显示动画
        window.setWindowAnimations(R.style.mystyle);
        window.setGravity(Gravity.BOTTOM);
        //内容区域外围的灰色去掉了
//        window.setDimAmount(0);

        WindowManager.LayoutParams wl = window.getAttributes();

        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dlg.onWindowAttributesChanged(wl);

        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.share_bottom_dialog);
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.share_bottom_dialog, null);
//        shareBottomPopupDialog = new ShareBottomPopupDialog(this, dialogView);
//        shareBottomPopupDialog.showPopup(rlDetails);

        //创建TranslateAnimation位移动画
        TranslateAnimation trananimation=new TranslateAnimation(0, 0,300,0);
        //设置动画时间
        trananimation.setDuration(250);
        //设置是否记录移动后的位置，true时动画将停留在当前位置，false将回到开始位置
        trananimation.setFillAfter(true);
        //设置插值器，可以理解为用于改变运动形式的东西
        //（现在设置的运动形式类似于自由落体，会有弹跳效果）
//        trananimation.setInterpolator(new BounceInterpolator());

        //创建TranslateAnimation位移动画
        TranslateAnimation trananimation1=new TranslateAnimation(0, 0,300,0);
        //设置动画时间
        trananimation1.setDuration(250);
        trananimation1.setStartOffset(250);
        //设置是否记录移动后的位置，true时动画将停留在当前位置，false将回到开始位置
        trananimation1.setFillAfter(true);
        //设置插值器，可以理解为用于改变运动形式的东西
        //（现在设置的运动形式类似于自由落体，会有弹跳效果）
//        trananimation1.setInterpolator(new BounceInterpolator());

        //创建TranslateAnimation位移动画
        TranslateAnimation trananimation2=new TranslateAnimation(0, 0,300,0);
        //设置动画时间
        trananimation2.setDuration(250);
        trananimation2.setStartOffset(500);
        //设置是否记录移动后的位置，true时动画将停留在当前位置，false将回到开始位置
        trananimation2.setFillAfter(true);
        //设置插值器，可以理解为用于改变运动形式的东西
        //（现在设置的运动形式类似于自由落体，会有弹跳效果）
//        trananimation2.setInterpolator(new BounceInterpolator());

        //创建TranslateAnimation位移动画
        TranslateAnimation trananimation3=new TranslateAnimation(0, 0,300,0);
        //设置动画时间
        trananimation3.setDuration(250);
        trananimation3.setStartOffset(750);
        //设置是否记录移动后的位置，true时动画将停留在当前位置，false将回到开始位置
        trananimation3.setFillAfter(true);
        //设置插值器，可以理解为用于改变运动形式的东西
        //（现在设置的运动形式类似于自由落体，会有弹跳效果）
//        trananimation3.setInterpolator(new BounceInterpolator());

        //创建TranslateAnimation位移动画
        TranslateAnimation trananimation4=new TranslateAnimation(0, 0,300,0);
        //设置动画时间
        trananimation4.setDuration(250);
        trananimation4.setStartOffset(1000);
        //设置是否记录移动后的位置，true时动画将停留在当前位置，false将回到开始位置
        trananimation4.setFillAfter(true);
        //设置插值器，可以理解为用于改变运动形式的东西
        //（现在设置的运动形式类似于自由落体，会有弹跳效果）
//        trananimation4.setInterpolator(new BounceInterpolator());

        //创建TranslateAnimation位移动画
        TranslateAnimation trananimation5=new TranslateAnimation(0, 0,300,0);
        //设置动画时间
        trananimation5.setDuration(250);
        trananimation5.setStartOffset(1250);
        //设置是否记录移动后的位置，true时动画将停留在当前位置，false将回到开始位置
        trananimation5.setFillAfter(true);
        //设置插值器，可以理解为用于改变运动形式的东西
        //（现在设置的运动形式类似于自由落体，会有弹跳效果）
//        trananimation5.setInterpolator(new BounceInterpolator());

        //分享到
        TextView textView = (TextView)window.findViewById(R.id.textView);
        textView.startAnimation(trananimation);

        //微信
        RelativeLayout share_weixin_rl = (RelativeLayout) window.findViewById(R.id.share_weixin_rl);
        share_weixin_rl.startAnimation(trananimation1);
        ImageButton share_weixin_btn = (ImageButton) window.findViewById(R.id.share_weixin_btn);
        share_weixin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb("http://a.app.qq.com/o/simple.jsp?pkgname=com.fat.bigfarm");
                web.setTitle("透明农庄");//标题
                web.setThumb(new UMImage(SetActivity.this, R.mipmap.sharelogo));  //缩略图
                web.setDescription("我的一亩三分地");//描述

                new ShareAction(SetActivity.this)
                        .withMedia(web)
                        .setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener)
                        .share();
                dlg.cancel();
            }
        });

        //微信朋友圈
        RelativeLayout share_weixinfriend_rl = (RelativeLayout) window.findViewById(R.id.share_weixinfriend_rl);
        share_weixinfriend_rl.startAnimation(trananimation2);
        ImageButton share_weixinfriend_btn = (ImageButton) window.findViewById(R.id.share_weixinfriend_btn);
        share_weixinfriend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb("http://a.app.qq.com/o/simple.jsp?pkgname=com.fat.bigfarm");
                web.setTitle("透明农庄");//标题
                web.setThumb(new UMImage(SetActivity.this, R.mipmap.sharelogo));  //缩略图
                web.setDescription("我的一亩三分地");//描述

                new ShareAction(SetActivity.this)
                        .withMedia(web)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener)
                        .share();
                dlg.cancel();
            }
        });

        //微博
        RelativeLayout share_weibo_rl = (RelativeLayout) window.findViewById(R.id.share_weibo_rl);
        share_weibo_rl.startAnimation(trananimation3);
        ImageButton share_to_weibo = (ImageButton) window.findViewById(R.id.share_to_weibo);
        share_to_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb("http://a.app.qq.com/o/simple.jsp?pkgname=com.fat.bigfarm");
                web.setTitle("透明农庄");//标题
                web.setThumb(new UMImage(SetActivity.this,  R.mipmap.sharelogo));  //缩略图
                web.setDescription("我的一亩三分地");//描述

                new ShareAction(SetActivity.this)
                        .withMedia(web)
                        .setPlatform(SHARE_MEDIA.SINA)
                        .setCallback(shareListener)
                        .share();
                dlg.cancel();
            }
        });

        //qq空间
        RelativeLayout share_to_qq_zone_rl = (RelativeLayout) window.findViewById(R.id.share_to_qq_zone_rl);
        share_to_qq_zone_rl.startAnimation(trananimation4);
        ImageButton share_to_qq_zone_btn = (ImageButton) window.findViewById(R.id.share_to_qq_zone_btn);
        share_to_qq_zone_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb("http://a.app.qq.com/o/simple.jsp?pkgname=com.fat.bigfarm" );
                web.setTitle("透明农庄");//标题
                web.setThumb(new UMImage(SetActivity.this,  R.mipmap.sharelogo));  //缩略图
                web.setDescription("我的一亩三分地");//描述

                new ShareAction(SetActivity.this)
                        .withMedia(web)
                        .setPlatform(SHARE_MEDIA.QZONE)
                        .setCallback(shareListener)
                        .share();
                dlg.cancel();
            }
        });
        //取消
        Button share_pop_cancle_btn = (Button) window.findViewById(R.id.share_pop_cancle_btn);
        ImageView share_pop_cancle_iv = (ImageView) window.findViewById(R.id.share_pop_cancle_iv);
        share_pop_cancle_btn.startAnimation(trananimation5);
        share_pop_cancle_iv.startAnimation(trananimation5);
        share_pop_cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.cancel();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(SetActivity.this).onActivityResult(requestCode,resultCode,data);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
//            SocializeUtils.safeShowDialog(shareBottomPopupDialog);
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(SetActivity.this,"成功了",Toast.LENGTH_LONG).show();
//            SocializeUtils.safeCloseDialog(dialog);
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(SetActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(SetActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

}
