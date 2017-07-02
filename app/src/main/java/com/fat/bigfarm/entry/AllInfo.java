package com.fat.bigfarm.entry;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class AllInfo {


    /**
     * code : 200
     * msg : success
     * data : [{"shopid":"3","shopname":"农机农场","shopselect":0,"goodsinfo":[{"cartid":"211","gid":"21","aid":"0","name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","price":"2","action_price":"1","freight":"0","unit":"个","selected":"0","count":"1"}],"freight":"0"},{"shopid":"1","shopname":"云农场","shopselect":0,"goodsinfo":[{"cartid":"210","gid":"19","aid":"0","name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","price":"2","action_price":"","freight":"0","unit":"个","selected":"0","count":"1"}],"freight":"0"}]
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
         * shopid : 3
         * shopname : 农机农场
         * shopselect : 0
         * goodsinfo : [{"cartid":"211","gid":"21","aid":"0","name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","price":"2","action_price":"1","freight":"0","unit":"个","selected":"0","count":"1"}]
         * freight : 0
         */

        private String shopid;
        private String shopname;
        private int shopselect;
        private String freight;
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

        public int getShopselect() {
            return shopselect;
        }

        public void setShopselect(int shopselect) {
            this.shopselect = shopselect;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public List<GoodsinfoBean> getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(List<GoodsinfoBean> goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public static class GoodsinfoBean {
            /**
             * cartid : 211
             * gid : 21
             * aid : 0
             * name : 测试123
             * thumb : http://www.kpano.com/kpano/images/jy1.jpg
             * price : 2
             * action_price : 1
             * freight : 0
             * unit : 个
             * selected : 0
             * count : 1
             */

            private String cartid;
            private String gid;
            private String aid;
            private String name;
            private String thumb;
            private String price;
            private String action_price;
            private String freight;
            private String unit;
            private String selected;
            private String count;

            public String getCartid() {
                return cartid;
            }

            public void setCartid(String cartid) {
                this.cartid = cartid;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
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

            public String getAction_price() {
                return action_price;
            }

            public void setAction_price(String action_price) {
                this.action_price = action_price;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
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
