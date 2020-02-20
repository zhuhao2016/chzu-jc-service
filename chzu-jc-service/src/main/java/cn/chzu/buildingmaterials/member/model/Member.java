package cn.chzu.buildingmaterials.member.model;

import cn.chzu.base.model.Prompt;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/15 13:59
 * @version: 1.0.0
 * @modified By:
 */
public class Member extends Prompt {

    //唯一id
    private String id;
    //会员名称
    private String vipName;
    //性别
    private String sex;
    //出生年月
    private String age;
    //联系电话
    private String number;
    //创建时间
    private String createAt;
    //注册门店
    private String shopStore;
    //会员启用状态
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getShopStore() {
        return shopStore;
    }

    public void setShopStore(String shopStore) {
        this.shopStore = shopStore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", vipName='" + vipName + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", number='" + number + '\'' +
                ", createAt='" + createAt + '\'' +
                ", shopStore='" + shopStore + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
