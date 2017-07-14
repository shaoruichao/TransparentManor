package com.fat.bigfarm.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.base.BaseActivity;
import com.fat.bigfarm.entry.HeadImage;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.HomeActivity;
import com.fat.bigfarm.utils.ImageUtil;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.view.ChangeBirthdayPopwindow;
import com.fat.bigfarm.view.CircleImageView;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fat.bigfarm.app.TMApplication.mContext;

//个人信息
public class DataActivity extends BaseActivity {

    private static final String TAG = "DataActivity";

    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_nickname_ok)
    Button tvNicknameOk;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.iv_nan)
    ImageView ivNan;
    @BindView(R.id.tv_nan)
    TextView tvNan;
    @BindView(R.id.rl_nan)
    RelativeLayout rlNan;
    @BindView(R.id.iv_nv)
    ImageView ivNv;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl_nv)
    RelativeLayout rlNv;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;
    @BindView(R.id.tv_showphone)
    TextView tvShowphone;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.rl_wx)
    RelativeLayout rlWx;
    @BindView(R.id.tv_wb)
    TextView tvWb;
    @BindView(R.id.iv_wb)
    ImageView ivWb;
    @BindView(R.id.rl_wb)
    RelativeLayout rlWb;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.rl_qq)
    RelativeLayout rlQq;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;

    private Bitmap head;// 头像Bitmap
    // 截图返回的uri
    private Uri outPutUri;
    private String nickname;
    private String avatar;

    private String name;
    private String userid;
    private String sex;
    private String birth;
    private String sp_sex;
    private String birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);

        // 添加到Activity集合
        TMApplication.instance.addActivity(this);

        avatar = TMApplication.instance.sp.getString("avatar", "");
        if (avatar != null && !avatar.equals("")){
            Picasso.with(this).load(avatar).into(ivHead);
        }

    }



    //隐藏软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        nickname = TMApplication.instance.sp.getString("nickname", "");

        userid = TMApplication.instance.sp.getString("userid", "");
        sp_sex = TMApplication.instance.sp.getString("sex", "");
        birthday = TMApplication.instance.sp.getString("birthday", "");

        if (nickname != null && !nickname.equals("")){
            tvName.setText(nickname);
        }

        if (sp_sex != null && !sp_sex.equals("")){
            if (sp_sex.equals("男")){
                ivNan.setImageResource(R.drawable.dot_check);
                ivNv.setImageResource(R.drawable.dot_uncheck);
            }else if (sp_sex.equals("女")){
                ivNv.setImageResource(R.drawable.dot_check);
                ivNan.setImageResource(R.drawable.dot_uncheck);
            }
        }
        if (birthday != null && !birthday.equals("")) {
            tvBirthday.setText(birthday);
        }

    }

    @OnClick({R.id.bt_back, R.id.iv_head, R.id.rl_name, R.id.rl_nan, R.id.rl_nv, R.id.rl_birthday, R.id.rl_phone, R.id.rl_password, R.id.rl_wx, R.id.rl_wb, R.id.rl_qq, R.id.rl_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            //修改头像
            case R.id.iv_head:
                showPhotoDialog();
                break;
            case R.id.rl_name:
//                alterName();

                startActivity(new Intent(this,AlterNameActivity.class));
                break;
            case R.id.rl_nan:
                sex = "男";
                ivNan.setImageResource(R.drawable.dot_check);
                ivNv.setImageResource(R.drawable.dot_uncheck);
                getUpdate();
                break;
            case R.id.rl_nv:
                sex = "女";
                ivNv.setImageResource(R.drawable.dot_check);
                ivNan.setImageResource(R.drawable.dot_uncheck);
                getUpdate();
                break;
            case R.id.rl_birthday:

                alterBirthday();

                break;
            case R.id.rl_phone:
                break;
            case R.id.rl_password:
                startActivity(new Intent(this,ForgotPasswordActivity.class));
                break;
            case R.id.rl_wx:
                break;
            case R.id.rl_wb:
                break;
            case R.id.rl_qq:
                break;
            //退出
            case R.id.rl_exit:

                exit();

                break;
        }
    }

    /**
     * 头像
     */
    private void showPhotoDialog() {

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
        window.setContentView(R.layout.alertdialog);
        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
        tv_paizhao.setText("拍照");
        tv_paizhao.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                        Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开

                dlg.cancel();
            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText("相册");
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dlg.cancel();
            }
        });
        //取消
        Button bt_cancel = (Button) window.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
