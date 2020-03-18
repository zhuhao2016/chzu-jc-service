package cn.chzu.buildingmaterials.shoppingcart.model;

import cn.chzu.base.model.Prompt;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/29 13:42
 * @version: 1.0.0
 * @modified By:
 */
public class ShoppingCart extends Prompt {

    //唯一id
    private String id;
    //唯一购物车id
    private String shoppingCartId;
    //用户账号id
    private String accountId;
    //图片url
    private String img;
    //商品名
    private String goodsName;
    //商品价格
    private String salesPrice;
    //数量
    private String counts;
    //商品id
    private String goodsId;
    //商品来源
    private String storeName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id='" + id + '\'' +
                ", shoppingCartId='" + shoppingCartId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", img='" + img + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", salesPrice='" + salesPrice + '\'' +
                ", counts='" + counts + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}
