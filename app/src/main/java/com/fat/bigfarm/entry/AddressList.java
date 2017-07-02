package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by src on 2017/5/24.
 */

public class AddressList implements Serializable {


    /**
     * code : 200
     * data : [{"city":"北京市","country":"东城区","detail":"新中西里","id":"23","linkman":"no","province":"北京市","status":"2","telnumber":"15111112222","uid":"10002","zip_code":"0"},{"city":"北京市","country":"东城区","detail":"新中西里","id":"19","linkman":"","province":"北京市","status":"1","telnumber":"15111112222","uid":"10002","zip_code":"0"},{"city":"北京市","country":"东城区","detail":"a","id":"20","linkman":"","province":"北京市","status":"1","telnumber":"15111112222","uid":"10002","zip_code":"0"},{"city":"北京市","country":"东城区","detail":"b","id":"21","linkman":"","province":"北京市","status":"1","telnumber":"15111112222","uid":"10002","zip_code":"0"},{"city":"北京市","country":"东城区","detail":"asdf","id":"22","linkman":"qwer","province":"北京市","status":"1","telnumber":"15111112222","uid":"10002","zip_code":"0"}]
     * msg : success
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

    public static class DataBean implements Serializable{
        /**
         * city : 北京市
         * country : 东城区
         * detail : 新中西里
         * id : 23
         * linkman : no
         * province : 北京市
         * status : 2
         * telnumber : 15111112222
         * uid : 10002
         * zip_code : 0
         */

        private String city;
        private String country;
        private String detail;
        private String id;
        private String linkman;
        private String province;
        private String status;
        private String telnumber;
        private String uid;
        private String zip_code;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTelnumber() {
            return telnumber;
        }

        public void setTelnumber(String telnumber) {
            this.telnumber = telnumber;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getZip_code() {
            return zip_code;
        }

        public void setZip_code(String zip_code) {
            this.zip_code = zip_code;
        }
    }
}
