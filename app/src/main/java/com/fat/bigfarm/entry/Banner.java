package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 轮播图
 * Created by src on 2017/5/10.
 */

public class Banner implements Serializable{


    /**
     * code : 200
     * msg : success
     * data : [{"id":"1","name":"测试1","address":"http://www.kpano.com/kpano/images/jy3.jpg","status":"1"},{"id":"2","name":"测试2","address":"http://www.kpano.com/kpano/images/jy3.jpg","status":"1"},{"id":"3","name":"测试3","address":"http://www.kpano.com/kpano/images/jy3.jpg","status":"1"},{"id":"4","name":"测试4","address":"http://www.kpano.com/kpano/images/jy3.jpg","status":"1"}]
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
         * id : 1
         * name : 测试1
         * address : http://www.kpano.com/kpano/images/jy3.jpg
         * status : 1
         */

        private String id;
        private String name;
        private String address;
        private String status;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
