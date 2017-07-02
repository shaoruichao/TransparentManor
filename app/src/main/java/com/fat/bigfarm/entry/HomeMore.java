package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 物品分类homemore
 * Created by src on 2017/5/10.
 */

public class HomeMore implements Serializable{


    /**
     * typeid : 1
     * typename : 代养套餐
     * typethumb : http://www.kpano.com/kpano/images/jy3.jpg
     * list : [{"id":"1","name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","des":"测试1","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"1","creattime":"1495520600","status":"1"},{"id":"6","name":"测试11","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","des":"测试11","price":"99.99","action_price":"","unit":"个","sid":"1","aid":"0","typeid":"1","creattime":"1495520600","status":"1"}]
     */

    private String typeid;
    private String typename;
    private String typethumb;
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

    public String getTypethumb() {
        return typethumb;
    }

    public void setTypethumb(String typethumb) {
        this.typethumb = typethumb;
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
