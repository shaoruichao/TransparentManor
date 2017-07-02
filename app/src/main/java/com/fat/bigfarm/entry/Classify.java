package com.fat.bigfarm.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 分类
 * Created by src on 2017/5/10.
 */

public class Classify implements Serializable{


    /**
     * code : 200
     * msg : success
     * data : [{"id":"1","name":"云农场","logo":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png"},{"id":"2","name":"纯草农庄","logo":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png"},{"id":"3","name":"农机农场","logo":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png"},{"id":"4","name":"农家乐","logo":"http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png"}]
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
         * name : 云农场
         * logo : http://www.9fat.com/asimage/lALOz33Px1BQ_80_80.png
         */

        private String id;
        private String name;
        private String logo;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
