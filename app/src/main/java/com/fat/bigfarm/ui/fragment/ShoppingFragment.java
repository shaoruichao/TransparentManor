package com.fat.bigfarm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fat.bigfarm.R;
import com.fat.bigfarm.app.TMApplication;
import com.fat.bigfarm.utils.ToastUtil;
import com.fat.bigfarm.adapter.ShopcartExpandableListViewAdapter;
import com.fat.bigfarm.app.AllUrl;
import com.fat.bigfarm.base.BaseFragment;
import com.fat.bigfarm.bean.GroupInfo;
import com.fat.bigfarm.bean.ProductInfo;
import com.fat.bigfarm.entry.AllInfo;
import com.fat.bigfarm.nohttp.HttpListener;
import com.fat.bigfarm.ui.activity.SuerOrderActivity;
import com.fat.bigfarm.utils.JsonUtil;
import com.fat.bigfarm.view.widget.SuperExpandableListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
public class ShoppingFragment extends BaseFragment implements ShopcartExpandableListViewAdapter.CheckInterface, View.OnClickListener, ShopcartExpandableListViewAdapter.ModifyCountInterface{

    private static final String TAG = "ShoppingFragment";


    private View view;
    private SwipeRefreshLayout sw_layout;
    private SuperExpandableListView exListView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;
    private LinearLayout ll_sureOrder;
    private FrameLayout fl_nomessage;

    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    private AllInfo allInfo;

    private ShopcartExpandableListViewAdapter selva;
    private List<GroupInfo> groups = new ArrayList<GroupInfo>();// 组元素数据列表
    private Map<String, List<ProductInfo>> children = new HashMap<>();// 子元素数据列表
    private String status;

    private String result;
    private String userid;
    private Button bt_delete;
    private TextView tv_head_delete;
    private boolean mark = true;
    private boolean check = true;
    private TextView tv_check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, container, false);

        initView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        status = TMApplication.instance.sp.getString("status", "");
        userid = TMApplication.instance.sp.getString("userid", "");
        Log.e(TAG, "onResume: "+userid );

        tv_total_price.setText("0.00");
        tv_go_to_pay.setText("下单");
        cb_check_all.setChecked(false);

        groups.clear();
        children.clear();
        PostShoppingcart();
    }

    private void initView() {
        exListView = (SuperExpandableListView) view.findViewById(R.id.exListView);
        cb_check_all = (CheckBox) view.findViewById(R.id.all_chekbox);
        tv_check = (TextView)view.findViewById(R.id.tv_check);
        tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
        tv_delete = (TextView) view.findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) view.findViewById(R.id.tv_go_to_pay);
        sw_layout = (SwipeRefreshLayout)view.findViewById(R.id.sw_layout);
        ll_sureOrder = (LinearLayout) view.findViewById(R.id.ll_sureOrder);
        fl_nomessage = (FrameLayout)view.findViewById(R.id.fl_nomessage);
        tv_head_delete = (TextView) view.findViewById(R.id.tv_head_delete);
        bt_delete = (Button) view.findViewById(R.id.bt_delete);

        //设置刷新的颜色
        sw_layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        sw_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //刷新的时候

//                hud = KProgressHUD.create(getActivity())
//                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
//                hud.show();

