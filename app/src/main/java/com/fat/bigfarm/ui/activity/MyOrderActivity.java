package com.fat.bigfarm.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fat.bigfarm.R;
import com.fat.bigfarm.base.BaseFragmentActivity;
import com.fat.bigfarm.ui.fragment.MyOrderAllFragment;
import com.fat.bigfarm.ui.fragment.MyOrderBeenshippedFragment;
import com.fat.bigfarm.ui.fragment.MyOrderObligationFragment;
import com.fat.bigfarm.ui.fragment.MyOrderSendgoodsFragment;
import com.fat.bigfarm.view.indicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的订单
public class MyOrderActivity extends BaseFragmentActivity {

    private static final String TAG = "MyOrderActivity";
    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.pager)
    ViewPager pager;

    private MyPagerAdapter mAdapter;
    private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
    public static String[] names = {"全部", "待付款", "待发货","已发货"};
    private MyOrderAllFragment myOrderAllFragment;
    private MyOrderObligationFragment myOrderObligationFragment;
    private MyOrderSendgoodsFragment myOrderSendgoodsFragment;
    private MyOrderBeenshippedFragment myOrderBeenshippedFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        myOrderAllFragment = new MyOrderAllFragment();
        myOrderObligationFragment = new MyOrderObligationFragment();
        myOrderSendgoodsFragment = new MyOrderSendgoodsFragment();
        myOrderBeenshippedFragment = new MyOrderBeenshippedFragment();

        pagerItemList.add(myOrderAllFragment);
        pagerItemList.add(myOrderObligationFragment);
        pagerItemList.add(myOrderSendgoodsFragment);
        pagerItemList.add(myOrderBeenshippedFragment);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager(),pagerItemList);
        pager.setAdapter(mAdapter);
        pager.setOffscreenPageLimit(3);//保留当前页面的数据

        // 进入时候自动锁定第一页
        pager.setCurrentItem(0);
        //设置关联的ViewPager
        indicator.setViewPager(pager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        int id = getIntent().getIntExtra("userloginflag", 0);

        if (id == 1 ) {
            pager.setCurrentItem(1);
        }else if (id == 2){
            pager.setCurrentItem(2);
        }else if (id == 3){
            pager.setCurrentItem(3);
        }

    }

    @OnClick(R.id.bt_back)
    public void onClick() {
        finish();
    }


    /**
     * @author Leo 适配器
     */
    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;


        public MyPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        /*
         * 返回头部的文件
         */
        public CharSequence getPageTitle(int position) {
            return names[position];
        }

        public int getCount() {
            return list.isEmpty() ? 0 : list.size();
        }

    }

}
