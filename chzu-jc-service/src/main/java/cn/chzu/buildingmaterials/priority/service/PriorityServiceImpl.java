package cn.chzu.buildingmaterials.priority.service;

import cn.chzu.buildingmaterials.priority.model.Priority;
import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 微信小程序商品推广管理
 * @author: zhu_hao
 * @date: Created in 2020/3/31 14:21
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    StoreMapper storeMapper;

    //首页优先推广管理
    @Override
    public Priority updatePriority(String id, String priority) {

        Priority p = new Priority();
        int i = storeMapper.updatePriority(id, priority);
        if (i == 1) {
            p.setMsg("成功设置推广优先级为：" + priority);
        } else {
            p.setMsg("设置失败");
        }
        return p;
    }
}
