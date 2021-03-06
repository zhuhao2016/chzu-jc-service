package cn.chzu.buildingmaterials.order.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 订单数据访问对象
 * @author: zhu_hao
 * @date: Created in 2020/3/6 15:09
 * @version: 1.0.0
 * @modified By:
 */
public class OrderDTO extends Prompt {
    //唯一id
    private String id;
    //用户账号唯一id
    private String accountId;
    //购物车商品的每个id
    private String shoppingId;
    //图片url
    private String img;
    //商品名
    private String goodsName;
    //商品价格
    private String salesPrice;
    //商品总价
    private String totalPrice;
    //数量
    private String counts;
    //商品id
    private String goodsId;
    //商品来源
    private String storeName;
    //收货人姓名
    private String userName;
    //收货人电话
    private String telNumber;
    //收货地址
    private String address;
    //收货人邮编
    private String postalCode;
    //下单时间
    private String createTime;
    //买家留言
    private String leaveMessage;
    //订单状态
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(String shoppingId) {
        this.shoppingId = shoppingId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", shoppingId='" + shoppingId + '\'' +
                ", img='" + img + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", salesPrice='" + salesPrice + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", counts='" + counts + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", userName='" + userName + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", leaveMessage='" + leaveMessage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
