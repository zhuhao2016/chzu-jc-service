package cn.chzu.buildingmaterials.salesworkflow.model;

import cn.chzu.base.model.Prompt;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/23 15:36
 * @version: 1.0.0
 * @modified By:
 */
public class CheckVO extends Prompt {

    //结算单号
    private String checkId;
    //销售总额
    private String salesSum;
    //封装
    private List<Check> check;

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(String salesSum) {
        this.salesSum = salesSum;
    }

    public List<Check> getCheck() {
        return check;
    }

    public void setCheck(List<Check> check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "CheckVO{" +
                "checkId='" + checkId + '\'' +
                ", salesSum='" + salesSum + '\'' +
                ", check=" + check +
                '}';
    }
}
