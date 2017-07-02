package com.fat.bigfarm.entry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 提交订单
 * Created by src on 2017/5/24.
 */

public class PushOrder implements Serializable {


    /**
     * code : 200
     * msg : success
     * order : {"appid":"wx791e5c84162d83bb","mch_id":"1481983672","nonce_str":"Vm6I0n5nnIYVluza","out_trade_no":"T1498552368328x1","package":"Sign=WXPay","prepay_id":"wx20170627163329f1a7e4f09b0607242734","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"C10B9562BD76C85414F8AF90895F4CE8","timestamp":1498552396,"trade_type":"APP"}
     */

    private int code;
    private String msg;
    private OrderBean order;

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

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * appid : wx791e5c84162d83bb
         * mch_id : 1481983672
         * nonce_str : Vm6I0n5nnIYVluza
         * out_trade_no : T1498552368328x1
         * package : Sign=WXPay
         * prepay_id : wx20170627163329f1a7e4f09b0607242734
         * result_code : SUCCESS
         * return_code : SUCCESS
         * return_msg : OK
         * sign : C10B9562BD76C85414F8AF90895F4CE8
         * timestamp : 1498552396
         * trade_type : APP
         */

        private String appid;
        private String mch_id;
        private String nonce_str;
        private String out_trade_no;
        @SerializedName("package")
        private String packageX;
        private String prepay_id;
        private String result_code;
        private String return_code;
        private String return_msg;
        private String sign;
        private int timestamp;
        private String trade_type;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }
    }
}
