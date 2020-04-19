package cn.chzu.buildingmaterials.manage.model;

/**
 * @description: 经营概括
 * @author: zhu_hao
 * @date: Created in 2020/3/9 13:36
 * @version: 1.0.0
 * @modified By:
 */
public class Manage {

    //客户总数量
    private int clientNumber;

    //订单总数
    private int orderNumber;

    //订单总金额
    private int orderTotalAmount;

    //现有商品总数
    private int goodsNumber;

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(int orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    @Override
    public String toString() {
        return "Manage{" +
                "clientNumber=" + clientNumber +
                ", orderNumber=" + orderNumber +
                ", orderTotalAmount=" + orderTotalAmount +
                ", goodsNumber=" + goodsNumber +
                '}';
    }
}
