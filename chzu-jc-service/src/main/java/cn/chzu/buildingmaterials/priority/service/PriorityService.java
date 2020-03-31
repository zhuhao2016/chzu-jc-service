package cn.chzu.buildingmaterials.priority.service;

import cn.chzu.buildingmaterials.priority.model.Priority;

/**
 * @description: 微信小程序商品推广管理
 * @author: zhu_hao
 * @date: Created in 2020/3/31 14:20
 * @version: 1.0.0
 * @modified By:
 */
public interface PriorityService {

    //首页优先推广管理
    public Priority updatePriority(String id , String priority);
}
