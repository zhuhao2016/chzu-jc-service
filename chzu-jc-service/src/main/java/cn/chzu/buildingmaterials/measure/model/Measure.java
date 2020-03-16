package cn.chzu.buildingmaterials.measure.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 商品计量单位实体类
 * @author: zhu_hao
 * @date: Created in 2020/3/16 13:15
 * @version: 1.0.0
 * @modified By:
 */
public class Measure extends Prompt {
    //唯一id
    private String id;

    //计量单位
    private String unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "id='" + id + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
