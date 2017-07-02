package com.fat.bigfarm.entry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by src on 2017/5/11.
 */

public class HeadImage implements Serializable {


    /**
     * avatar : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg?_=1496221222
     * code : 200
     * htmlavatar : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/src.jpg
     * thumb : {"180":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/180x180.jpg?_=1496221222","30":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/30x30.jpg?_=1496221222","45":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/45x45.jpg?_=1496221222","90":"http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/90x90.jpg?_=1496221222"}
     */

    private String avatar;
    private int code;
    private String htmlavatar;
    private ThumbBean thumb;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHtmlavatar() {
        return htmlavatar;
    }

    public void setHtmlavatar(String htmlavatar) {
        this.htmlavatar = htmlavatar;
    }

    public ThumbBean getThumb() {
        return thumb;
    }

    public void setThumb(ThumbBean thumb) {
        this.thumb = thumb;
    }

    public static class ThumbBean {
        /**
         * 180 : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/180x180.jpg?_=1496221222
         * 30 : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/30x30.jpg?_=1496221222
         * 45 : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/45x45.jpg?_=1496221222
         * 90 : http://www.9fat.com/phpsso_server/uploadfile/avatar/2/1/10002/90x90.jpg?_=1496221222
         */

        @SerializedName("180")
        private String value180;
        @SerializedName("30")
        private String value30;
        @SerializedName("45")
        private String value45;
        @SerializedName("90")
        private String value90;

        public String getValue180() {
            return value180;
        }

        public void setValue180(String value180) {
            this.value180 = value180;
        }

        public String getValue30() {
            return value30;
        }

        public void setValue30(String value30) {
            this.value30 = value30;
        }

        public String getValue45() {
            return value45;
        }

        public void setValue45(String value45) {
            this.value45 = value45;
        }

        public String getValue90() {
            return value90;
        }

        public void setValue90(String value90) {
            this.value90 = value90;
        }
    }
}
