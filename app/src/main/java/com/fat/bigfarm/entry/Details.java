package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 物品详情
 * Created by src on 2017/5/10.
 */

public class Details implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : {"id":"1","name":"纯草农庄贵妃鸡1+2套餐","thumb":["http://www.9fat.com/tmnzimage/ccnz1+2@2x.png","\nhttp://jushizhibo.com/uploadfile/2017/0331/20170331045520936.jpg","\nhttp://www.9fat.com/tmnzimage/ccnzjd@2x.png"],"videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"299.00","freight":"0","action_price":"","unit":"套","sid":"2","aid":"0","uid":"0","typeid":"1","fostertime":"1年","cycle":"7天","content":"贵妃鸡*1只,鸡蛋*10枚","is_income":"0","income_count":"0","creattime":"1496025707","status":"1","shopname":"纯草农庄","guess":[{"id":"12","name":"纯草农庄自种有机蓝莓10盆","thumb":"http://www.9fat.com/tmnzimage/yncpt@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"199.00","freight":"0","action_price":"","unit":"套","sid":"2","aid":"0","uid":"0","typeid":"1","fostertime":"90天","cycle":"7天","content":"蓝莓*5盒","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"},{"id":"13","name":"农机农场黄酸柠檬*10棵","thumb":"http://www.9fat.com/tmnzimage/yncnm@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"399.00","freight":"10","action_price":"","unit":"套","sid":"3","aid":"0","uid":"0","typeid":"1","fostertime":"3年","cycle":"7天","content":"柠檬*10盒","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"},{"id":"27","name":"农家乐梅花鹿代养套餐","thumb":"http://www.9fat.com/tmnzimage/mhl@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"399.00","freight":"0","action_price":"299.99","unit":"套","sid":"4","aid":"3","uid":"0","typeid":"1","fostertime":"2年","cycle":"30天","content":"鹿茸*5盒","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"},{"id":"10","name":"云农场奶牛代养套餐","thumb":"http://www.9fat.com/tmnzimage/yncnn@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"359.00","freight":"0","action_price":"","unit":"套","sid":"1","aid":"0","uid":"0","typeid":"1","fostertime":"180天","cycle":"1天","content":"牛奶*1瓶","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"}]}
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
         * id : 1
         * name : 纯草农庄贵妃鸡1+2套餐
         * thumb : ["http://www.9fat.com/tmnzimage/ccnz1+2@2x.png","\nhttp://jushizhibo.com/uploadfile/2017/0331/20170331045520936.jpg","\nhttp://www.9fat.com/tmnzimage/ccnzjd@2x.png"]
         * videos : http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4
         * des :
         * des_url : http://www.9fat.com/tmnzimage/20161230111346913.jpg
         * label :
         * price : 299.00
         * freight : 0
         * action_price :
         * unit : 套
         * sid : 2
         * aid : 0
         * uid : 0
         * typeid : 1
         * fostertime : 1年
         * cycle : 7天
         * content : 贵妃鸡*1只,鸡蛋*10枚
         * is_income : 0
         * income_count : 0
         * creattime : 1496025707
         * status : 1
         * shopname : 纯草农庄
         * guess : [{"id":"12","name":"纯草农庄自种有机蓝莓10盆","thumb":"http://www.9fat.com/tmnzimage/yncpt@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"199.00","freight":"0","action_price":"","unit":"套","sid":"2","aid":"0","uid":"0","typeid":"1","fostertime":"90天","cycle":"7天","content":"蓝莓*5盒","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"},{"id":"13","name":"农机农场黄酸柠檬*10棵","thumb":"http://www.9fat.com/tmnzimage/yncnm@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"399.00","freight":"10","action_price":"","unit":"套","sid":"3","aid":"0","uid":"0","typeid":"1","fostertime":"3年","cycle":"7天","content":"柠檬*10盒","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"},{"id":"27","name":"农家乐梅花鹿代养套餐","thumb":"http://www.9fat.com/tmnzimage/mhl@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"399.00","freight":"0","action_price":"299.99","unit":"套","sid":"4","aid":"3","uid":"0","typeid":"1","fostertime":"2年","cycle":"30天","content":"鹿茸*5盒","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"},{"id":"10","name":"云农场奶牛代养套餐","thumb":"http://www.9fat.com/tmnzimage/yncnn@2x.png","videos":"http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4","des":"","des_url":"http://www.9fat.com/tmnzimage/20161230111346913.jpg","label":"","price":"359.00","freight":"0","action_price":"","unit":"套","sid":"1","aid":"0","uid":"0","typeid":"1","fostertime":"180天","cycle":"1天","content":"牛奶*1瓶","is_income":"0","income_count":"0","creattime":"1496025707","status":"1"}]
         */

        private String id;
        private String name;
        private String videos;
        private String des;
        private String des_url;
        private String label;
        private String price;
        private String freight;
        private String action_price;
        private String unit;
        private String sid;
        private String aid;
        private String uid;
        private String typeid;
        private String fostertime;
        private String cycle;
        private String content;
        private String is_income;
        private String income_count;
        private String creattime;
        private String status;
        private String shopname;
        private List<String> thumb;
        private List<GuessBean> guess;

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

        public String getVideos() {
            return videos;
        }

        public void setVideos(String videos) {
            this.videos = videos;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getDes_url() {
            return des_url;
        }

        public void setDes_url(String des_url) {
            this.des_url = des_url;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getFostertime() {
            return fostertime;
        }

        public void setFostertime(String fostertime) {
            this.fostertime = fostertime;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_income() {
            return is_income;
        }

        public void setIs_income(String is_income) {
            this.is_income = is_income;
        }

        public String getIncome_count() {
            return income_count;
        }

        public void setIncome_count(String income_count) {
            this.income_count = income_count;
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

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public List<String> getThumb() {
            return thumb;
        }

        public void setThumb(List<String> thumb) {
            this.thumb = thumb;
        }

        public List<GuessBean> getGuess() {
            return guess;
        }

        public void setGuess(List<GuessBean> guess) {
            this.guess = guess;
        }

        public static class GuessBean {
            /**
             * id : 12
             * name : 纯草农庄自种有机蓝莓10盆
             * thumb : http://www.9fat.com/tmnzimage/yncpt@2x.png
             * videos : http://jushizhibo.com/uploadfile/2017/0612/20170612041940118.mp4
             * des :
             * des_url : http://www.9fat.com/tmnzimage/20161230111346913.jpg
             * label :
             * price : 199.00
             * freight : 0
             * action_price :
             * unit : 套
             * sid : 2
             * aid : 0
             * uid : 0
             * typeid : 1
             * fostertime : 90天
             * cycle : 7天
             * content : 蓝莓*5盒
             * is_income : 0
             * income_count : 0
             * creattime : 1496025707
             * status : 1
             */

            private String id;
            private String name;
            private String thumb;
            private String videos;
            private String des;
            private String des_url;
            private String label;
            private String price;
            private String freight;
            private String action_price;
            private String unit;
            private String sid;
            private String aid;
            private String uid;
            private String typeid;
            private String fostertime;
            private String cycle;
            private String content;
            private String is_income;
            private String income_count;
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

            public String getVideos() {
                return videos;
            }

            public void setVideos(String videos) {
                this.videos = videos;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getDes_url() {
                return des_url;
            }

            public void setDes_url(String des_url) {
                this.des_url = des_url;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getFostertime() {
                return fostertime;
            }

            public void setFostertime(String fostertime) {
                this.fostertime = fostertime;
            }

            public String getCycle() {
                return cycle;
            }

            public void setCycle(String cycle) {
                this.cycle = cycle;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIs_income() {
                return is_income;
            }

            public void setIs_income(String is_income) {
                this.is_income = is_income;
            }

            public String getIncome_count() {
                return income_count;
            }

            public void setIncome_count(String income_count) {
                this.income_count = income_count;
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
