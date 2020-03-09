package cn.chzu.buildingmaterials.storeclass.service;

import cn.chzu.buildingmaterials.storeclass.model.StoreClass;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/24 16:30
 * @version: 1.0.0
 * @modified By:
 */
public interface StoreClassService {

    //查询所有
    public List<StoreClass> findAll();

    // 新增
    public StoreClass add(StoreClass storeClass);

    //删除
    public StoreClass deleteClassification(String id);
}
