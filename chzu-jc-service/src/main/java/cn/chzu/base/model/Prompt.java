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

    //模糊查询字段
    private String links;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Prompt{" +
                "msg='" + msg + '\'' +
                ", links='" + links + '\'' +
                '}';
    }
}
