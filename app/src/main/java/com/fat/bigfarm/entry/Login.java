package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 登录
 * Created by src on 2017/5/11.
 */

public class Login implements Serializable {


    /**
     * code : 200
     * msg : 欢迎您：15111112222，回来！
     * status : 1
     * data : {"userid":"10002","phpssouid":"10002","username":"15111112222","password":"08474ff581b7e609c60d4da3dfc9ee19","encrypt":"VIKB4C","nickname":"马里奥","regdate":"1494429475","lastdate":"1497597092","regip":"125.34.187.99","lastip":"114.244.159.226","loginnum":"0","email":"15111112222@9fat.com","groupid":"2","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","from":"","mobile":"15111112222","avatar":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg?_=1497598258","htmlavatar":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg","uid":"10002","address":[{"id":"32","uid":"10002","province":"北京市","city":"北京市","country":"东城区","detail":"嗯呀","zip_code":"0","linkman":"佛语","telnumber":"369","status":"2"}],"sex":"男","age":"0","birthday":"2017-06-16"}
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
         * userid : 10002
         * phpssouid : 10002
         * username : 15111112222
         * password : 08474ff581b7e609c60d4da3dfc9ee19
         * encrypt : VIKB4C
         * nickname : 马里奥
         * regdate : 1494429475
         * lastdate : 1497597092
         * regip : 125.34.187.99
         * lastip : 114.244.159.226
         * loginnum : 0
         * email : 15111112222@9fat.com
         * groupid : 2
         * areaid : 0
         * amount : 0.00
         * point : 0
         * modelid : 10
         * message : 0
         * islock : 0
         * vip : 0
         * overduedate : 0
         * siteid : 1
         * connectid :
         * from :
         * mobile : 15111112222
         * avatar : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg?_=1497598258
         * htmlavatar : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg
         * uid : 10002
         * address : [{"id":"32","uid":"10002","province":"北京市","city":"北京市","country":"东城区","detail":"嗯呀","zip_code":"0","linkman":"佛语","telnumber":"369","status":"2"}]
         * sex : 男
         * age : 0
         * birthday : 2017-06-16
         */

        private String userid;
        private String phpssouid;
        private String username;
        private String password;
        private String encrypt;
        private String nickname;
        private String regdate;
        private String lastdate;
        private String regip;
        private String lastip;
        private String loginnum;
        private String email;
        private String groupid;
        private String areaid;
        private String amount;
        private String point;
        private String modelid;
        private String message;
        private String islock;
        private String vip;
        private String overduedate;
        private String siteid;
        private String connectid;
        private String from;
        private String mobile;
        private String avatar;
        private String htmlavatar;
        private String uid;
        private String sex;
        private String age;
        private String birthday;
        private List<AddressBean> address;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPhpssouid() {
            return phpssouid;
        }

        public void setPhpssouid(String phpssouid) {
            this.phpssouid = phpssouid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEncrypt() {
            return encrypt;
        }

        public void setEncrypt(String encrypt) {
            this.encrypt = encrypt;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRegdate() {
            return regdate;
        }

        public void setRegdate(String regdate) {
            this.regdate = regdate;
        }

        public String getLastdate() {
            return lastdate;
        }

        public void setLastdate(String lastdate) {
            this.lastdate = lastdate;
        }

        public String getRegip() {
            return regip;
        }

        public void setRegip(String regip) {
            this.regip = regip;
        }

        public String getLastip() {
            return lastip;
        }

        public void setLastip(String lastip) {
            this.lastip = lastip;
        }

        public String getLoginnum() {
            return loginnum;
        }

        public void setLoginnum(String loginnum) {
            this.loginnum = loginnum;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getModelid() {
            return modelid;
        }

        public void setModelid(String modelid) {
            this.modelid = modelid;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getIslock() {
            return islock;
        }

        public void setIslock(String islock) {
            this.islock = islock;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getOverduedate() {
            return overduedate;
        }

        public void setOverduedate(String overduedate) {
            this.overduedate = overduedate;
        }

        public String getSiteid() {
            return siteid;
        }

        public void setSiteid(String siteid) {
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class AddressBean {
            /**
             * id : 32
             * uid : 10002
             * province : 北京市
             * city : 北京市
             * country : 东城区
             * detail : 嗯呀
             * zip_code : 0
             * linkman : 佛语
             * telnumber : 369
             * status : 2
             */

            private String id;
            private String uid;
            private String province;
            private String city;
            private String country;
            private String detail;
            private String zip_code;
            private String linkman;
            private String telnumber;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

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

            public String getZip_code() {
                return zip_code;
            }

            public void setZip_code(String zip_code) {
                this.zip_code = zip_code;
            }

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }

            public String getTelnumber() {
                return telnumber;
            }

            public void setTelnumber(String telnumber) {
                this.telnumber = telnumber;
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
