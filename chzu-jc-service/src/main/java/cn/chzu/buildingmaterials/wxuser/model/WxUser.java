package cn.chzu.buildingmaterials.wxuser.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 微信用户实体类
 * @author: zhu_hao
 * @date: Created in 2020/2/25 13:11
 * @version: 1.0.0
 * @modified By:
 */
public class WxUser extends Prompt {
    //唯一id
    private String id;
    //账号
    private String userName;
    //用户密码
    private String passWord;
    //手机号码
    private String phone;
    //用户创建时间
    private String created;
    //用户修改信息时间
    private String updated;
    //使用状态（Y正常 N非正常）
    private String status;
    //是否验证手机号
    private String isMobileCheck;
    //性别
    private String sex;
    //绑定会员id
    private String memberId;
    //出生年月日
    private String birthday;
    //最后登录时间
    private String lastLoginTime;
    //购物车id
    private String shoppingCartId;
    //登录方式,0账号登录，1，微信授权登录
    private String loginType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsMobileCheck() {
        return isMobileCheck;
    }

    public void setIsMobileCheck(String isMobileCheck) {
        this.isMobileCheck = isMobileCheck;
    }



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        return "WxUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", status='" + status + '\'' +
                ", isMobileCheck='" + isMobileCheck + '\'' +
                ", sex='" + sex + '\'' +
                ", memberId='" + memberId + '\'' +
                ", birthday='" + birthday + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", shoppingCartId='" + shoppingCartId + '\'' +
                ", loginType='" + loginType + '\'' +
                '}';
    }
}
