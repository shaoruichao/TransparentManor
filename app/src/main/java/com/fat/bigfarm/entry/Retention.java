package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 收益自留
 * Created by src on 2017/6/17.
 */

public class Retention implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : {"shopid":"1","shopname":"云农场","foster":{"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498552651","cycle":"15","content":"鹿茸*5盒","count":"1","status":"1","price":"2","unit":"个","income":[{"name":"鹿茸","count":"5","unit":"盒"}]}}
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
         * shopid : 1
         * shopname : 云农场
         * foster : {"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498552651","cycle":"15","content":"鹿茸*5盒","count":"1","status":"1","price":"2","unit":"个","income":[{"name":"鹿茸","count":"5","unit":"盒"}]}
         */

        private String shopid;
        private String shopname;
        private FosterBean foster;

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

        public FosterBean getFoster() {
            return foster;
        }

        public void setFoster(FosterBean foster) {
            this.foster = foster;
        }

        public static class FosterBean {
            /**
             * name : 测试1123123
             * thumb : http://www.kpano.com/kpano/images/jy1.jpg
             * fostertime : 1年
             * creattime : 1498552651
             * cycle : 15
             * content : 鹿茸*5盒
             * count : 1
             * status : 1
             * price : 2
             * unit : 个
             * income : [{"name":"鹿茸","count":"5","unit":"盒"}]
             */

            private String name;
            private String thumb;
            private String fostertime;
            private String creattime;
            private String cycle;
            private String content;
            private String count;
            private String status;
            private String price;
            private String unit;
            private List<IncomeBean> income;

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

            public String getFostertime() {
                return fostertime;
            }

            public void setFostertime(String fostertime) {
                this.fostertime = fostertime;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
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

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public List<IncomeBean> getIncome() {
                return income;
            }

            public void setIncome(List<IncomeBean> income) {
                this.income = income;
            }

            public static class IncomeBean {
                /**
                 * name : 鹿茸
                 * count : 5
                 * unit : 盒
                 */

                private String name;
                private String count;
                private String unit;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }
            }
        }
    }
}
