package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by src on 2017/6/17.
 */

public class ActivitiesDetail implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"picture":"http://www.9fat.com/tmnzimage/zlbj@2x.png","action_id":"1","action_name":"周六半价","action_thumb":"http://www.9fat.com/tmnzimage/zlbj@2x.png","action_des":"","action_status":"1","goods":[{"id":"1","name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","des":"测试1","price":"100","freight":"0","action_price":"50","unit":"个","sid":"1","aid":"1","typeid":"1","creattime":"1495520600","status":"1"},{"id":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","des":"测试2","price":"200","freight":"0","action_price":"100","unit":"个","sid":"2","aid":"1","typeid":"2","creattime":"1495520600","status":"1"},{"id":"3","name":"测试3","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试3","price":"300","freight":"0","action_price":"150","unit":"个","sid":"3","aid":"1","typeid":"3","creattime":"1495334200","status":"1"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picture : http://www.9fat.com/tmnzimage/zlbj@2x.png
         * action_id : 1
         * action_name : 周六半价
         * action_thumb : http://www.9fat.com/tmnzimage/zlbj@2x.png
         * action_des :
         * action_status : 1
         * goods : [{"id":"1","name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","des":"测试1","price":"100","freight":"0","action_price":"50","unit":"个","sid":"1","aid":"1","typeid":"1","creattime":"1495520600","status":"1"},{"id":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","des":"测试2","price":"200","freight":"0","action_price":"100","unit":"个","sid":"2","aid":"1","typeid":"2","creattime":"1495520600","status":"1"},{"id":"3","name":"测试3","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试3","price":"300","freight":"0","action_price":"150","unit":"个","sid":"3","aid":"1","typeid":"3","creattime":"1495334200","status":"1"}]
         */

        private String picture;
        private String action_id;
        private String action_name;
        private String action_thumb;
        private String action_des;
        private String action_status;
        private List<GoodsBean> goods;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getAction_id() {
            return action_id;
        }

        public void setAction_id(String action_id) {
            this.action_id = action_id;
        }

        public String getAction_name() {
            return action_name;
        }

        public void setAction_name(String action_name) {
            this.action_name = action_name;
        }

        public String getAction_thumb() {
            return action_thumb;
        }

        public void setAction_thumb(String action_thumb) {
            this.action_thumb = action_thumb;
        }

        public String getAction_des() {
            return action_des;
        }

        public void setAction_des(String action_des) {
            this.action_des = action_des;
        }

        public String getAction_status() {
            return action_status;
        }

        public void setAction_status(String action_status) {
            this.action_status = action_status;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 1
             * name : 测试1
             * thumb : http://www.kpano.com/kpano/images/jy1.jpg
             * des : 测试1
             * price : 100
             * freight : 0
             * action_price : 50
             * unit : 个
             * sid : 1
             * aid : 1
             * typeid : 1
             * creattime : 1495520600
             * status : 1
             */

            private String id;
            private String name;
            private String thumb;
            private String des;
            private String price;
            private String freight;
            private String action_price;
            private String unit;
            private String sid;
            private String aid;
            private String typeid;
            private String creattime;
            private String status;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getAction_price() {
                return action_price;
            }

            public void setAction_price(String action_price) {
                this.action_price = action_price;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
