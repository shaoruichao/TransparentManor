package com.fat.bigfarm.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fat.bigfarm.R;
import com.fat.bigfarm.base.BaseFragment;
import com.fat.bigfarm.ui.activity.MyWarehouseActivity;
import com.fat.bigfarm.view.indicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的农庄
 */
public class MyfarmFragment extends BaseFragment {

    private static final String TAG = "MyfarmFragment";

    private View view;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myfarm, container, false);

        ButterKnife.bind(this, view);

        myRaiseFragment = new MyRaiseFragment();
        myEarningsFragment = new MyEarningsFragment();

        pagerItemList.add(myRaiseFragment);
        pagerItemList.add(myEarningsFragment);

        mAdapter = new MyPagerAdapter(getChildFragmentManager(),pagerItemList);
        pager.setAdapter(mAdapter);
        pager.setOffscreenPageLimit(2);//保留当前页面的数据

        // 进入时候自动锁定第一页
        pager.setCurrentItem(0);
        //设置关联的ViewPager
        indicator.setViewPager(pager);

        return view;
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
