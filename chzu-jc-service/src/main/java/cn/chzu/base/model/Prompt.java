package cn.chzu.base.model;

import java.io.Serializable;

/**
 * @description: 信息提示
 * @author: zhu_hao
 * @date: Created in 2020/1/31 14:07
 * @version: 1.0.0
 * @modified By:
 */
public abstract class Prompt implements Serializable {
    //信息提示
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Prompt{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
