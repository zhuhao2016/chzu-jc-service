package cn.chzu.buildingmaterials.storegoods.dao;


import cn.chzu.buildingmaterials.storegoods.model.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/31 12:59
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface StoreMapper {
    // 新增商品
    public int add(Store store);

    //查询所有，分页展示
    public List<Store> findAll(String classification);

    //根据GoodsName查询
    public Store findGoodsName(String goodsName);

    //商品名模糊查询
    public List<Store> findByGoodsName(String classification);

    // 删除单个商品
    public int delete(String id);

    // 根据id修改
    public int updateById(Store store);

}