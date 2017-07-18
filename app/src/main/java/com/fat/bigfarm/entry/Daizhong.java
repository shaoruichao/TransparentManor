package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的仓库-代种
 * Created by src on 2017/6/17.
 */

public class Daizhong implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : [{"shopid":"3","shopname":"香味果园","foster":[{"name":"香味果园 正玫瑰葡萄代养","thumb":"http://www.9fat.com/tmnzimage/gypt1.jpg","fostertime":"100天","gid":"10","creattime":"1499936144","cycle":"7天","content":"时令耕作物","count":"1","status":"1"}]}]
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
         * shopid : 3
         * shopname : 香味果园
         * foster : [{"name":"香味果园 正玫瑰葡萄代养","thumb":"http://www.9fat.com/tmnzimage/gypt1.jpg","fostertime":"100天","gid":"10","creattime":"1499936144","cycle":"7天","content":"时令耕作物","count":"1","status":"1"}]
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
             * name : 香味果园 正玫瑰葡萄代养
             * thumb : http://www.9fat.com/tmnzimage/gypt1.jpg
             * fostertime : 100天
             * gid : 10
             * creattime : 1499936144
             * cycle : 7天
             * content : 时令耕作物
             * count : 1
             * status : 1
             */

            private String name;
            private String thumb;
            private String fostertime;
            private String gid;
            private String creattime;
            private String cycle;
            private String content;
            private String count;
            private String status;

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

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
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
        }
    }
}
