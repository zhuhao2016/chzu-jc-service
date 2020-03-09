package cn.chzu.buildingmaterials.storeclass.model;

import cn.chzu.base.model.Prompt;

/**
 * @description: 产品分类字典
 * @author: zhu_hao
 * @date: Created in 2020/2/24 16:25
 * @version: 1.0.0
 * @modified By:
 */
public class StoreClass extends Prompt {
    //id
    private String id;

    //产品分类
    private String classification;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "StoreClass{" +
                "id='" + id + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
