package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的仓库-收益
 */

public class Earnings implements Serializable {

    /**
     * code : 200
     * data : [{"foster":[{"income":{"content":"","creattime":"1500204500","fid":"3","id":"3","status":"5"},"name":"纯草农庄 无激素贵妃鸡","thumb":"http://jushizhibo.com/uploadfile/2017/0612/20170612042244425.jpg"}],"shopid":"2","shopname":"纯草农庄"},{"foster":[{"income":{"content":"","creattime":"1500204500","fid":"2","id":"4","status":"5"},"name":"香味果园 正玫瑰葡萄","thumb":"http://www.9fat.com/tmnzimage/gypt1.jpg"}],"shopid":"3","shopname":"香味果园"}]
     * msg : success
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * foster : [{"income":{"content":"","creattime":"1500204500","fid":"3","id":"3","status":"5"},"name":"纯草农庄 无激素贵妃鸡","thumb":"http://jushizhibo.com/uploadfile/2017/0612/20170612042244425.jpg"}]
         * shopid : 2
         * shopname : 纯草农庄
         */

        private String shopid;
        private String shopname;
        private List<FosterBean> foster;

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

        public List<FosterBean> getFoster() {
            return foster;
        }

        public void setFoster(List<FosterBean> foster) {
            this.foster = foster;
        }

        public static class FosterBean {
            /**
             * income : {"content":"","creattime":"1500204500","fid":"3","id":"3","status":"5"}
             * name : 纯草农庄 无激素贵妃鸡
             * thumb : http://jushizhibo.com/uploadfile/2017/0612/20170612042244425.jpg
             */

            private IncomeBean income;
            private String name;
            private String thumb;

            public IncomeBean getIncome() {
                return income;
            }

            public void setIncome(IncomeBean income) {
                this.income = income;
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

            public static class IncomeBean {
                /**
                 * content :
                 * creattime : 1500204500
                 * fid : 3
                 * id : 3
                 * status : 5
                 */

                private String content;
                private String creattime;
                private String fid;
                private String id;
                private String status;

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

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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
}
