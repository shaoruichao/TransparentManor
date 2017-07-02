package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by src on 2017/5/25.
 */

public class Shopping implements Serializable {


    /**
     * code : 200
     * data : [{"goodsinfo":[{"cartid":"57","count":"1","gid":"1","name":"测试1","price":"100","selected":"0","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","unit":"个"},{"cartid":"8","count":"3","gid":"2","name":"测试2","price":"200","selected":"0","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","unit":"个"}],"shopid":"1","shopname":"云农场"},{"goodsinfo":[{"cartid":"15","count":"10","gid":"10","name":"测试55","price":"99.99","selected":"0","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","unit":"个"},{"cartid":"9","count":"3","gid":"11","name":"测试555","price":"99.99","selected":"0","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","unit":"个"}],"shopid":"2","shopname":"纯草农庄"}]
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

    public static class DataBean implements Serializable{
        /**
         * goodsinfo : [{"cartid":"57","count":"1","gid":"1","name":"测试1","price":"100","selected":"0","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","unit":"个"},{"cartid":"8","count":"3","gid":"2","name":"测试2","price":"200","selected":"0","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","unit":"个"}]
         * shopid : 1
         * shopname : 云农场
         */

        private String shopid;
        private String shopname;
        private List<GoodsinfoBean> goodsinfo;

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public List<GoodsinfoBean> getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(List<GoodsinfoBean> goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public static class GoodsinfoBean {
            /**
             * cartid : 57
             * count : 1
             * gid : 1
             * name : 测试1
             * price : 100
             * selected : 0
             * thumb : http://www.kpano.com/kpano/images/jy1.jpg
             * unit : 个
             */

            private String cartid;
            private String count;
            private String gid;
            private String name;
            private String price;
            private String selected;
            private String thumb;
            private String unit;

            public String getCartid() {
                return cartid;
            }

            public void setCartid(String cartid) {
                this.cartid = cartid;
            }

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

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
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
