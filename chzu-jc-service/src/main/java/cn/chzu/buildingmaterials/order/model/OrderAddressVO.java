package cn.chzu.buildingmaterials.order.model;

import cn.chzu.base.model.Prompt;

import java.util.List;

/**
 * @description: 订单地址信息和订单信息传递视图
 * @author: zhu_hao
 * @date: Created in 2020/3/6 14:21
 * @version: 1.0.0
 * @modified By:
 */
public class OrderAddressVO extends Prompt {

    //用户账号唯一id
    private String accountId;
    //收货人姓名
    private String userName;
    //收货人电话
    private String telNumber;
    //收货地址
    private String all;
    //收货人邮编
    private String postalCode;
    //封装商品订单信息
    private List<Order> order;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderAddressVO{" +
                "accountId='" + accountId + '\'' +
                ", userName='" + userName + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", all='" + all + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", order=" + order +
                '}';
    }
}
