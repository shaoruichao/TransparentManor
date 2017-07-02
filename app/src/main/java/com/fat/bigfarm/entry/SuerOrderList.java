package com.fat.bigfarm.entry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by src on 2017/5/24.
 */

public class SuerOrderList implements Serializable {


    /**
     * goodsinfo : {"4":1}
     * sid : 4
     * freight : 10
     */

    private GoodsinfoBean goodsinfo;
    private int sid;
    private int freight;

    public GoodsinfoBean getGoodsinfo() {
        return goodsinfo;
    }

    public void setGoodsinfo(GoodsinfoBean goodsinfo) {
        this.goodsinfo = goodsinfo;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public static class GoodsinfoBean {
        /**
         * 4 : 1
         */

        @SerializedName("4")
        private int value4;

        public int getValue4() {
            return value4;
        }

        public void setValue4(int value4) {
            this.value4 = value4;
        }
    }
}
