package cn.chzu.buildingmaterials.wxuser.model;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/6 16:35
 * @version: 1.0.0
 * @modified By:
 */
public class WxUserVO {

    //微信用户账号
    private String nickName;
    //微信用户性别
    private String gender;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "WxUserVO{" +
                "nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