//                    Bundle extras = data.getExtras();
//                    head = extras.getParcelable("data");
//                    head = getBitmapFromBigImagByUri(outPutUri);//这个方法也是可行，应该是只是尺寸压缩，没有压缩质量，故调用了之前写的ImageUtil（不这么写的话，在裁剪那里的时候返回就甭了）
                    try {

                        head = ImageUtil.getBitmapFormUri(DataActivity.this,outPutUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (head != null) {
                        Log.e(TAG, "onActivityResult11111: " + head);
                        /*** 上传服务器代码*/
                        //Base64的编码
                        String imgCode = ImageUtil.getBitmapStrBase64(head);
                        Log.e(TAG, "onActivityResult: " + imgCode);
                        //上传头像
                        upload(imgCode);

                        //setPicToView(head);// 保存在SD卡中
                        ivHead.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /*
     * 上传图片
     * 上传图片
     */
    public void upload(String imgCode) {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.IMGE , RequestMethod.POST);

        if (request != null) {
            request.add("avatardata", imgCode);

            // 添加到请求队列
            request(0, request, uploadobjectListener, true, true);
        }
    }

    private HttpListener<JSONObject> uploadobjectListener = new HttpListener<JSONObject>() {

        private String avatar;
        private HeadImage headImage;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: " + js);

                int code = js.getInt("code");
                headImage = JsonUtil.parseJsonToBean(js.toString(), HeadImage.class);
                if (code == 200) {
                    avatar = headImage.getAvatar();

                    SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                    edit.putString("avatar", avatar);

                    edit.commit();
                } else {
                    ToastUtil.showToast(getBaseContext(),"上传失败，请重新上传！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");
        }
    };

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        //获取uri并压缩  不然在乐视手机上会bug（剪裁的时候返回崩溃）
        File tempFiles = getTempFile();
        outPutUri = Uri.fromFile(tempFiles);
        intent.putExtra("output", outPutUri);

        startActivityForResult(intent, 3);
    }

    private File getTempFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/tm_crop_image.jpg");
        return file;
    }


    /**
     * 修改名字
     */
    private void alterName() {

        rlNickname.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.GONE);

        if (nickname != null && !nickname.equals("")) {
            etName.setText(nickname);
        }
        //光标至尾
        etName.setSelection(etName.getText().length());

        //自动获取光标
        etName.setFocusableInTouchMode(true);
        etName.requestFocus();
        //弹出软键盘
        InputMethodManager imm = (InputMethodManager) DataActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        tvNicknameOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                if (!name.equals("")){
                    tvName.setText(name);
                    getUpdate();
                    rlNickname.setVisibility(View.GONE);
                    tvName.setVisibility(View.VISIBLE);
                }else {
                    ToastUtil.showToast(getBaseContext(),"请输入昵称");
                }

            }
        });

    }

    //修改个人资料
    private void getUpdate() {

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.UPDATEPERSONAL, RequestMethod.POST);
        if (request != null) {
            request.add("uid", userid);
            request.add("nickname", name);
            request.add("sex",sex);
            request.add("birthday",birth);
//            request.add("birthday",)
            // 添加到请求队列
            request(0, request, nameobjectListener, true, true);
        }

    }
    private HttpListener<JSONObject> nameobjectListener = new HttpListener<JSONObject>() {


        @Override
        public void onSucceed(int what, Response<JSONObject> response) {


            try {
                JSONObject js = response.get();
                Log.e(TAG, "onSucceed123: " + js);


                int code = js.getInt("code");
                String message = js.getString("msg");

                if (code == 200) {

                    SharedPreferences.Editor edit = TMApplication.instance.sp.edit();
                    edit.putString("nickname", name);
                    edit.commit();

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "修改成功", Toast.LENGTH_SHORT);
                    //放在左上角。如果你想往右边移动，将第二个参数设为>0；往下移动，增大第三个参数；后两个参数都只得像素
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
                if (code == 0) {
                    ToastUtil.showToast(getBaseContext(),message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getBaseContext(),"访问网络失败，请检查您的网络！");
        }
    };


    //修改生日
    private void alterBirthday() {
        ChangeBirthdayPopwindow mChangeBirthdayPopwindow = new ChangeBirthdayPopwindow(DataActivity.this);
        mChangeBirthdayPopwindow.showAtLocation(rlBirthday, Gravity.BOTTOM, 0, 0);
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        mChangeBirthdayPopwindow.setBackgroundDrawable(new BitmapDrawable());
        mChangeBirthdayPopwindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        mChangeBirthdayPopwindow
                .setBirthdayListener(new ChangeBirthdayPopwindow.OnBirthListener() {

                    @Override
                    public void onClick(String year, String month, String day) {

                        if (Integer.parseInt(month) < 10){
                            month = "0"+month;
                        }
                        if (Integer.parseInt(day) < 10){
                            day = "0"+ day;
                        }
                        birth = year + "-" + month + "-" + day;
                        if (!birth.equals("")){
                            tvBirthday.setText(birth);
                            getUpdate();
                        }

                    }

                });

        mChangeBirthdayPopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    private void exit() {
        final AlertDialog dlg =
                new AlertDialog.Builder(this, R.style.MyDialogStyle).create();
        dlg.setCanceledOnTouchOutside(true);
        dlg.show();
        Window window = dlg.getWindow();
        //设置窗口的内容页面
        window.setContentView(R.layout.exit_dialog);
        //取消
        Button bt_cancel = (Button) window.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });
        //确定
        Button bt_ok = (Button) window.findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logout();

                dlg.cancel();
            }
        });
    }

    private void logout(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.LOGOUT);
        request(0, request, objectListener, true, true);
    }

    private HttpListener<JSONObject> objectListener = new HttpListener<JSONObject>() {

        private int code;
        private String msg;

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {


            JSONObject js = response.get();
            Log.e(TAG, "on3: " + js);
            try {

                code = js.getInt("code");
                msg = js.getString("msg");
                if (code == 200) {
                    startActivity(new Intent(DataActivity.this, HomeActivity.class));
                    DataActivity.this.finish();
                    TMApplication.instance.exit();
                    /**sp清空数据（账号密码）*/
                    TMApplication.instance.sp.edit().clear().commit();

                } else {
                    ToastUtil.showToast(getApplicationContext(),msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getApplicationContext(),"访问网络失败，请检查您的网络！");
        }
    };

}
