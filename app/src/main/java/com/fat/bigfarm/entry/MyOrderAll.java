package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的订单-全部
 * Created by src on 2017/5/24.
 */

public class MyOrderAll implements Serializable {


    /**
     * code : 200
     * data : [{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"98","ordernumber":"T1496832837356","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"99","ordernumber":"T1496832837894","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"96","ordernumber":"T1496832836261","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"97","ordernumber":"T1496832836962","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"95","ordernumber":"T1496832810120","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"93","ordernumber":"T1496832809103","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"94","ordernumber":"T1496832809606","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"89","ordernumber":"T1496832808813","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"90","ordernumber":"T1496832808943","orderstatus":"1"},{"goodsinfo":[{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}],"orderid":"91","ordernumber":"T1496832808349","orderstatus":"1"}]
     * msg : success
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodsinfo : [{"count":"1","gid":"5","name":"测试5","price":"99.99","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","unit":"个"}]
         * orderid : 98
         * ordernumber : T1496832837356
         * orderstatus : 1
         */

        private String orderid;
        private String ordernumber;
        private String orderstatus;
        private List<GoodsinfoBean> goodsinfo;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public String getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
        }

        public List<GoodsinfoBean> getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(List<GoodsinfoBean> goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public static class GoodsinfoBean {
            /**
             * count : 1
             * gid : 5
             * name : 测试5
             * price : 99.99
             * thumb : http://www.kpano.com/kpano/images/jy5.jpg
             * unit : 个
             */

            private String count;
            private String gid;
            private String name;
            private String price;
            private String thumb;
            private String unit;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}
