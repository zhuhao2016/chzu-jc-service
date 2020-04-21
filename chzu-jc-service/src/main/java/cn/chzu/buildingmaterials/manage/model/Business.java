package cn.chzu.buildingmaterials.manage.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 待处理事务
 * @author: zhu_hao
 * @date: Created in 2020/4/21 10:21
 * @version: 1.0.0
 * @modified By:
 */
public class Business extends Prompt {

    //待发货数量
    private int toBeDelivered;
    //已发货数量
    private int shippedNum;
    //已关闭订单数
    private int closedOrdersNum;
    //已完成线下售卖数
    private int salesOfflineNum;

    public int getToBeDelivered() {
        return toBeDelivered;
    }

    public void setToBeDelivered(int toBeDelivered) {
        this.toBeDelivered = toBeDelivered;
    }

    public int getShippedNum() {
        return shippedNum;
    }

    public void setShippedNum(int shippedNum) {
        this.shippedNum = shippedNum;
    }

    public int getClosedOrdersNum() {
        return closedOrdersNum;
    }

    public void setClosedOrdersNum(int closedOrdersNum) {
        this.closedOrdersNum = closedOrdersNum;
    }

    public int getSalesOfflineNum() {
        return salesOfflineNum;
    }

    public void setSalesOfflineNum(int salesOfflineNum) {
        this.salesOfflineNum = salesOfflineNum;
    }

    @Override
    public String toString() {
        return "Business{" +
                "toBeDelivered=" + toBeDelivered +
                ", shippedNum=" + shippedNum +
                ", closedOrdersNum=" + closedOrdersNum +
                ", salesOfflineNum=" + salesOfflineNum +
                '}';
    }
}
