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
     * data : {"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"7天","des":"含糖量高、麝香味浓、着色好,深受消费者喜爱","des_url":["http://www.9fat.com/tmnzimage/gyptdes.png"],"fostertime":"100天","freight":"0","guess":[{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"7天","des":"色泽鲜艳晶莹剔透，味道鲜美","des_url":"http://www.9fat.com/tmnzimage/gyxzdes.png","fostertime":"365天","freight":"0","id":"14","income_count":"0","is_income":"0","label":"","name":"香味果园 水晶杏代养","name_url":"","price":"366.00","sid":"3","status":"1","thumb":"http://www.9fat.com/tmnzimage/gyxz1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""},{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"7天","des":"鲜红多汁，质地柔软，酸甜适中，气味芳香","des_url":"http://www.9fat.com/tmnzimage/gycmdes.png","fostertime":"45天","freight":"0","id":"12","income_count":"0","is_income":"0","label":"","name":"香味果园 草莓代养","name_url":"","price":"299.00","sid":"3","status":"1","thumb":"http://www.9fat.com/tmnzimage/gycm1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""},{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"7天","des":"补充番茄红素，抵抗衰老","des_url":"http://www.9fat.com/tmnzimage/wawcxhsdes.png","fostertime":"170天","freight":"0","id":"17","income_count":"0","is_income":"0","label":"","name":"我爱我菜 西红柿代养","name_url":"","price":"229.00","sid":"4","status":"1","thumb":"http://www.9fat.com/tmnzimage/wawcxhs1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""},{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"30天","des":"质地柔嫩，味道清香","des_url":"http://www.9fat.com/tmnzimage/wawcxbcdes.png","fostertime":"30天","freight":"18","id":"16","income_count":"0","is_income":"0","label":"","name":"我爱我菜 小白菜代养","name_url":"","price":"138.00","sid":"4","status":"1","thumb":"http://www.9fat.com/tmnzimage/wawcxbc1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""}],"id":"10","income_count":"0","is_income":"0","label":"","name":"香味果园 正玫瑰葡萄代养","name_url":"","price":"169.00","shopname":"香味果园","sid":"3","status":"1","thumb":["http://www.9fat.com/tmnzimage/gypt1.jpg","http://www.9fat.com/tmnzimage/gypt2.jpg","http://www.9fat.com/tmnzimage/gypt3.jpg"],"typeid":"1","uid":"0","unit":"套","username":"","videos":""}
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

    public static class DataBean implements Serializable{
        /**
         * action_price :
         * aid : 0
         * content : 时令耕作物
         * creattime : 1496025707
         * cycle : 7天
         * des : 含糖量高、麝香味浓、着色好,深受消费者喜爱
         * des_url : ["http://www.9fat.com/tmnzimage/gyptdes.png"]
         * fostertime : 100天
         * freight : 0
         * guess : [{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"7天","des":"色泽鲜艳晶莹剔透，味道鲜美","des_url":"http://www.9fat.com/tmnzimage/gyxzdes.png","fostertime":"365天","freight":"0","id":"14","income_count":"0","is_income":"0","label":"","name":"香味果园 水晶杏代养","name_url":"","price":"366.00","sid":"3","status":"1","thumb":"http://www.9fat.com/tmnzimage/gyxz1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""},{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"7天","des":"鲜红多汁，质地柔软，酸甜适中，气味芳香","des_url":"http://www.9fat.com/tmnzimage/gycmdes.png","fostertime":"45天","freight":"0","id":"12","income_count":"0","is_income":"0","label":"","name":"香味果园 草莓代养","name_url":"","price":"299.00","sid":"3","status":"1","thumb":"http://www.9fat.com/tmnzimage/gycm1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""},{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"7天","des":"补充番茄红素，抵抗衰老","des_url":"http://www.9fat.com/tmnzimage/wawcxhsdes.png","fostertime":"170天","freight":"0","id":"17","income_count":"0","is_income":"0","label":"","name":"我爱我菜 西红柿代养","name_url":"","price":"229.00","sid":"4","status":"1","thumb":"http://www.9fat.com/tmnzimage/wawcxhs1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""},{"action_price":"","aid":"0","content":"时令耕作物","creattime":"1496025707","cycle":"30天","des":"质地柔嫩，味道清香","des_url":"http://www.9fat.com/tmnzimage/wawcxbcdes.png","fostertime":"30天","freight":"18","id":"16","income_count":"0","is_income":"0","label":"","name":"我爱我菜 小白菜代养","name_url":"","price":"138.00","sid":"4","status":"1","thumb":"http://www.9fat.com/tmnzimage/wawcxbc1.jpg","typeid":"1","uid":"0","unit":"套","username":"","videos":""}]
         * id : 10
         * income_count : 0
         * is_income : 0
         * label :
         * name : 香味果园 正玫瑰葡萄代养
         * name_url :
         * price : 169.00
         * shopname : 香味果园
         * sid : 3
         * status : 1
         * thumb : ["http://www.9fat.com/tmnzimage/gypt1.jpg","http://www.9fat.com/tmnzimage/gypt2.jpg","http://www.9fat.com/tmnzimage/gypt3.jpg"]
         * typeid : 1
         * uid : 0
         * unit : 套
         * username :
         * videos :
         */

        private String action_price;
        private String aid;
        private String content;
        private String creattime;
        private String cycle;
        private String des;
        private String fostertime;
        private String freight;
        private String id;
        private String income_count;
        private String is_income;
        private String label;
        private String name;
        private String name_url;
        private String price;
        private String shopname;
        private String sid;
        private String status;
        private String typeid;
        private String uid;
        private String unit;
        private String username;
        private String videos;
        private List<String> des_url;
        private List<GuessBean> guess;
        private List<String> thumb;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getFostertime() {
            return fostertime;
        }

        public void setFostertime(String fostertime) {
            this.fostertime = fostertime;
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

        public String getIncome_count() {
            return income_count;
        }

        public void setIncome_count(String income_count) {
            this.income_count = income_count;
        }

        public String getIs_income() {
            return is_income;
        }

        public void setIs_income(String is_income) {
            this.is_income = is_income;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName_url() {
            return name_url;
        }

        public void setName_url(String name_url) {
            this.name_url = name_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
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

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getVideos() {
            return videos;
        }

        public void setVideos(String videos) {
            this.videos = videos;
        }

        public List<String> getDes_url() {
            return des_url;
        }

        public void setDes_url(List<String> des_url) {
            this.des_url = des_url;
        }

        public List<GuessBean> getGuess() {
            return guess;
        }

        public void setGuess(List<GuessBean> guess) {
            this.guess = guess;
        }

        public List<String> getThumb() {
            return thumb;
        }

        public void setThumb(List<String> thumb) {
            this.thumb = thumb;
        }

        public static class GuessBean {
            /**
             * action_price :
             * aid : 0
             * content : 时令耕作物
             * creattime : 1496025707
             * cycle : 7天
             * des : 色泽鲜艳晶莹剔透，味道鲜美
             * des_url : http://www.9fat.com/tmnzimage/gyxzdes.png
             * fostertime : 365天
             * freight : 0
             * id : 14
             * income_count : 0
             * is_income : 0
             * label :
             * name : 香味果园 水晶杏代养
             * name_url :
             * price : 366.00
             * sid : 3
             * status : 1
             * thumb : http://www.9fat.com/tmnzimage/gyxz1.jpg
             * typeid : 1
             * uid : 0
             * unit : 套
             * username :
             * videos :
             */

            private String action_price;
            private String aid;
            private String content;
            private String creattime;
            private String cycle;
            private String des;
            private String des_url;
            private String fostertime;
            private String freight;
            private String id;
            private String income_count;
            private String is_income;
            private String label;
            private String name;
            private String name_url;
            private String price;
            private String sid;
            private String status;
            private String thumb;
            private String typeid;
            private String uid;
            private String unit;
            private String username;
            private String videos;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
            }

            public String getCycle() {
                return cycle;
            }

            public void setCycle(String cycle) {
                this.cycle = cycle;
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

            public String getFostertime() {
                return fostertime;
            }

            public void setFostertime(String fostertime) {
                this.fostertime = fostertime;
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

            public String getIncome_count() {
                return income_count;
            }

            public void setIncome_count(String income_count) {
                this.income_count = income_count;
            }

            public String getIs_income() {
                return is_income;
            }

            public void setIs_income(String is_income) {
                this.is_income = is_income;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName_url() {
                return name_url;
            }

            public void setName_url(String name_url) {
                this.name_url = name_url;
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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getVideos() {
                return videos;
            }

            public void setVideos(String videos) {
                this.videos = videos;
            }
        }
    }
}
