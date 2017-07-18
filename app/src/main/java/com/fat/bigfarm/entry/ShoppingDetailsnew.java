package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 分类详情
 * Created by src on 2017/5/10.
 */

public class ShoppingDetailsnew implements Serializable {


    /**
     * code : 200
     * data : {"advertising":[""],"creattime":"0","des":"","des_url":"http://www.9fat.com/H5test/farmapp0608/newadd/wantplant.html","icon":"http://www.9fat.com/tmnzimage/wyzb@2x.png","id":"1","is_head":"1","logo":"http://www.9fat.com/tmnzimage/wyzc@2x.png","name":"我要种","new_icon":"","status":"1","thumb":"http://www.9fat.com/tmnzimage/ste1.jpg"}
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
         * advertising : [""]
         * creattime : 0
         * des :
         * des_url : http://www.9fat.com/H5test/farmapp0608/newadd/wantplant.html
         * icon : http://www.9fat.com/tmnzimage/wyzb@2x.png
         * id : 1
         * is_head : 1
         * logo : http://www.9fat.com/tmnzimage/wyzc@2x.png
         * name : 我要种
         * new_icon :
         * status : 1
         * thumb : http://www.9fat.com/tmnzimage/ste1.jpg
         */

        private String creattime;
        private String des;
        private String des_url;
        private String icon;
        private String id;
        private String is_head;
        private String logo;
        private String name;
        private String new_icon;
        private String status;
        private String thumb;
        private List<String> advertising;

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

        public String getDes_url() {
            return des_url;
        }

        public void setDes_url(String des_url) {
            this.des_url = des_url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_head() {
            return is_head;
        }

        public void setIs_head(String is_head) {
            this.is_head = is_head;
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

        public String getNew_icon() {
            return new_icon;
        }

        public void setNew_icon(String new_icon) {
            this.new_icon = new_icon;
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

        public List<String> getAdvertising() {
            return advertising;
        }

        public void setAdvertising(List<String> advertising) {
            this.advertising = advertising;
        }
    }
}
