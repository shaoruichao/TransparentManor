package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的订单-待付款
 * Created by src on 2017/5/24.
 */

public class MyOrderObligation implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : [{"orderid":"535","ordernumber":"T1498056584631","orderstatus":"1","goodsinfo":[{"gid":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","price":"2","unit":"个","count":"1"},{"gid":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"5","unit":"个","count":"1"}]},{"orderid":"536","ordernumber":"T1498056584876","orderstatus":"1","goodsinfo":[{"gid":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"3","unit":"个","count":"3"}]},{"orderid":"533","ordernumber":"T1498056538890","orderstatus":"1","goodsinfo":[{"gid":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","price":"2","unit":"个","count":"1"},{"gid":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"5","unit":"个","count":"1"}]},{"orderid":"534","ordernumber":"T1498056538322","orderstatus":"1","goodsinfo":[{"gid":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"3","unit":"个","count":"3"}]},{"orderid":"531","ordernumber":"T1498056529648","orderstatus":"1","goodsinfo":[{"gid":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","price":"2","unit":"个","count":"1"},{"gid":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"5","unit":"个","count":"1"}]},{"orderid":"532","ordernumber":"T1498056529553","orderstatus":"1","goodsinfo":[{"gid":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"3","unit":"个","count":"3"}]},{"orderid":"529","ordernumber":"T1498056280847","orderstatus":"1","goodsinfo":[{"gid":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","price":"2","unit":"个","count":"1"},{"gid":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"5","unit":"个","count":"1"}]},{"orderid":"530","ordernumber":"T1498056280506","orderstatus":"1","goodsinfo":[{"gid":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"3","unit":"个","count":"3"}]},{"orderid":"521","ordernumber":"T1498043379134","orderstatus":"1","goodsinfo":[{"gid":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"3","unit":"个","count":"3"}]},{"orderid":"436","ordernumber":"T1498015600969","orderstatus":"1","goodsinfo":[{"gid":"19","name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","price":"2","unit":"个","count":"1"}]}]
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
         * orderid : 535
         * ordernumber : T1498056584631
         * orderstatus : 1
         * goodsinfo : [{"gid":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","price":"2","unit":"个","count":"1"},{"gid":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"5","unit":"个","count":"1"}]
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
             * gid : 2
             * name : 测试2
             * thumb : http://www.kpano.com/kpano/images/jy2.jpg
             * price : 2
             * unit : 个
             * count : 1
             */

            private String gid;
            private String name;
            private String thumb;
            private String price;
            private String unit;
            private String count;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}
