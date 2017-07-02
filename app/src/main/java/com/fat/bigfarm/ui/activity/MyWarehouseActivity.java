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
import com.fat.bigfarm.ui.fragment.MyEarningsFragment;
import com.fat.bigfarm.ui.fragment.MyRaiseFragment;
import com.fat.bigfarm.view.indicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的仓库
 */
public class MyWarehouseActivity extends BaseFragmentActivity {

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
    public static String[] names = {"我的代养", "我的收益"};
    private MyRaiseFragment myRaiseFragment;
    private MyEarningsFragment myEarningsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_warehouse);
        ButterKnife.bind(this);

        myRaiseFragment = new MyRaiseFragment();
        myEarningsFragment = new MyEarningsFragment();

        pagerItemList.add(myRaiseFragment);
        pagerItemList.add(myEarningsFragment);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager(),pagerItemList);
        pager.setAdapter(mAdapter);
        pager.setOffscreenPageLimit(2);//保留当前页面的数据

        // 进入时候自动锁定第一页
        pager.setCurrentItem(0);
        //设置关联的ViewPager
        indicator.setViewPager(pager);
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
