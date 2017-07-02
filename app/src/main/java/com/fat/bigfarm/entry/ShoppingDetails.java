package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 商家详情
 * Created by src on 2017/5/10.
 */

public class ShoppingDetails implements Serializable {


    /**
     * code : 200
     * data : {"advertising":["http://www.kpano.com/kpano/images/jy1.jpg","http://www.kpano.com/kpano/images/jy2.jpg","http://www.kpano.com/kpano/images/jy3.jpg"],"creattime":"0","des":"","goods":[{"action_price":"50","aid":"1","creattime":"1495520600","des":"测试1","freight":"0","id":"1","name":"测试1","price":"100","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","typeid":"1","unit":"个"},{"action_price":"","aid":"0","creattime":"1495520600","des":"测试5","freight":"0","id":"5","name":"测试5","price":"99.99","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","typeid":"5","unit":"个"},{"action_price":"","aid":"0","creattime":"1495434200","des":"测试44","freight":"0","id":"9","name":"测试44","price":"99.99","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","typeid":"4","unit":"个"},{"action_price":"","aid":"0","creattime":"1495520600","des":"测试55555","freight":"0","id":"13","name":"测试55555","price":"99.99","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","typeid":"5","unit":"个"}],"id":"1","logo":"http://www.9fat.com/tmnzimage/ync@2x.png","name":"云农场","status":"1"}
     * msg : success
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * advertising : ["http://www.kpano.com/kpano/images/jy1.jpg","http://www.kpano.com/kpano/images/jy2.jpg","http://www.kpano.com/kpano/images/jy3.jpg"]
         * creattime : 0
         * des :
         * goods : [{"action_price":"50","aid":"1","creattime":"1495520600","des":"测试1","freight":"0","id":"1","name":"测试1","price":"100","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","typeid":"1","unit":"个"},{"action_price":"","aid":"0","creattime":"1495520600","des":"测试5","freight":"0","id":"5","name":"测试5","price":"99.99","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","typeid":"5","unit":"个"},{"action_price":"","aid":"0","creattime":"1495434200","des":"测试44","freight":"0","id":"9","name":"测试44","price":"99.99","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","typeid":"4","unit":"个"},{"action_price":"","aid":"0","creattime":"1495520600","des":"测试55555","freight":"0","id":"13","name":"测试55555","price":"99.99","sid":"1","status":"1","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","typeid":"5","unit":"个"}]
         * id : 1
         * logo : http://www.9fat.com/tmnzimage/ync@2x.png
         * name : 云农场
         * status : 1
         */

        private String creattime;
        private String des;
        private String id;
        private String logo;
        private String name;
        private String status;
        private List<String> advertising;
        private List<GoodsBean> goods;

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getAdvertising() {
            return advertising;
        }

        public void setAdvertising(List<String> advertising) {
            this.advertising = advertising;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * action_price : 50
             * aid : 1
             * creattime : 1495520600
             * des : 测试1
             * freight : 0
             * id : 1
             * name : 测试1
             * price : 100
             * sid : 1
             * status : 1
             * thumb : http://www.kpano.com/kpano/images/jy1.jpg
             * typeid : 1
             * unit : 个
             */

            private String action_price;
            private String aid;
            private String creattime;
            private String des;
            private String freight;
            private String id;
            private String name;
            private String price;
            private String sid;
            private String status;
            private String thumb;
            private String typeid;
            private String unit;

            public String getAction_price() {
                return action_price;
            }

            public void setAction_price(String action_price) {
                this.action_price = action_price;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
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
