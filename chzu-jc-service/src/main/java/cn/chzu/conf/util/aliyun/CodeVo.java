package cn.chzu.conf.util.aliyun;

import cn.chzu.base.model.Prompt;

/**
 * @description: 发送短信回执
 * @author: zhu_hao
 * @date: Created in 2020/2/25 17:02
 * @version: 1.0.0
 * @modified By:
 */
public class CodeVo extends Prompt {

    //code
    private String code;

    //手机号码
    private String phone;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CodeVo{" +
                "code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
