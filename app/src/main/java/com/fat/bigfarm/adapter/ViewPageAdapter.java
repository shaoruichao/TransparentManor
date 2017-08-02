package com.fat.bigfarm.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by src on 2017/7/5.
 */

public class ViewPageAdapter extends PagerAdapter{

    //数据
    List<String> mDatas;
    List<String> mDatasid;
    Context mContext;

    public ViewPageAdapter(Context context,List<String> imageViews,List<String> bigPicsid) {
        mDatas = imageViews;
        mContext = context;
        mDatasid = bigPicsid;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        final int i = position % mDatas.size();//为了防止角标越界
            //届时 就不用再初始化图片了，直接把imageViews替换成服务器获取的图片数组thumb就行了例如下面
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(mDatas.get(i)).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, mDatasid.get(i) + "", Toast.LENGTH_LONG).show();

                }
            });
            container.addView(imageView);
            return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
