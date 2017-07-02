package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 新品列表
 * Created by src on 2017/5/10.
 */

public class ProductList implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : [{"id":"1","name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg http://www.kpano.com/kpano/images/jy1.jpg http://www.kpano.com/kpano/images/jy1.jpg","des":"测试1","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"1","creattime":"1495520600","status":"1"},{"id":"2","name":"测试2","thumb":"http://www.kpano.com/kpano/images/jy2.jpg http://www.kpano.com/kpano/images/jy2.jpg http://www.kpano.com/kpano/images/jy2.jpg","des":"测试2","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"2","creattime":"1495520600","status":"1"},{"id":"5","name":"测试5","thumb":"http://www.kpano.com/kpano/images/jy5.jpg http://www.kpano.com/kpano/images/jy5.jpg http://www.kpano.com/kpano/images/jy5.jpg ","des":"测试5","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"5","creattime":"1495520600","status":"1"},{"id":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg ","des":"测试11","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"1","creattime":"1495520600","status":"1"},{"id":"11","name":"测试555","thumb":"http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg","des":"测试555","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"5","creattime":"1495520600","status":"1"},{"id":"13","name":"测试55555","thumb":"http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg","des":"测试55555","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"5","creattime":"1495520600","status":"1"},{"id":"16","name":"测试333","thumb":"http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg http://www.kpano.com/kpano/images/jy3.jpg","des":"测试333","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"3","creattime":"1495520600","status":"1"}]
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
         * id : 1
         * name : 测试1
         * thumb : http://www.kpano.com/kpano/images/jy1.jpg http://www.kpano.com/kpano/images/jy1.jpg http://www.kpano.com/kpano/images/jy1.jpg
         * des : 测试1
         * price : 99.99
         * action_price :
         * unit : 个
         * sid : 1
         * aid : 0
         * typeid : 1
         * creattime : 1495520600
         * status : 1
         */

        private String id;
        private String name;
        private String thumb;
        private String des;
        private String price;
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
