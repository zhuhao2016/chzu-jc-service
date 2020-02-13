package cn.chzu.buildingmaterials.storegoods.service;


import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.conf.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;

    //新增商品
    @Override
    public Store add(Store store) {
        String id = store.getId();
        String goodsName = store.getGoodsName();
        //商品不能为空
        if (store.getGoodsName() == null || store.getGoodsName().isEmpty()) {
            store.setMsg("");
            System.out.println(store.getMsg());
            return store;

        }

        //根据商品名称查询数据库，看是否已有该商品
        Store goodsName1 = storeMapper.findGoodsName(goodsName);
        if (goodsName1 != null) {
            store.setMsg("该商品已存在，请重新输入！");

        } else {
            if (id == null || id.isEmpty()) {
                store.setId(UUID.getUUID());
            }

            store.setStoreName("太平建材市场");
            storeMapper.add(store);
            store.setMsg("成功新增商品！");
        }
        System.out.println(store.getMsg());
        return store;


    }

    //查询所有，分页展示
    @Override
    public List<Store> findAll(String classification) {

        return storeMapper.findAll(classification);
    }

    //商品名模糊查询
    @Override
    public List<Store> findByGoodsName(String goodsName) {

        return storeMapper.findByGoodsName(goodsName);
    }

    // 删除单个商品
    @Override
    public Store delete(String id) {

        Store store = new Store();
        int delete = storeMapper.delete(id);
        if (delete == 1) {
            store.setMsg("删除成功！");
        } else {
            store.setMsg("删除失败！");
        }

        return store;
    }

    @Override
    public Store updateById(Store store) {

        int i = storeMapper.updateById(store);
        if (i == 1) {
            store.setMsg("修改成功！");
        } else {
            store.setMsg("修改失败！");
        }

        return store;
    }


}
