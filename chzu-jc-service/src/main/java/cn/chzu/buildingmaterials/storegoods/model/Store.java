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
    private String kind;
    //计量单位
    private String measure;
    //派发数量
    private String cont;
    //商品图片
    private String image;
    //商品推荐优先级
    private String priority;
    //逻辑删除
    private String logic;

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
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
                ", kind='" + kind + '\'' +
                ", measure='" + measure + '\'' +
                ", cont='" + cont + '\'' +
                ", image='" + image + '\'' +
                ", priority='" + priority + '\'' +
                ", logic='" + logic + '\'' +
                '}';
    }
}