package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页列表
 * Created by src on 2017/5/10.
 */

public class HomeList implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : [{"typeid":"1","typename":"代养套餐","typestatus":"1","icon":"http://www.9fat.com/tmnzimage/dytc1@2x.png","list":[{"id":"1","name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","des":"测试1","price":"100","freight":"0","action_price":"50","unit":"个","sid":"1","aid":"1","typeid":"1","creattime":"1495520600","status":"1"},{"id":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试11","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"2","aid":"0","typeid":"1","creattime":"1495520600","status":"1"}]},{"typeid":"2","typename":"高端定制","typestatus":"1","icon":"http://www.9fat.com/tmnzimage/gddz1@2x.png","list":[{"id":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg","des":"测试2","price":"200","freight":"0","action_price":"100","unit":"个","sid":"2","aid":"1","typeid":"2","creattime":"1495520600","status":"1"},{"id":"7","name":"测试22","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试22","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"3","aid":"0","typeid":"2","creattime":"1495434200","status":"1"}]},{"typeid":"3","typename":"肉禽蛋类","typestatus":"2","icon":"http://www.9fat.com/tmnzimage/rqdl1@2x.png","list":[{"id":"3","name":"测试3","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试3","price":"300","freight":"0","action_price":"150","unit":"个","sid":"3","aid":"1","typeid":"3","creattime":"1495334200","status":"1"},{"id":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试33","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"4","aid":"0","typeid":"3","creattime":"1495434200","status":"1"},{"id":"16","name":"测试333","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试333","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"4","aid":"0","typeid":"3","creattime":"1495520600","status":"1"}]},{"typeid":"4","typename":"瓜果时蔬","typestatus":"2","icon":"http://www.9fat.com/tmnzimage/ggss1@2x.png","list":[{"id":"4","name":"测试4","thumb":"http://www.kpano.com/kpano/images/jy4.jpg","des":"测试4","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"4","aid":"0","typeid":"4","creattime":"1495334200","status":"1"},{"id":"9","name":"测试44","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试44","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"4","creattime":"1495434200","status":"1"},{"id":"14","name":"测试444","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试444","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"2","aid":"0","typeid":"4","creattime":"1495434200","status":"1"},{"id":"15","name":"测试4444","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试4444","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"3","aid":"0","typeid":"4","creattime":"1495434200","status":"1"}]},{"typeid":"5","typename":"收益专卖","typestatus":"2","icon":"http://www.9fat.com/tmnzimage/syzm1@2x.png","list":[{"id":"5","name":"测试5","thumb":"http://www.kpano.com/kpano/images/jy5.jpg","des":"测试5","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"5","creattime":"1495520600","status":"1"},{"id":"10","name":"测试55","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试55","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"2","aid":"0","typeid":"5","creattime":"1495434200","status":"1"},{"id":"11","name":"测试555","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试555","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"3","aid":"0","typeid":"5","creattime":"1495520600","status":"1"},{"id":"12","name":"测试5555","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","des":"测试5555","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"4","aid":"0","typeid":"5","creattime":"1495434200","status":"1"},{"id":"13","name":"测试55555","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试55555","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"5","creattime":"1495520600","status":"1"}]}]
     */

    private int code;
    private String msg;
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * typeid : 1
         * typename : 代养套餐
         * typestatus : 1
         * icon : http://www.9fat.com/tmnzimage/dytc1@2x.png
         * list : [{"id":"1","name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","des":"测试1","price":"100","freight":"0","action_price":"50","unit":"个","sid":"1","aid":"1","typeid":"1","creattime":"1495520600","status":"1"},{"id":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试11","price":"99.99","freight":"0","action_price":"","unit":"个","sid":"2","aid":"0","typeid":"1","creattime":"1495520600","status":"1"}]
         */

        private String typeid;
        private String typename;
        private String typestatus;
        private String icon;
        private List<ListBean> list;

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getTypestatus() {
            return typestatus;
        }

        public void setTypestatus(String typestatus) {
            this.typestatus = typestatus;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
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
