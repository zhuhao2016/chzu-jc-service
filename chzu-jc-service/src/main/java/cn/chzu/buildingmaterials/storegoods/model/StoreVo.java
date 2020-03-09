package cn.chzu.buildingmaterials.storegoods.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 小程序商品分类点击详情
 * @author: zhu_hao
 * @date: Created in 2020/2/24 17:01
 * @version: 1.0.0
 * @modified By:
 */
public class StoreVo extends Prompt {

    //商品编号
    private String id;

    //商品图片
    private String img;

    //商品名
    private String goodsName;

    //商品价格
    private String salesPrice;

    //商品来源
    private String storeName;

    //剩余库存
    private String salesNumber;

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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    @Override
    public String toString() {
        return "StoreVo{" +
                "id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", salesPrice='" + salesPrice + '\'' +
                ", storeName='" + storeName + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                '}';
    }
}
