package cn.chzu.buildingmaterials.manage.service;


import cn.chzu.buildingmaterials.manage.model.Business;
import cn.chzu.buildingmaterials.manage.model.Manage;
import cn.chzu.buildingmaterials.manage.model.StoreAnalysis;

import java.util.List;

/**
 * @description: 经营概括
 * @author: zhu_hao
 * @date: Created in 2020/3/9 13:36
 * @version: 1.0.0
 * @modified By:
 */
public interface ManageService {

    //经营概括信息统计
    public Manage informationStatistics();

    //店铺订单总分析
    public StoreAnalysis storeAnalysis();

    //各个店铺每天销售额、毛利润、销售笔数、客单价分析
    public List<StoreAnalysis> salesAnalysis(List<StoreAnalysis> analysis);

    //待处理事务
    public Business pendingTransaction();
}
