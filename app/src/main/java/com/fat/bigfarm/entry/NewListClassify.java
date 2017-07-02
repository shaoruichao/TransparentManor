package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 新品列表分类
 * Created by src on 2017/5/10.
 */

public class NewListClassify implements Serializable{


    /**
     * code : 200
     * msg : success
     * data : [{"id":"1","name":"代养套餐","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","icon":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png","creattime":"0","status":"1"},{"id":"2","name":"高端定制","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","icon":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png","creattime":"0","status":"1"},{"id":"3","name":"肉禽蛋类","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","icon":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png","creattime":"0","status":"1"},{"id":"4","name":"瓜果时蔬","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","icon":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png","creattime":"0","status":"1"},{"id":"5","name":"收益专卖","thumb":"http://www.kpano.com/kpano/images/jy3.jpg","icon":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png","creattime":"0","status":"1"}]
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
         * name : 代养套餐
         * thumb : http://www.kpano.com/kpano/images/jy3.jpg
         * icon : http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png
         * creattime : 0
         * status : 1
         */

        private String id;
        private String name;
        private String thumb;
        private String icon;
        private String creattime;
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

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
