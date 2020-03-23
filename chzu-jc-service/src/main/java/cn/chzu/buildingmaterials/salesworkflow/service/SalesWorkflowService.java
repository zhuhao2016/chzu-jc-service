package cn.chzu.buildingmaterials.salesworkflow.service;

import cn.chzu.buildingmaterials.salesworkflow.model.Check;
import cn.chzu.buildingmaterials.salesworkflow.model.CheckVO;
import cn.chzu.buildingmaterials.storegoods.model.StoreVo;

import java.util.List;

/**
 * @description: 线下销售模块
 * @author: zhu_hao
 * @date: Created in 2020/3/23 14:46
 * @version: 1.0.0
 * @modified By:
 */
public interface SalesWorkflowService {

    //员工查询所有商品接口，便于下单
    public List<StoreVo> findAllStore();

    //线下员工结单操作,已确认支付
    public CheckVO checkEmployee(List<Check> checkList);
}
