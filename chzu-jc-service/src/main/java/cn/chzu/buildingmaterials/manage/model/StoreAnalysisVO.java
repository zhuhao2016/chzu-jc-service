package cn.chzu.buildingmaterials.manage.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 信息封装
 * @author: zhu_hao
 * @date: Created in 2020/3/11 16:28
 * @version: 1.0.0
 * @modified By:
 */
public class StoreAnalysisVO extends Prompt {

    //每天时间
    private String time;
    //店铺对应订单数量
    private int orderNumber;
    //每个店铺对应销售总金额
    private double salesAmount;
    //每个店铺销售毛利润
    private double drossProfit;
    //客单价
    private double customerPrice;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "StoreAnalysisVO{" +
                "time='" + time + '\'' +
                ", orderNumber=" + orderNumber +
                ", salesAmount=" + salesAmount +
                ", drossProfit=" + drossProfit +
                ", customerPrice=" + customerPrice +
                '}';
    }
}
