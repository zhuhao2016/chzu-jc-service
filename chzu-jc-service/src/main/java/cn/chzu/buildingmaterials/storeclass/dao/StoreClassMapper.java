package cn.chzu.buildingmaterials.storeclass.dao;

import cn.chzu.buildingmaterials.storeclass.model.StoreClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/24 16:28
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface StoreClassMapper {

    //查询所有字段
    public List<StoreClass> findAll();

    // 新增
    public int add(StoreClass storeClass);

    //根据Classification查询
    public StoreClass findClassification(String classification);

    //删除种类
    public int deleteClassification(String id);
}
