package cn.chzu.buildingmaterials.storegoods.model;

import cn.chzu.base.model.Prompt;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/31 12:42
 * @version: 1.0.0
 * @modified By:
 */
public class Store extends Prompt {

    //唯一id
    private String id;
    //门店名称
    private String storeName;
    //商品名称
    private String goodsName;
    //商品库存
    private String salesNumber;
    //进价
    private String price;
    //售价
    private String salesPrice;
    //商品简介
    private String remarks;
    //商品类别
    private String classification;

    //计量单位
    private String measure;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", storeName='" + storeName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                ", price='" + price + '\'' +
                ", salesPrice='" + salesPrice + '\'' +
                ", remarks='" + remarks + '\'' +
                ", classification='" + classification + '\'' +
                ", measure='" + measure + '\'' +
                '}';
    }
}