package com.fat.bigfarm.entry;

import java.io.Serializable;

/**
 * Created by src on 2017/6/17.
 */

public class Activities implements Serializable {


    /**
     * code : 200
     * data : {"action1":{"des":"","id":"1","name":"周六半价","picture":"http://www.9fat.com/tmnzimage/zlbj@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/zlbj@2x.png"},"action2":{"des":"","id":"2","name":"29.9元专享","picture":"http://www.9fat.com/tmnzimage/zx@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/zx@2x.png"},"action3":{"des":"","id":"3","name":"超值折扣","picture":"http://www.9fat.com/tmnzimage/czzk@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/czzk@2x.png"},"action4":{"des":"","id":"4","name":"口碑排行","picture":"http://www.9fat.com/tmnzimage/kbph@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/kbph@2x.png"}}
     * head_pic : http://www.9fat.com/tmnzimage/action.gif
     * msg : success
     */

    private int code;
    private DataBean data;
    private String head_pic;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * action1 : {"des":"","id":"1","name":"周六半价","picture":"http://www.9fat.com/tmnzimage/zlbj@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/zlbj@2x.png"}
         * action2 : {"des":"","id":"2","name":"29.9元专享","picture":"http://www.9fat.com/tmnzimage/zx@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/zx@2x.png"}
         * action3 : {"des":"","id":"3","name":"超值折扣","picture":"http://www.9fat.com/tmnzimage/czzk@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/czzk@2x.png"}
         * action4 : {"des":"","id":"4","name":"口碑排行","picture":"http://www.9fat.com/tmnzimage/kbph@2x.png","status":"1","thumb":"http://www.9fat.com/tmnzimage/kbph@2x.png"}
         */

        private Action1Bean action1;
        private Action2Bean action2;
        private Action3Bean action3;
        private Action4Bean action4;

        public Action1Bean getAction1() {
            return action1;
        }

        public void setAction1(Action1Bean action1) {
            this.action1 = action1;
        }

        public Action2Bean getAction2() {
            return action2;
        }

        public void setAction2(Action2Bean action2) {
            this.action2 = action2;
        }

        public Action3Bean getAction3() {
            return action3;
        }

        public void setAction3(Action3Bean action3) {
            this.action3 = action3;
        }

        public Action4Bean getAction4() {
            return action4;
        }

        public void setAction4(Action4Bean action4) {
            this.action4 = action4;
        }

        public static class Action1Bean {
            /**
             * des :
             * id : 1
             * name : 周六半价
             * picture : http://www.9fat.com/tmnzimage/zlbj@2x.png
             * status : 1
             * thumb : http://www.9fat.com/tmnzimage/zlbj@2x.png
             */

            private String des;
            private String id;
            private String name;
            private String picture;
            private String status;
            private String thumb;

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class Action2Bean {
            /**
             * des :
             * id : 2
             * name : 29.9元专享
             * picture : http://www.9fat.com/tmnzimage/zx@2x.png
             * status : 1
             * thumb : http://www.9fat.com/tmnzimage/zx@2x.png
             */

            private String des;
            private String id;
            private String name;
            private String picture;
            private String status;
            private String thumb;

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class Action3Bean {
            /**
             * des :
             * id : 3
             * name : 超值折扣
             * picture : http://www.9fat.com/tmnzimage/czzk@2x.png
             * status : 1
             * thumb : http://www.9fat.com/tmnzimage/czzk@2x.png
             */

            private String des;
            private String id;
            private String name;
            private String picture;
            private String status;
            private String thumb;

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class Action4Bean {
            /**
             * des :
             * id : 4
             * name : 口碑排行
             * picture : http://www.9fat.com/tmnzimage/kbph@2x.png
             * status : 1
             * thumb : http://www.9fat.com/tmnzimage/kbph@2x.png
             */

            private String des;
            private String id;
            private String name;
            private String picture;
            private String status;
            private String thumb;

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }
}
