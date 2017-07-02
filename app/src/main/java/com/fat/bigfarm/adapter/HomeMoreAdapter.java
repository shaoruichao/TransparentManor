package com.fat.bigfarm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fat.bigfarm.R;
import com.fat.bigfarm.entry.HomeMore;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**Rv适配器，包含多个头布局
 * Created by yusheng on 2016/11/28.
 */
public class HomeMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    /**普通商品的（normalHolder）的标题集合,调用者传入*/
    private List<HomeMore.ListBean> normalGoodsTitls;
    /**头布局总数*/
    private int HEADER_CONUNT = 1;

    private Context mcontext;


    public HomeMoreAdapter(Context context, List<HomeMore.ListBean> normalGoodsTitls) {
        this.mcontext = context;
        this.normalGoodsTitls = normalGoodsTitls;
    }

//    private int HEADER0 = 0;
    private int HEADER1 = 1;

    private int NORMAL = 100;

//    private View headView0;
    private View headView1;


//    public void setHeadView0(View headView0) {
//        this.headView0 = headView0;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == HEADER0) {
//            return new BannerHolder(headView0);
//        }else
            if(viewType==HEADER1){
            return new GridMenuHolder(headView1);
        } else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.homemore, parent,false);

            return new NormalHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        Log.d("alan","holder位置---》"+holder.getLayoutPosition());

//        if (viewType == HEADER0 ) {
//            return;
//        }else
            if(viewType==HEADER1){
            return;
        }else if(viewType==NORMAL){
            NormalHolder normalHolder= (NormalHolder) holder;
//            final int realPostion=position - HEADER_CONUNT;//获取真正的位置

            if (position > 0 && position <= normalGoodsTitls.size()){
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

                Log.d("alan","位置-->"+position);
                if (position-1 == 0){
                    normalHolder.tv_tab_title.setVisibility(View.VISIBLE);
                    normalHolder.tv_tab_title.setText("代养专栏");
                }


                String img = normalGoodsTitls.get(position-1).getThumb();
                if (!img.equals("")) {
                    Glide.with(mcontext)
                            .load(img)
                            .into(normalHolder.iv_thumb);
                }

                String name = normalGoodsTitls.get(position - 1).getName();
                normalHolder.tv_title.setText(name);

                String des = normalGoodsTitls.get(position - 1).getDes();
                normalHolder.tv_des.setText(des);

                String price = normalGoodsTitls.get(position-1).getPrice();
                String unit = normalGoodsTitls.get(position - 1).getUnit();
                normalHolder.tv_price.setText("¥"+price+"元/"+unit);

            }


        }

    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0 && headView0 != null) {
//            return HEADER0;
//        }else
            if(position==0&&headView1!=null){
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

//    /**
//     * 添加顶部banner
//     */
//    public void  addHeadView0(View view) {
//        this.headView0 = view;
//    }
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
        @BindView(R.id.tv_tab_title)
        TextView tv_tab_title;
        @BindView(R.id.iv_thumb)
        ImageView iv_thumb;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_des)
        TextView tv_des;
        @BindView(R.id.tv_price)
        TextView tv_price;
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
