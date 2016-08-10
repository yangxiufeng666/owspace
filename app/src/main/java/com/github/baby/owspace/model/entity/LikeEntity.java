package com.github.baby.owspace.model.entity;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/9
 * owspace
 */
public class LikeEntity {

    /**
     * status : ok
     * datas : [{"uid":"320188","avatar":"http://img.owspace.com//@/avatar/2016-08-09/63ebc0de7eb81c64ac5ddae0346cf2adf3"},{"uid":"3153","avatar":"http://img.owspace.com/Public/uploads/2016/03/3153_1459391408424.jpg"},{"uid":"282014","avatar":"http://img.owspace.com//@/avatar/2016-07-11/7571d8e085644231481e31b65fda613fea"},{"uid":"214817","avatar":"http://img.owspace.com//@/avatar/2016-04-15/51344eaacc21bae049402cc6daf01a7eed"},{"uid":"46352","avatar":"http://img.owspace.com/Public/uploads/2016/08/46352_1470703221233.jpg"},{"uid":"319400","avatar":"http://img.owspace.com//@/avatar/2016-08-08/72ca00f8e357d31426d07047a479a361cd"}]
     * msg :
     * code : 0
     */

    private String status;
    private String msg;
    private int code;
    /**
     * uid : 320188
     * avatar : http://img.owspace.com//@/avatar/2016-08-09/63ebc0de7eb81c64ac5ddae0346cf2adf3
     */

    private List<DatasBean> datas;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String uid;
        private String avatar;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
