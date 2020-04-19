package cn.chzu.buildingmaterials.salesworkflow.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 结单实体类，用来存放线下售货结单实体类
 * @author: zhu_hao
 * @date: Created in 2020/3/23 14:58
 * @version: 1.0.0
 * @modified By:
 */
public class Check extends Prompt {

    //id
    private String id;
    //销售商品名
    private String goodsName;
    //销售商品单价
    private String salesPrice;
    //销售商品数量
    private String count;
    //销售商品计量单位
    private String measure;
    //单个销售商品总价
    private String salesPriceSum;
    //员工账号id
    private String accountId;
    //图片url
    private String img;
    //商品id
    private String goodsId;
    //订单号
    private String orderNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getSalesPriceSum() {
        return salesPriceSum;
    }

    public void setSalesPriceSum(String salesPriceSum) {
        this.salesPriceSum = salesPriceSum;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id='" + id + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", salesPrice='" + salesPrice + '\'' +
                ", count='" + count + '\'' +
                ", measure='" + measure + '\'' +
                ", salesPriceSum='" + salesPriceSum + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
