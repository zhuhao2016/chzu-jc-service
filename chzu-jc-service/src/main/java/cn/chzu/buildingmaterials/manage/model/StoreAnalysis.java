package cn.chzu.buildingmaterials.manage.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 店铺分析
 * @author: zhu_hao
 * @date: Created in 2020/3/9 15:45
 * @version: 1.0.0
 * @modified By:
 */
public class StoreAnalysis extends Prompt {

    //店铺名称
    private String storeName;
    //店铺对应订单数量
    private int orderNumber;
    //店铺对应销售总金额
    private double salesAmount;
    //店铺销售毛利润
    private double drossProfit;
    //客单价
    private double customerPrice;
    //每天时间
    private String time;
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public double getDrossProfit() {
        return drossProfit;
    }

    public void setDrossProfit(double drossProfit) {
        this.drossProfit = drossProfit;
    }

    public double getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(double customerPrice) {
        this.customerPrice = customerPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
