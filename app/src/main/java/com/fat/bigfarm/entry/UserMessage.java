package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * Created by src on 2017/5/12.
 */

public class UserMessage implements Serializable {


    /**
     * status : 1
     * data : {"uid":"10002","userid":"10002","userName":"15111112222","nickname":"莫言","mobile":null,"sex":"女","age":"0","birthday":"2000-12-01","email":"15111112222@9fat.com","avatar":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg?_=1497950015","htmlavatar":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg","address":[{"id":"32","uid":"10002","province":"北京市","city":"北京市","country":"东城区","detail":"嗯呀","zip_code":"0","linkman":"佛语","telnumber":"369","status":"2"}]}
     */

    private int status;
    private DataBean data;

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

    public static class DataBean implements Serializable{
        /**
         * uid : 10002
         * userid : 10002
         * userName : 15111112222
         * nickname : 莫言
         * mobile : null
         * sex : 女
         * age : 0
         * birthday : 2000-12-01
         * email : 15111112222@9fat.com
         * avatar : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg?_=1497950015
         * htmlavatar : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg
         * address : [{"id":"32","uid":"10002","province":"北京市","city":"北京市","country":"东城区","detail":"嗯呀","zip_code":"0","linkman":"佛语","telnumber":"369","status":"2"}]
         */

        private String uid;
        private String userid;
        private String userName;
        private String nickname;
        private Object mobile;
        private String sex;
        private String age;
        private String birthday;
        private String email;
        private String avatar;
        private String htmlavatar;
        private List<AddressBean> address;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class AddressBean implements Serializable {
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
