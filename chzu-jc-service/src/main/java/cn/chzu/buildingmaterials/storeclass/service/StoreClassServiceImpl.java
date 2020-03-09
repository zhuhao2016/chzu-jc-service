package cn.chzu.buildingmaterials.storeclass.service;

import cn.chzu.buildingmaterials.storeclass.dao.StoreClassMapper;
import cn.chzu.buildingmaterials.storeclass.model.StoreClass;
import cn.chzu.conf.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/24 16:31
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class StoreClassServiceImpl implements StoreClassService {

    @Autowired
    StoreClassMapper storeClassMapper;

    //查询所有
    @Override
    public List<StoreClass> findAll() {

        return storeClassMapper.findAll();
    }

    @Override
    public StoreClass add(StoreClass storeClass) {

        String id = storeClass.getId();
        String classification = storeClass.getClassification();
        //不能为空
        if (classification == null || classification.isEmpty()) {
            storeClass.setMsg("分类不能为空!");
            System.out.println(storeClass.getMsg());
            return storeClass;

        }

        //根据分类名称查询数据库，看是否已有
        StoreClass classification1 = storeClassMapper.findClassification(classification);
        if (classification1 != null) {
            storeClass.setMsg("该分类名称已存在，请重新输入！");

        } else {
            if (id == null || id.isEmpty()) {
                storeClass.setId(UUID.getUUID());
            }

            storeClassMapper.add(storeClass);
            storeClass.setMsg("成功新增分类: " + classification);
        }
        System.out.println(storeClass.getMsg());
        return storeClass;
    }

    //删除
    @Override
    public StoreClass deleteClassification(String id) {

        StoreClass storeClass = new StoreClass();
        int i = storeClassMapper.deleteClassification(id);
        if (i == 1) {
            storeClass.setMsg("删除成功");
        }else {
            storeClass.setMsg("删除失败");
        }
        return storeClass;
    }
}
