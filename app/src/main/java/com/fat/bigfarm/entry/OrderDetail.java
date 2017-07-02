package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情
 * Created by src on 2017/6/17.
 */

public class OrderDetail implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : {"orderinfo":{"id":"560","number":"T1498122915860","status":"1","price":"9","freight":"2","pay_way":"","creattime":"1498122915"},"addressinfo":{"id":"28","uid":"10002","province":"北京市","city":"北京市","country":"东城区","detail":"新中西里可口可乐了龙图腾","zip_code":"0","linkman":"莫名","telnumber":"15112341234","status":"2"},"shopid":"4","shopname":"农家乐","goodsinfo":[{"id":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"3","unit":"个","count":"3"}]}
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
         * orderinfo : {"id":"560","number":"T1498122915860","status":"1","price":"9","freight":"2","pay_way":"","creattime":"1498122915"}
         * addressinfo : {"id":"28","uid":"10002","province":"北京市","city":"北京市","country":"东城区","detail":"新中西里可口可乐了龙图腾","zip_code":"0","linkman":"莫名","telnumber":"15112341234","status":"2"}
         * shopid : 4
         * shopname : 农家乐
         * goodsinfo : [{"id":"8","name":"测试33","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","price":"3","unit":"个","count":"3"}]
         */

        private OrderinfoBean orderinfo;
        private AddressinfoBean addressinfo;
        private String shopid;
        private String shopname;
        private List<GoodsinfoBean> goodsinfo;

        public OrderinfoBean getOrderinfo() {
            return orderinfo;
        }

        public void setOrderinfo(OrderinfoBean orderinfo) {
            this.orderinfo = orderinfo;
        }

        public AddressinfoBean getAddressinfo() {
            return addressinfo;
        }

        public void setAddressinfo(AddressinfoBean addressinfo) {
            this.addressinfo = addressinfo;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public List<GoodsinfoBean> getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(List<GoodsinfoBean> goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public static class OrderinfoBean {
            /**
             * id : 560
             * number : T1498122915860
             * status : 1
             * price : 9
             * freight : 2
             * pay_way :
             * creattime : 1498122915
             */

            private String id;
            private String number;
            private String status;
            private String price;
            private String freight;
            private String pay_way;
            private String creattime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getPay_way() {
                return pay_way;
            }

            public void setPay_way(String pay_way) {
                this.pay_way = pay_way;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
            }
        }

        public static class AddressinfoBean {
            /**
             * id : 28
             * uid : 10002
             * province : 北京市
             * city : 北京市
             * country : 东城区
             * detail : 新中西里可口可乐了龙图腾
             * zip_code : 0
             * linkman : 莫名
             * telnumber : 15112341234
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

        public static class GoodsinfoBean {
            /**
             * id : 8
             * name : 测试33
             * thumb : http://www.kpano.com/kpano/images/jy3.jpg
             * price : 3
             * unit : 个
             * count : 3
             */

            private String id;
            private String name;
            private String thumb;
            private String price;
            private String unit;
            private String count;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}
