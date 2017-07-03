package com.fat.bigfarm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.HomeList;
import com.fat.bigfarm.ui.activity.DetailsActivity;
import com.fat.bigfarm.ui.activity.HomeMoreActivity;
import com.fat.bigfarm.ui.activity.HomeSmallMoreActivity;
import com.fat.bigfarm.utils.ToastUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页adapter
 */
public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "RvAdapter";

    /**普通商品的（normalHolder）的标题集合,调用者传入*/
    private ArrayList<HomeList.DataBean> normalGoodsTitls;
    /**头布局总数*/
    private int HEADER_CONUNT = 2;

    private Context mcontext;
    private HomeList.DataBean dataBean;
    private List<HomeList.DataBean.ListBean> dataBeanList;


    public RvAdapter(Context context, ArrayList<HomeList.DataBean> normalGoodsTitls) {
        this.mcontext = context;
        this.normalGoodsTitls = normalGoodsTitls;
    }

    private int HEADER0 = 0;
    private int HEADER1 = 1;

    private int NORMAL = 100;

    private View headView0;
    private View headView1;


    public void setHeadView0(View headView0) {
        this.headView0 = headView0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER0) {
            return new BannerHolder(headView0);
        }else if(viewType==HEADER1){
            return new GridMenuHolder(headView1);
        } else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.rv_item, parent,false);

            return new NormalHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        Log.e(TAG, "holder位置: "+holder.getLayoutPosition() );

        if (viewType == HEADER0 ) {
            return;
        }else if(viewType==HEADER1){
            return;
        }else if(viewType==NORMAL){
            NormalHolder normalHolder= (NormalHolder) holder;
//            final int realPostion=position - HEADER_CONUNT;//获取真正的位置

            if (position > 1 && position <= normalGoodsTitls.size()+1){
                if (mOnItemClickLitener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            int pos = realPostion;
                            mOnItemClickLitener.onItemClick(holder.itemView, position);
                        }
                    });
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                    {
                        @Override
                        public boolean onLongClick(View v)
                        {
//                            int pos = realPostion;
                            mOnItemClickLitener.onItemLongClick(holder.itemView, position);
                            return true;
                        }
                    });
                }

                Log.e(TAG, "位置: "+position );

                dataBean = normalGoodsTitls.get(position - 2);
                Log.e(TAG, "onBindViewHolder: "+dataBean.getTypename() );
                dataBeanList = dataBean.getList();
                //显示小图
                if (position-2>1){

                    normalHolder.listview_insets.setVisibility(View.VISIBLE);
                    normalHolder.listview.setVisibility(View.GONE);
                    if (dataBeanList.size()==1){
                        normalHolder.insets_rl1.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl2.setVisibility(View.GONE);
                        normalHolder.insets_rl3.setVisibility(View.GONE);
                        normalHolder.insets_rl4.setVisibility(View.GONE);
                        normalHolder.insets_rl5.setVisibility(View.GONE);
                        normalHolder.insets_rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();

                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }

                            }
                        });

                        String thumb = dataBeanList.get(0).getThumb();
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();

                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.insets_iv_thumb1);
                        normalHolder.insets_iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name = dataBeanList.get(0).getName();
                        normalHolder.insets_tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.insets_tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.insets_tv_price1.setText("¥"+price_point+"元/"+unit);

                    }
                    if (dataBeanList.size()==2){
                        normalHolder.insets_rl1.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl2.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl3.setVisibility(View.GONE);
                        normalHolder.insets_rl4.setVisibility(View.GONE);
                        normalHolder.insets_rl5.setVisibility(View.GONE);
                        normalHolder.insets_rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }

                            }
                        });

                        String thumb = dataBeanList.get(0).getThumb();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.insets_iv_thumb1);
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();

                        normalHolder.insets_iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name = dataBeanList.get(0).getName();
                        normalHolder.insets_tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.insets_tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.insets_tv_price1.setText("¥"+price_point+"元/"+unit);

                        String thumb1 = dataBeanList.get(1).getThumb();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.insets_iv_thumb2);
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();

                        normalHolder.insets_iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.insets_tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.insets_tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.insets_tv_price2.setText("¥"+price_point1+"元/"+unit1);

                    }
                    if (dataBeanList.size()==3){
                        normalHolder.insets_rl1.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl2.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl3.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl4.setVisibility(View.GONE);
                        normalHolder.insets_rl5.setVisibility(View.GONE);
                        normalHolder.insets_rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }

                            }
                        });

                        String thumb = dataBeanList.get(0).getThumb();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.insets_iv_thumb1);
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();

                        normalHolder.insets_iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name = dataBeanList.get(0).getName();
                        normalHolder.insets_tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.insets_tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.insets_tv_price1.setText("¥"+price_point+"元/"+unit);

                        String thumb1 = dataBeanList.get(1).getThumb();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.insets_iv_thumb2);
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.insets_tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.insets_tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.insets_tv_price2.setText("¥"+price_point1+"元/"+unit1);

                        String thumb2 = dataBeanList.get(2).getThumb();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.insets_iv_thumb3);
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.insets_tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.insets_tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.insets_tv_price3.setText("¥"+price_point2+"元/"+unit2);

                    }

                    if (dataBeanList.size()==4){
                        normalHolder.insets_rl1.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl2.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl3.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl4.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl5.setVisibility(View.GONE);
                        normalHolder.insets_rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });
                        //1
                        String thumb = dataBeanList.get(0).getThumb();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.insets_iv_thumb1);
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();
                        normalHolder.insets_iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.insets_tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.insets_tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.insets_tv_price1.setText("¥"+price_point+"元/"+unit);
                        //2
                        String thumb1 = dataBeanList.get(1).getThumb();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.insets_iv_thumb2);
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.insets_tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.insets_tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.insets_tv_price2.setText("¥"+price_point1+"元/"+unit1);
                        //3
                        String thumb2 = dataBeanList.get(2).getThumb();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.insets_iv_thumb3);
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.insets_tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.insets_tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.insets_tv_price3.setText("¥"+price_point2+"元/"+unit2);
                        //4
                        String thumb3 = dataBeanList.get(3).getThumb();
                        Glide.with(mcontext)
                                .load(thumb3)
                                .into(normalHolder.insets_iv_thumb4);
                        final String id3 = dataBeanList.get(3).getId();
                        final String sid3 = dataBeanList.get(3).getSid();
                        final String typename3 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id3);
                                intent.putExtra("sid",sid3);
                                intent.putExtra("typename",typename3);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name3 = dataBeanList.get(3).getName();
                        normalHolder.insets_tv_title4.setText(name3);

                        String des3 = dataBeanList.get(3).getDes();
                        normalHolder.insets_tv_des4.setText(des3);

                        String price3 = dataBeanList.get(3).getPrice();
                        String unit3 = dataBeanList.get(3).getUnit();
                        //保留两位小数点
                        String price_point3 = String .format("%.2f",Double.valueOf(price3));
                        normalHolder.insets_tv_price4.setText("¥"+price_point3+"元/"+unit3);
                    }

                    if (dataBeanList.size()==5){
                        normalHolder.insets_rl1.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl2.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl3.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl4.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl5.setVisibility(View.GONE);
                        normalHolder.insets_rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });
                        //1
                        String thumb = dataBeanList.get(0).getThumb();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.insets_iv_thumb1);
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();
                        normalHolder.insets_iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.insets_tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.insets_tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.insets_tv_price1.setText("¥"+price_point+"元/"+unit);
                        //2
                        String thumb1 = dataBeanList.get(1).getThumb();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.insets_iv_thumb2);
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.insets_tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.insets_tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.insets_tv_price2.setText("¥"+price_point1+"元/"+unit1);
                        //3
                        String thumb2 = dataBeanList.get(2).getThumb();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.insets_iv_thumb3);
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.insets_tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.insets_tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.insets_tv_price3.setText("¥"+price_point2+"元/"+unit2);
                        //4
                        String thumb3 = dataBeanList.get(3).getThumb();
                        Glide.with(mcontext)
                                .load(thumb3)
                                .into(normalHolder.insets_iv_thumb4);
                        final String id3 = dataBeanList.get(3).getId();
                        final String sid3 = dataBeanList.get(3).getSid();
                        final String typename3 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id3);
                                intent.putExtra("sid",sid3);
                                intent.putExtra("typename",typename3);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name3 = dataBeanList.get(3).getName();
                        normalHolder.insets_tv_title4.setText(name3);

                        String des3 = dataBeanList.get(3).getDes();
                        normalHolder.insets_tv_des4.setText(des3);

                        String price3 = dataBeanList.get(3).getPrice();
                        String unit3 = dataBeanList.get(3).getUnit();
                        //保留两位小数点
                        String price_point3 = String .format("%.2f",Double.valueOf(price3));
                        normalHolder.insets_tv_price4.setText("¥"+price_point3+"元/"+unit3);
                        //5
                        String thumb4 = dataBeanList.get(4).getThumb();
                        Glide.with(mcontext)
                                .load(thumb4)
                                .into(normalHolder.insets_iv_thumb5);
                        final String id4 = dataBeanList.get(4).getId();
                        final String sid4 = dataBeanList.get(4).getSid();
                        final String typename4 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id4);
                                intent.putExtra("sid",sid4);
                                intent.putExtra("typename",typename4);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name4 = dataBeanList.get(4).getName();
                        normalHolder.insets_tv_title5.setText(name4);

                        String des4 = dataBeanList.get(4).getDes();
                        normalHolder.insets_tv_des5.setText(des4);

                        String price4 = dataBeanList.get(4).getPrice();
                        String unit4 = dataBeanList.get(4).getUnit();
                        //保留两位小数点
                        String price_point4 = String .format("%.2f",Double.valueOf(price4));
                        normalHolder.insets_tv_price5.setText("¥"+price_point4+"元/"+unit4);
                    }

                    if (dataBeanList.size()==6){
                        normalHolder.insets_rl1.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl2.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl3.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl4.setVisibility(View.VISIBLE);
                        normalHolder.insets_rl5.setVisibility(View.GONE);
                        normalHolder.insets_rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });
                        //1
                        String thumb = dataBeanList.get(0).getThumb();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.insets_iv_thumb1);
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();
                        normalHolder.insets_iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.insets_tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.insets_tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.insets_tv_price1.setText("¥"+price_point+"元/"+unit);
                        //2
                        String thumb1 = dataBeanList.get(1).getThumb();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.insets_iv_thumb2);
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.insets_tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.insets_tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.insets_tv_price2.setText("¥"+price_point1+"元/"+unit1);
                        //3
                        String thumb2 = dataBeanList.get(2).getThumb();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.insets_iv_thumb3);
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.insets_tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.insets_tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.insets_tv_price3.setText("¥"+price_point2+"元/"+unit2);
                        //4
                        String thumb3 = dataBeanList.get(3).getThumb();
                        Glide.with(mcontext)
                                .load(thumb3)
                                .into(normalHolder.insets_iv_thumb4);
                        final String id3 = dataBeanList.get(3).getId();
                        final String sid3 = dataBeanList.get(3).getSid();
                        final String typename3 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id3);
                                intent.putExtra("sid",sid3);
                                intent.putExtra("typename",typename3);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name3 = dataBeanList.get(3).getName();
                        normalHolder.insets_tv_title4.setText(name3);

                        String des3 = dataBeanList.get(3).getDes();
                        normalHolder.insets_tv_des4.setText(des3);

                        String price3 = dataBeanList.get(3).getPrice();
                        String unit3 = dataBeanList.get(3).getUnit();
                        //保留两位小数点
                        String price_point3 = String .format("%.2f",Double.valueOf(price3));
                        normalHolder.insets_tv_price4.setText("¥"+price_point3+"元/"+unit3);
                        //5
                        String thumb4 = dataBeanList.get(4).getThumb();
                        Glide.with(mcontext)
                                .load(thumb4)
                                .into(normalHolder.insets_iv_thumb5);
                        final String id4 = dataBeanList.get(4).getId();
                        final String sid4 = dataBeanList.get(4).getSid();
                        final String typename4 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id4);
                                intent.putExtra("sid",sid4);
                                intent.putExtra("typename",typename4);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name4 = dataBeanList.get(4).getName();
                        normalHolder.insets_tv_title5.setText(name4);

                        String des4 = dataBeanList.get(4).getDes();
                        normalHolder.insets_tv_des5.setText(des4);

                        String price4 = dataBeanList.get(4).getPrice();
                        String unit4 = dataBeanList.get(4).getUnit();
                        //保留两位小数点
                        String price_point4 = String .format("%.2f",Double.valueOf(price4));
                        normalHolder.insets_tv_price5.setText("¥"+price_point4+"元/"+unit4);
                        //6
                        String thumb5 = dataBeanList.get(5).getThumb();
                        Glide.with(mcontext)
                                .load(thumb5)
                                .into(normalHolder.insets_iv_thumb6);
                        final String id5 = dataBeanList.get(5).getId();
                        final String sid5 = dataBeanList.get(5).getSid();
                        final String typename5 = dataBean.getTypename();
                        normalHolder.insets_iv_thumb6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id5);
                                intent.putExtra("sid",sid5);
                                intent.putExtra("typename",typename5);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name5 = dataBeanList.get(5).getName();
                        normalHolder.insets_tv_title6.setText(name5);

                        String des5 = dataBeanList.get(5).getDes();
                        normalHolder.insets_tv_des6.setText(des5);

                        String price5 = dataBeanList.get(5).getPrice();
                        String unit5 = dataBeanList.get(5).getUnit();
                        //保留两位小数点
                        String price_point5 = String .format("%.2f",Double.valueOf(price5));
                        normalHolder.insets_tv_price6.setText("¥"+price_point5+"元/"+unit5);
                    }
                }else {

                    normalHolder.listview_insets.setVisibility(View.GONE);
                    normalHolder.listview.setVisibility(View.VISIBLE);

                    if (dataBeanList.size()==1){
                        normalHolder.rl1.setVisibility(View.VISIBLE);
                        normalHolder.rl2.setVisibility(View.GONE);
                        normalHolder.rl3.setVisibility(View.GONE);
                        normalHolder.rl4.setVisibility(View.GONE);
                        normalHolder.rl5.setVisibility(View.GONE);
                        normalHolder.rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });

                        String thumb = dataBeanList.get(0).getThumb();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.iv_thumb1);
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();
                        normalHolder.iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name = dataBeanList.get(0).getName();
                        normalHolder.tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.tv_price1.setText("¥"+price_point+"元/"+unit);

                    }
                    if (dataBeanList.size()==2){
                        normalHolder.rl1.setVisibility(View.VISIBLE);
                        normalHolder.rl2.setVisibility(View.VISIBLE);
                        normalHolder.rl3.setVisibility(View.GONE);
                        normalHolder.rl4.setVisibility(View.GONE);
                        normalHolder.rl5.setVisibility(View.GONE);
                        normalHolder.rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });

                        String thumb = dataBeanList.get(0).getThumb();
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
//                        final String typename = dataBeanList.get(0).getName();
                        final String typename = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.iv_thumb1);
                        normalHolder.iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.tv_price1.setText("¥"+price_point+"元/"+unit);

                        String thumb1 = dataBeanList.get(1).getThumb();
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.iv_thumb2);
                        normalHolder.iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.tv_price2.setText("¥"+price_point1+"元/"+unit1);

                    }
                    if (dataBeanList.size()==3){
                        normalHolder.rl1.setVisibility(View.VISIBLE);
                        normalHolder.rl2.setVisibility(View.VISIBLE);
                        normalHolder.rl3.setVisibility(View.VISIBLE);
                        normalHolder.rl4.setVisibility(View.GONE);
                        normalHolder.rl5.setVisibility(View.GONE);
                        normalHolder.rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });

                        String thumb = dataBeanList.get(0).getThumb();
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.iv_thumb1);
                        normalHolder.iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.tv_price1.setText("¥"+price_point+"元/"+unit);

                        String thumb1 = dataBeanList.get(1).getThumb();
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.iv_thumb2);
                        normalHolder.iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.tv_price2.setText("¥"+price_point1+"元/"+unit1);

                        String thumb2 = dataBeanList.get(2).getThumb();
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.iv_thumb3);
                        normalHolder.iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.tv_price3.setText("¥"+price_point2+"元/"+unit2);

                    }

                    if (dataBeanList.size()==4){
                        normalHolder.rl1.setVisibility(View.VISIBLE);
                        normalHolder.rl2.setVisibility(View.VISIBLE);
                        normalHolder.rl3.setVisibility(View.VISIBLE);
                        normalHolder.rl4.setVisibility(View.VISIBLE);
                        normalHolder.rl5.setVisibility(View.GONE);
                        normalHolder.rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });
                        //1
                        String thumb = dataBeanList.get(0).getThumb();
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typaname = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.iv_thumb1);
                        normalHolder.iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typaname);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.tv_price1.setText("¥"+price_point+"元/"+unit);
                        //2
                        String thumb1 = dataBeanList.get(1).getThumb();
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.iv_thumb2);
                        normalHolder.iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.tv_price2.setText("¥"+price_point1+"元/"+unit1);
                        //3
                        String thumb2 = dataBeanList.get(2).getThumb();
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.iv_thumb3);
                        normalHolder.iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.tv_price3.setText("¥"+price_point2+"元/"+unit2);
                        //4
                        String thumb3 = dataBeanList.get(3).getThumb();
                        final String id3 = dataBeanList.get(3).getId();
                        final String sid3 = dataBeanList.get(3).getSid();
                        final String typename3 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb3)
                                .into(normalHolder.iv_thumb4);
                        normalHolder.iv_thumb4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id3);
                                intent.putExtra("sid",sid3);
                                intent.putExtra("typename",typename3);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name3 = dataBeanList.get(3).getName();
                        normalHolder.tv_title4.setText(name3);

                        String des3 = dataBeanList.get(3).getDes();
                        normalHolder.tv_des4.setText(des3);

                        String price3 = dataBeanList.get(3).getPrice();
                        String unit3 = dataBeanList.get(3).getUnit();
                        //保留两位小数点
                        String price_point3 = String .format("%.2f",Double.valueOf(price3));
                        normalHolder.tv_price4.setText("¥"+price_point3+"元/"+unit3);
                    }

                    if (dataBeanList.size()==5){
                        normalHolder.rl1.setVisibility(View.VISIBLE);
                        normalHolder.rl2.setVisibility(View.VISIBLE);
                        normalHolder.rl3.setVisibility(View.VISIBLE);
                        normalHolder.rl4.setVisibility(View.VISIBLE);
                        normalHolder.rl5.setVisibility(View.VISIBLE);
                        normalHolder.rl6.setVisibility(View.GONE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });
                        //1
                        String thumb = dataBeanList.get(0).getThumb();
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.iv_thumb1);
                        normalHolder.iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.tv_price1.setText("¥"+price_point+"元/"+unit);
                        //2
                        String thumb1 = dataBeanList.get(1).getThumb();
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.iv_thumb2);
                        normalHolder.iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.tv_price2.setText("¥"+price_point1+"元/"+unit1);
                        //3
                        String thumb2 = dataBeanList.get(2).getThumb();
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.iv_thumb3);
                        normalHolder.iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.tv_price3.setText("¥"+price_point2+"元/"+unit2);
                        //4
                        String thumb3 = dataBeanList.get(3).getThumb();
                        final String id3 = dataBeanList.get(3).getId();
                        final String sid3 = dataBeanList.get(3).getSid();
                        final String typename3 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb3)
                                .into(normalHolder.iv_thumb4);
                        normalHolder.iv_thumb4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id3);
                                intent.putExtra("sid",sid3);
                                intent.putExtra("typename",typename3);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name3 = dataBeanList.get(3).getName();
                        normalHolder.tv_title4.setText(name3);

                        String des3 = dataBeanList.get(3).getDes();
                        normalHolder.tv_des4.setText(des3);

                        String price3 = dataBeanList.get(3).getPrice();
                        String unit3 = dataBeanList.get(3).getUnit();
                        //保留两位小数点
                        String price_point3 = String .format("%.2f",Double.valueOf(price3));
                        normalHolder.tv_price4.setText("¥"+price_point3+"元/"+unit3);
                        //5
                        String thumb4 = dataBeanList.get(4).getThumb();
                        final String id4 = dataBeanList.get(4).getId();
                        final String sid4 = dataBeanList.get(4).getSid();
                        final String typename4 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb4)
                                .into(normalHolder.iv_thumb5);
                        normalHolder.iv_thumb5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id4);
                                intent.putExtra("sid",sid4);
                                intent.putExtra("typename",typename4);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name4 = dataBeanList.get(4).getName();
                        normalHolder.tv_title5.setText(name4);

                        String des4 = dataBeanList.get(4).getDes();
                        normalHolder.tv_des5.setText(des4);

                        String price4 = dataBeanList.get(4).getPrice();
                        String unit4 = dataBeanList.get(4).getUnit();
                        //保留两位小数点
                        String price_point4 = String .format("%.2f",Double.valueOf(price4));
                        normalHolder.tv_price5.setText("¥"+price_point4+"元/"+unit4);
                    }

                    if (dataBeanList.size()==6){
                        normalHolder.rl1.setVisibility(View.VISIBLE);
                        normalHolder.rl2.setVisibility(View.VISIBLE);
                        normalHolder.rl3.setVisibility(View.VISIBLE);
                        normalHolder.rl4.setVisibility(View.VISIBLE);
                        normalHolder.rl5.setVisibility(View.VISIBLE);
                        normalHolder.rl6.setVisibility(View.VISIBLE);

                        normalHolder.tv_tab_title.setText(dataBean.getTypename());
                        Glide.with(mcontext)
                                .load(dataBean.getIcon())
                                .into(normalHolder.iv_tab);
                        final String typeid = dataBean.getTypeid();
                        final String typestatus = dataBean.getTypestatus();
                        normalHolder.bt_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ToastUtil.showToast(mcontext,typeid);
                                if (typestatus.equals("1")){
                                    Intent intent = new Intent(mcontext, HomeMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }else {
                                    Intent intent = new Intent(mcontext, HomeSmallMoreActivity.class);
                                    intent.putExtra("id",typeid);
                                    mcontext.startActivity(intent);
                                }
                            }
                        });

                        //1
                        String thumb = dataBeanList.get(0).getThumb();
                        final String id = dataBeanList.get(0).getId();
                        final String sid = dataBeanList.get(0).getSid();
                        final String typename = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb)
                                .into(normalHolder.iv_thumb1);
                        normalHolder.iv_thumb1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id);
                                intent.putExtra("sid",sid);
                                intent.putExtra("typename",typename);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name = dataBeanList.get(0).getName();
                        normalHolder.tv_title1.setText(name);

                        String des = dataBeanList.get(0).getDes();
                        normalHolder.tv_des1.setText(des);

                        String price = dataBeanList.get(0).getPrice();
                        String unit = dataBeanList.get(0).getUnit();
                        //保留两位小数点
                        String price_point = String .format("%.2f",Double.valueOf(price));
                        normalHolder.tv_price1.setText("¥"+price_point+"元/"+unit);
                        //2
                        String thumb1 = dataBeanList.get(1).getThumb();
                        final String id1 = dataBeanList.get(1).getId();
                        final String sid1 = dataBeanList.get(1).getSid();
                        final String typename1 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb1)
                                .into(normalHolder.iv_thumb2);
                        normalHolder.iv_thumb2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id1);
                                intent.putExtra("sid",sid1);
                                intent.putExtra("typename",typename1);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });
                        String name1 = dataBeanList.get(1).getName();
                        normalHolder.tv_title2.setText(name1);

                        String des1 = dataBeanList.get(1).getDes();
                        normalHolder.tv_des2.setText(des1);

                        String price1 = dataBeanList.get(1).getPrice();
                        String unit1 = dataBeanList.get(1).getUnit();
                        //保留两位小数点
                        String price_point1 = String .format("%.2f",Double.valueOf(price1));
                        normalHolder.tv_price2.setText("¥"+price_point1+"元/"+unit1);
                        //3
                        String thumb2 = dataBeanList.get(2).getThumb();
                        final String id2 = dataBeanList.get(2).getId();
                        final String sid2 = dataBeanList.get(2).getSid();
                        final String typename2 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb2)
                                .into(normalHolder.iv_thumb3);
                        normalHolder.iv_thumb3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id2);
                                intent.putExtra("sid",sid2);
                                intent.putExtra("typename",typename2);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name2 = dataBeanList.get(2).getName();
                        normalHolder.tv_title3.setText(name2);

                        String des2 = dataBeanList.get(2).getDes();
                        normalHolder.tv_des3.setText(des2);

                        String price2 = dataBeanList.get(2).getPrice();
                        String unit2 = dataBeanList.get(2).getUnit();
                        //保留两位小数点
                        String price_point2 = String .format("%.2f",Double.valueOf(price2));
                        normalHolder.tv_price3.setText("¥"+price_point2+"元/"+unit2);
                        //4
                        String thumb3 = dataBeanList.get(3).getThumb();
                        final String id3 = dataBeanList.get(3).getId();
                        final String sid3 = dataBeanList.get(3).getSid();
                        final String typename3 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb3)
                                .into(normalHolder.iv_thumb4);
                        normalHolder.iv_thumb4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id3);
                                intent.putExtra("sid",sid3);
                                intent.putExtra("typename",typename3);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name3 = dataBeanList.get(3).getName();
                        normalHolder.tv_title4.setText(name3);

                        String des3 = dataBeanList.get(3).getDes();
                        normalHolder.tv_des4.setText(des3);

                        String price3 = dataBeanList.get(3).getPrice();
                        String unit3 = dataBeanList.get(3).getUnit();
                        //保留两位小数点
                        String price_point3 = String .format("%.2f",Double.valueOf(price3));
                        normalHolder.tv_price4.setText("¥"+price_point3+"元/"+unit3);
                        //5
                        String thumb4 = dataBeanList.get(4).getThumb();
                        final String id4 = dataBeanList.get(4).getId();
                        final String sid4 = dataBeanList.get(4).getSid();
                        final String typename4 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb4)
                                .into(normalHolder.iv_thumb5);
                        normalHolder.iv_thumb5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id4);
                                intent.putExtra("sid",sid4);
                                intent.putExtra("typename",typename4);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name4 = dataBeanList.get(4).getName();
                        normalHolder.tv_title5.setText(name4);

                        String des4 = dataBeanList.get(4).getDes();
                        normalHolder.tv_des5.setText(des4);

                        String price4 = dataBeanList.get(4).getPrice();
                        String unit4 = dataBeanList.get(4).getUnit();
                        //保留两位小数点
                        String price_point4 = String .format("%.2f",Double.valueOf(price4));
                        normalHolder.tv_price5.setText("¥"+price_point4+"元/"+unit4);
                        //6
                        String thumb5 = dataBeanList.get(5).getThumb();
                        final String id5 = dataBeanList.get(5).getId();
                        final String sid5 = dataBeanList.get(5).getSid();
                        final String typename5 = dataBean.getTypename();
                        Glide.with(mcontext)
                                .load(thumb5)
                                .into(normalHolder.iv_thumb6);
                        normalHolder.iv_thumb6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("id",id5);
                                intent.putExtra("sid",sid5);
                                intent.putExtra("typename",typename5);
                                intent.setClass(mcontext, DetailsActivity.class);
                                mcontext.startActivity(intent);
                            }
                        });

                        String name5 = dataBeanList.get(5).getName();
                        normalHolder.tv_title6.setText(name5);

                        String des5 = dataBeanList.get(5).getDes();
                        normalHolder.tv_des6.setText(des5);

                        String price5 = dataBeanList.get(5).getPrice();
                        String unit5 = dataBeanList.get(5).getUnit();
                        //保留两位小数点
                        String price_point5 = String .format("%.2f",Double.valueOf(price5));
                        normalHolder.tv_price6.setText("¥"+price_point5+"元/"+unit5);
                    }
                }


            }


        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headView0 != null) {
            return HEADER0;
        }else if(position==1&&headView1!=null){
            return HEADER1;
        }
        else {
            return NORMAL;
        }
    }

    //有7条普通数据，但是要加上Header的总数
    @Override
    public int getItemCount() {
        return normalGoodsTitls.size()+HEADER_CONUNT;
    }

    /**
     * 添加顶部banner
     */
    public void  addHeadView0(View view) {
        this.headView0 = view;
    }
    /**添加10个子菜单*/
    public void addHeaderView1(View v) {
        this.headView1 = v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){


        }
    }

    //顶部banner
    class BannerHolder extends RecyclerView.ViewHolder {

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //10个子菜单
    class GridMenuHolder extends RecyclerView.ViewHolder{

        public GridMenuHolder(View itemView) {
            super(itemView);
        }
    }

    //普通的Holder
    class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listview)
        HorizontalScrollView listview;
        @BindView(R.id.listview_insets)
        HorizontalScrollView listview_insets;
        @BindView(R.id.rl_tab)
        RelativeLayout rl_tab;
        @BindView(R.id.iv_tab)
        ImageView iv_tab;
        @BindView(R.id.tv_tab_title)
        TextView tv_tab_title;
        @BindView(R.id.bt_more)
        Button bt_more;

        @BindView(R.id.rl1)
        RelativeLayout rl1;
        @BindView(R.id.iv_thumb1)
        ImageView iv_thumb1;
        @BindView(R.id.tv_title1)
        TextView tv_title1;
        @BindView(R.id.tv_des1)
        TextView tv_des1;
        @BindView(R.id.tv_price1)
        TextView tv_price1;

        @BindView(R.id.rl2)
        RelativeLayout rl2;
        @BindView(R.id.iv_thumb2)
        ImageView iv_thumb2;
        @BindView(R.id.tv_title2)
        TextView tv_title2;
        @BindView(R.id.tv_des2)
        TextView tv_des2;
        @BindView(R.id.tv_price2)
        TextView tv_price2;

        @BindView(R.id.rl3)
        RelativeLayout rl3;
        @BindView(R.id.iv_thumb3)
        ImageView iv_thumb3;
        @BindView(R.id.tv_title3)
        TextView tv_title3;
        @BindView(R.id.tv_des3)
        TextView tv_des3;
        @BindView(R.id.tv_price3)
        TextView tv_price3;

        @BindView(R.id.rl4)
        RelativeLayout rl4;
        @BindView(R.id.iv_thumb4)
        ImageView iv_thumb4;
        @BindView(R.id.tv_title4)
        TextView tv_title4;
        @BindView(R.id.tv_des4)
        TextView tv_des4;
        @BindView(R.id.tv_price4)
        TextView tv_price4;

        @BindView(R.id.rl5)
        RelativeLayout rl5;
        @BindView(R.id.iv_thumb5)
        ImageView iv_thumb5;
        @BindView(R.id.tv_title5)
        TextView tv_title5;
        @BindView(R.id.tv_des5)
        TextView tv_des5;
        @BindView(R.id.tv_price5)
        TextView tv_price5;

        @BindView(R.id.rl6)
        RelativeLayout rl6;
        @BindView(R.id.iv_thumb6)
        ImageView iv_thumb6;
        @BindView(R.id.tv_title6)
        TextView tv_title6;
        @BindView(R.id.tv_des6)
        TextView tv_des6;
        @BindView(R.id.tv_price6)
        TextView tv_price6;

        @BindView(R.id.insets_rl1)
        RelativeLayout insets_rl1;
        @BindView(R.id.insets_iv_thumb1)
        ImageView insets_iv_thumb1;
        @BindView(R.id.insets_tv_title1)
        TextView insets_tv_title1;
        @BindView(R.id.insets_tv_des1)
        TextView insets_tv_des1;
        @BindView(R.id.insets_tv_price1)
        TextView insets_tv_price1;

        @BindView(R.id.insets_rl2)
        RelativeLayout insets_rl2;
        @BindView(R.id.insets_iv_thumb2)
        ImageView insets_iv_thumb2;
        @BindView(R.id.insets_tv_title2)
        TextView insets_tv_title2;
        @BindView(R.id.insets_tv_des2)
        TextView insets_tv_des2;
        @BindView(R.id.insets_tv_price2)
        TextView insets_tv_price2;

        @BindView(R.id.insets_rl3)
        RelativeLayout insets_rl3;
        @BindView(R.id.insets_iv_thumb3)
        ImageView insets_iv_thumb3;
        @BindView(R.id.insets_tv_title3)
        TextView insets_tv_title3;
        @BindView(R.id.insets_tv_des3)
        TextView insets_tv_des3;
        @BindView(R.id.insets_tv_price3)
        TextView insets_tv_price3;

        @BindView(R.id.insets_rl4)
        RelativeLayout insets_rl4;
        @BindView(R.id.insets_iv_thumb4)
        ImageView insets_iv_thumb4;
        @BindView(R.id.insets_tv_title4)
        TextView insets_tv_title4;
        @BindView(R.id.insets_tv_des4)
        TextView insets_tv_des4;
        @BindView(R.id.insets_tv_price4)
        TextView insets_tv_price4;

        @BindView(R.id.insets_rl5)
        RelativeLayout insets_rl5;
        @BindView(R.id.insets_iv_thumb5)
        ImageView insets_iv_thumb5;
        @BindView(R.id.insets_tv_title5)
        TextView insets_tv_title5;
        @BindView(R.id.insets_tv_des5)
        TextView insets_tv_des5;
        @BindView(R.id.insets_tv_price5)
        TextView insets_tv_price5;

        @BindView(R.id.insets_rl6)
        RelativeLayout insets_rl6;
        @BindView(R.id.insets_iv_thumb6)
        ImageView insets_iv_thumb6;
        @BindView(R.id.insets_tv_title6)
        TextView insets_tv_title6;
        @BindView(R.id.insets_tv_des6)
        TextView insets_tv_des6;
        @BindView(R.id.insets_tv_price6)
        TextView insets_tv_price6;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View v, int postion);
        void onItemLongClick(View v, int postion);
    }
    /**自定义条目点击监听*/
    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
