package com.fat.bigfarm.entry;

import java.io.Serializable;

/**
 * Created by src on 2017/5/26.
 */

public class DeleteCart implements Serializable {

    /**
     * code : 200
     * msg : success
     */

    private int code;
    private String msg;

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
}