//                children = new HashMap<>();
                groups.clear();
                children.clear();
                PostShoppingcart();

                //停止刷新
                sw_layout.setRefreshing(false);
            }
        });
    }

    //购物车列表
    private void PostShoppingcart() {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SHOPPINGCAT, RequestMethod.POST);
        if (request != null) {
            request.add("dosubmit", 1);
            request.add("uid",userid);//用户id

            // 添加到请求队列
            request(0, request, cartListener, true, true);
        }
    }

    private HttpListener<JSONObject> cartListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "cartListener: " + js);
                int code = js.getInt("code");
                if (code == 200){
                    allInfo = JsonUtil.parseJsonToBean(js.toString(), AllInfo.class);
                    List<AllInfo.DataBean> data = allInfo.getData();
                    if (data.size()==0){

                        sw_layout.setVisibility(View.GONE);
                        ll_sureOrder.setVisibility(View.GONE);
                        fl_nomessage.setVisibility(View.VISIBLE);

                    }else {

                        sw_layout.setVisibility(View.VISIBLE);
                        ll_sureOrder.setVisibility(View.VISIBLE);
                        fl_nomessage.setVisibility(View.GONE);

                        for (int i = 0; i < allInfo.getData().size(); i++) {
                            groups.add(new GroupInfo(i + "", allInfo.getData().get(i).getShopname(),"",allInfo.getData().get(i).getShopname()));
                            List<ProductInfo> products = new ArrayList<>();
                            List<AllInfo.DataBean.GoodsinfoBean> goodsinfo = allInfo.getData().get(i).getGoodsinfo();

           /* public ProductInfo(String id, String name, String imageUrl, String desc, String price, int count)*/
                            for (int j = 0; j < goodsinfo.size(); j++) {
                                products.add(new ProductInfo(goodsinfo.get(j).getGid(), goodsinfo.get(j).getName(),
                                        goodsinfo.get(j).getThumb(), goodsinfo.get(j).getName(),goodsinfo.get(j).getAid()
                                        ,goodsinfo.get(j).getAction_price(),goodsinfo.get(j).getPrice()
                                        ,Integer.parseInt(goodsinfo.get(j).getCount()),goodsinfo.get(j).getCartid()
                                        ,allInfo.getData().get(i).getShopid(),allInfo.getData().get(i).getShopname(),goodsinfo.get(j).getUnit()));
                            }
                            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
                        }

                        initEvents();

                    }



                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getActivity(),"访问网络失败，请检查您的网络！");

        }
    };

    private void initEvents() {
        selva = new ShopcartExpandableListViewAdapter(groups, children, getActivity());
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(selva);

        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }

        cb_check_all.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_go_to_pay.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
    }


    private List<ProductInfo> productLists = new ArrayList<>();
    private List<GroupInfo> groupsuerorder = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.all_chekbox:
                if (check){
                    tv_check.setText("全部取消");
                    check = false;
                }else {
                    tv_check.setText("全部选中");
                    check = true;
                }
                doCheckAll();
                break;
            case R.id.tv_go_to_pay:
                Log.e(TAG, "onClicktotalCount: "+totalCount );
                if (totalCount == 0) {
                    Toast.makeText(getActivity(), "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }else if (!status.equals("1")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                    return;
                }

                ProductInfo product = null;
                GroupInfo group = null;

                for (int i = 0; i < groups.size(); i++) {
                    group = groups.get(i);
                    groupsuerorder.add(group);

                    List<ProductInfo> childs = children.get(group.getId());
                    for (int j = 0; j < childs.size(); j++) {
                        product = childs.get(j);

                        if (product.isChoosed()) {
                            totalCount = 0;
                            String aid = product.getAid();
                            String action_price = product.getAction_price();
                            if (aid.equals("0")){
                                totalPrice +=(Double.parseDouble(product.getPrice()))*product.getCount();
                            }else {
                                totalPrice +=(Double.parseDouble(action_price))*product.getCount();
                            }

                            productLists.add(product);

//                            Log.e(TAG, "onClick: "+product.getName() );
//                            Log.e(TAG, "onClick: "+group.getShopname() );

                        }
                    }
                }

                Intent intent = new Intent();
                intent.putExtra("productLists", (Serializable) productLists);
                intent.putExtra("result",result);
                intent.setClass(getActivity(),SuerOrderActivity.class);
                startActivity(intent);
                productLists.clear();
                break;
            case R.id.bt_delete:
                if (mark){
                    tv_head_delete.setText("完成");
                    tv_go_to_pay.setVisibility(View.GONE);
                    tv_delete.setVisibility(View.VISIBLE);
                    mark = false;
                }else {
                    tv_head_delete.setText("编辑");
                    tv_go_to_pay.setVisibility(View.VISIBLE);
                    tv_delete.setVisibility(View.GONE);
                    mark = true;
                }

                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(getActivity(), "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }

                doDelete();

                break;
        }
    }

    //购物车编辑
    private void PostShoppingcartEdit(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(AllUrl.SHOPPINGCATEDIT, RequestMethod.POST);
        if (request != null) {

            for (int x=0 ; x< list.size();x++){
                request.add("delete"+"["+x+"]", list.get(x));
                Log.e(TAG, "onActivityResult2222222: "+list.get(x) );
            }

            request.add("dosubmit", 1);
            request.add("count", c);//购物车id+对应的数量
            request.add("cartid", doid);//购物车id+对应的数量


            // 添加到请求队列
            request(0, request, carteditListener, true, true);
        }
    }

    private HttpListener<JSONObject> carteditListener = new HttpListener<JSONObject>() {

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {
            try {

                JSONObject js = response.get();
                Log.e(TAG, "carteditListener: " + js);
                int code = js.getInt("code");
                String mgs = js.getString("msg");
                if (code == 200){
                    ToastUtil.showToast(getActivity(),mgs);
                }else {
                    ToastUtil.showToast(getActivity(),mgs);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

            ToastUtil.showToast(getActivity(),"访问网络失败，请检查您的网络！");

        }
    };

    ArrayList<String> list = new ArrayList<>();
    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    public void doDelete() {
        List<GroupInfo> toBeDeleteGroups = new ArrayList<GroupInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            if (group.isChoosed()) {

                toBeDeleteGroups.add(group);
            }
            List<ProductInfo> toBeDeleteProducts = new ArrayList<ProductInfo>();// 待删除的子元素列表
            List<ProductInfo> childs = children.get(group.getId());

            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));

                    int count = childs.get(j).getCount();
                    String cartid = childs.get(j).getCartid();
                    Log.e(TAG, "要删除的数量: "+count );
                    Log.e(TAG, "要删除的id: "+cartid );
                    list.add(cartid);
                    Log.e(TAG, "doDelete: "+list );

                }
            }
            childs.removeAll(toBeDeleteProducts);
        }

        Log.e(TAG, "doDelete: "+list );

        PostShoppingcartEdit();

        groups.removeAll(toBeDeleteGroups);

        selva.notifyDataSetChanged();
        calculate();
    }

    /** 全选与反选 */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(cb_check_all.isChecked());
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(cb_check_all.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                ProductInfo product = childs.get(j);

                if (product.isChoosed()) {
                    totalCount++;
                    String aid = product.getAid();
                    String action_price = product.getAction_price();
                    if (aid.equals("0")){
                        totalPrice +=(Double.parseDouble(product.getPrice()))*product.getCount();
                    }else {
                        totalPrice +=(Double.parseDouble(action_price))*product.getCount();
                    }
                }
            }
        }
        //double 保留两位小数点
        result = String .format("%.2f",totalPrice);
        tv_total_price.setText("¥" + result);
        tv_go_to_pay.setText("下单(" + totalCount + ")");
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            cb_check_all.setChecked(true);
        else
            cb_check_all.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck())
            cb_check_all.setChecked(true);
        else
            cb_check_all.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }


    public String doid = "";
    public String c = "";
    ArrayList<String> addlist = new ArrayList<>();
    Map<Integer, String> testMap = new HashMap<Integer, String>();

    //增加
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        String cartid = product.getCartid();

        testMap.put(Integer.parseInt(cartid),currentCount+"");

        Log.e(TAG, "doIncrease: "+testMap.toString() );

//        doid = "{";
        doid = cartid;
        c = String.valueOf(currentCount);

//        doid+="\""+cartid+"\":\""+currentCount+"\",";
//
//        doid = doid.substring(0, doid.length()-1);
//        doid +="}";

//        addlist.add(doid);
//        Log.e(TAG, "doIncrease: "+doid );
//        Log.e(TAG, "doIncrease: "+addlist );

        PostShoppingcartEdit();

        selva.notifyDataSetChanged();
        calculate();
    }

    //减少
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;

        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        String cartid = product.getCartid();

        doid = cartid;
        c = String.valueOf(currentCount);

        PostShoppingcartEdit();

        selva.notifyDataSetChanged();
        calculate();
    }

    private boolean isAllCheck() {
        for (GroupInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

}
