package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的收益详情
 * Created by src on 2017/7/17.
 */

public class EarningsDetail implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"name":"纯草农庄 贵妃鸡代养","fostertime":"450天","income":[{"id":"3","username":"15111112222","gid":"7","fid":"3","content":"贵妃鸡蛋","count":"10","unit":"枚","creattime":"1500204500","edittime":"0","status":"7"},{"id":"5","username":"15111112222","gid":"7","fid":"3","content":"贵妃鸡蛋","count":"10","unit":"枚","creattime":"1500204500","edittime":"0","status":"5"}],"thumb":"http://www.9fat.com/tmnzimage/bjy1.jpg"}
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
         * name : 纯草农庄 贵妃鸡代养
         * fostertime : 450天
         * income : [{"id":"3","username":"15111112222","gid":"7","fid":"3","content":"贵妃鸡蛋","count":"10","unit":"枚","creattime":"1500204500","edittime":"0","status":"7"},{"id":"5","username":"15111112222","gid":"7","fid":"3","content":"贵妃鸡蛋","count":"10","unit":"枚","creattime":"1500204500","edittime":"0","status":"5"}]
         * thumb : http://www.9fat.com/tmnzimage/bjy1.jpg
         */

        private String name;
        private String fostertime;
        private String thumb;
        private List<IncomeBean> income;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFostertime() {
            return fostertime;
        }

        public void setFostertime(String fostertime) {
            this.fostertime = fostertime;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public List<IncomeBean> getIncome() {
            return income;
        }

        public void setIncome(List<IncomeBean> income) {
            this.income = income;
        }

        public static class IncomeBean {
            /**
             * id : 3
             * username : 15111112222
             * gid : 7
             * fid : 3
             * content : 贵妃鸡蛋
             * count : 10
             * unit : 枚
             * creattime : 1500204500
             * edittime : 0
             * status : 7
             */

            private String id;
            private String username;
            private String gid;
            private String fid;
            private String content;
            private String count;
            private String unit;
            private String creattime;
            private String edittime;
            private String status;

            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
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

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
            }

            public String getEdittime() {
                return edittime;
            }

            public void setEdittime(String edittime) {
                this.edittime = edittime;
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
