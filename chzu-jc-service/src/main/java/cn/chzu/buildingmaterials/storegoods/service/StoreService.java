package cn.chzu.buildingmaterials.storegoods.service;

import cn.chzu.buildingmaterials.storegoods.model.Store;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/31 12:53
 * @version: 1.0.0
 * @modified By:
 */
public interface StoreService {
    //新增商品
    Store add(Store store);

    //查询所有，分页展示
    List<Store> findAll(String classification);

    //商品名模糊查询
    List<Store> findByGoodsName(String goodsName);

    // 删除单个商品
    Store delete(String id);

    //根据id修改
    Store updateById(Store store);

}
