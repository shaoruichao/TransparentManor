package com.fat.bigfarm.entry;

import java.io.Serializable;

/**
 * 收益自留
 * Created by src on 2017/6/17.
 */

public class Retention implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : {"shopid":"2","shopname":"纯草农庄","foster":{"name":"纯草农庄 北京鸭代养","thumb":"http://www.9fat.com/tmnzimage/bjy1.jpg","fostertime":"450天","creattime":"1499936144","content":"北京鸭蛋","price":"100.00","unit":"只","income":{"name":"贵妃鸡蛋","count":"10","unit":"枚"}}}
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
         * shopid : 2
         * shopname : 纯草农庄
         * foster : {"name":"纯草农庄 北京鸭代养","thumb":"http://www.9fat.com/tmnzimage/bjy1.jpg","fostertime":"450天","creattime":"1499936144","content":"北京鸭蛋","price":"100.00","unit":"只","income":{"name":"贵妃鸡蛋","count":"10","unit":"枚"}}
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
             * name : 纯草农庄 北京鸭代养
             * thumb : http://www.9fat.com/tmnzimage/bjy1.jpg
             * fostertime : 450天
             * creattime : 1499936144
             * content : 北京鸭蛋
             * price : 100.00
             * unit : 只
             * income : {"name":"贵妃鸡蛋","count":"10","unit":"枚"}
             */

            private String name;
            private String thumb;
            private String fostertime;
            private String creattime;
            private String content;
            private String price;
            private String unit;
            private IncomeBean income;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public IncomeBean getIncome() {
                return income;
            }

            public void setIncome(IncomeBean income) {
                this.income = income;
            }

            public static class IncomeBean {
                /**
                 * name : 贵妃鸡蛋
                 * count : 10
                 * unit : 枚
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
