package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的仓库-代养
 * Created by src on 2017/6/17.
 */

public class Raise implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : [{"shopid":"1","shopname":"云农场","foster":[{"name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"180天","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"1","status":"1"},{"name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"9","status":"1"},{"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"15","content":"鹿茸*5盒","count":"4","status":"1"},{"name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"180天","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"1","status":"1"},{"name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"9","status":"1"},{"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"15","content":"鹿茸*5盒","count":"4","status":"1"},{"name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"180天","creattime":"1498386281","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"1","status":"1"},{"name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498386281","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"9","status":"1"},{"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498386281","cycle":"15","content":"鹿茸*5盒","count":"4","status":"1"}]}]
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
         * shopid : 1
         * shopname : 云农场
         * foster : [{"name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"180天","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"1","status":"1"},{"name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"9","status":"1"},{"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"15","content":"鹿茸*5盒","count":"4","status":"1"},{"name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"180天","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"1","status":"1"},{"name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"9","status":"1"},{"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498384508","cycle":"15","content":"鹿茸*5盒","count":"4","status":"1"},{"name":"测试123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"180天","creattime":"1498386281","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"1","status":"1"},{"name":"测试1","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498386281","cycle":"7","content":"贵妃鸡*1，鸡蛋*10","count":"9","status":"1"},{"name":"测试1123123","thumb":"http://www.kpano.com/kpano/images/jy1.jpg","fostertime":"1年","creattime":"1498386281","cycle":"15","content":"鹿茸*5盒","count":"4","status":"1"}]
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
             * name : 测试123
             * thumb : http://www.kpano.com/kpano/images/jy1.jpg
             * fostertime : 180天
             * creattime : 1498384508
             * cycle : 7
             * content : 贵妃鸡*1，鸡蛋*10
             * count : 1
             * status : 1
             */

            private String name;
            private String thumb;
            private String fostertime;
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
