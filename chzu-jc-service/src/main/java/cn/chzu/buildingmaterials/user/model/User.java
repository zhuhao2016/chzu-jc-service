package cn.chzu.buildingmaterials.user.model;

import cn.chzu.base.model.Prompt;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 16:47
 * @version: 1.0.0
 * @modified By:
 */
public class User extends Prompt {
    //唯一id
    private String id;
    //用户名
    private String username;
    //用户密码
    private String password;
    //所属店铺编号
    private String classification;
    //电话号码
    private String phoneNumber;
    //性别
    private String sex;
    //角色
    private String roleId;
    //用户创建时间
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", classification='" + classification + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", roleId='" + roleId + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
