package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 注册
 * Created by src on 2016/9/18.
 */
public class Register implements Serializable {


    /**
     * code : 200
     * msg : 恭喜您15315361536，注册成功！
     * status : 1
     * data : {"encrypt":"l3sV5k","mobile":"15315361536","username":"15315361536","nickname":"s1495882227314","email":"15315361536@9fat.com","password":"48ea34e2719f91e34c892bf7b88cc8b1","modelid":10,"regip":"61.149.242.31","point":0,"amount":0,"lastdate":1495882227,"regdate":1495882227,"siteid":1,"connectid":"","from":"","groupid":2,"phpssouid":"10039","userid":"10039","avatar":"","htmlavatar":"","uid":"10039","address":[]}
     */

    private int code;
    private String msg;
    private int status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * encrypt : l3sV5k
         * mobile : 15315361536
         * username : 15315361536
         * nickname : s1495882227314
         * email : 15315361536@9fat.com
         * password : 48ea34e2719f91e34c892bf7b88cc8b1
         * modelid : 10
         * regip : 61.149.242.31
         * point : 0
         * amount : 0
         * lastdate : 1495882227
         * regdate : 1495882227
         * siteid : 1
         * connectid :
         * from :
         * groupid : 2
         * phpssouid : 10039
         * userid : 10039
         * avatar :
         * htmlavatar :
         * uid : 10039
         * address : []
         */

        private String encrypt;
        private String mobile;
        private String username;
        private String nickname;
        private String email;
        private String password;
        private int modelid;
        private String regip;
        private int point;
        private int amount;
        private int lastdate;
        private int regdate;
        private int siteid;
        private String connectid;
        private String from;
        private int groupid;
        private String phpssouid;
        private String userid;
        private String avatar;
        private String htmlavatar;
        private String uid;
        private List<?> address;

        public String getEncrypt() {
            return encrypt;
        }

        public void setEncrypt(String encrypt) {
            this.encrypt = encrypt;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getModelid() {
            return modelid;
        }

        public void setModelid(int modelid) {
            this.modelid = modelid;
        }

        public String getRegip() {
            return regip;
        }

        public void setRegip(String regip) {
            this.regip = regip;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getLastdate() {
            return lastdate;
        }

        public void setLastdate(int lastdate) {
            this.lastdate = lastdate;
        }

        public int getRegdate() {
            return regdate;
        }

        public void setRegdate(int regdate) {
            this.regdate = regdate;
        }

        public int getSiteid() {
            return siteid;
        }

        public void setSiteid(int siteid) {
            this.siteid = siteid;
        }

        public String getConnectid() {
            return connectid;
        }

        public void setConnectid(String connectid) {
            this.connectid = connectid;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getPhpssouid() {
            return phpssouid;
        }

        public void setPhpssouid(String phpssouid) {
            this.phpssouid = phpssouid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getHtmlavatar() {
            return htmlavatar;
        }

        public void setHtmlavatar(String htmlavatar) {
            this.htmlavatar = htmlavatar;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public List<?> getAddress() {
            return address;
        }

        public void setAddress(List<?> address) {
            this.address = address;
        }
    }
}
