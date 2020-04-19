package cn.chzu.buildingmaterials.salesworkflow.service;

import cn.chzu.buildingmaterials.order.model.OrderDTO;
import cn.chzu.buildingmaterials.salesworkflow.model.Check;
import cn.chzu.buildingmaterials.salesworkflow.model.CheckVO;
import cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart;
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
    public Check checkEmployee(String accountId);

    //消单-->取消订单、增加库存、退还金额
    public CheckVO cancellation(CheckVO checkVO);

    //根据商品id退货
    public Check returns(String id, String count);

    //商品加入购物车
    public Check addCar(Check check);

    //结算页面列表渲染,根据员工的账号id查询
    public List<ShoppingCart> settlementListRendering(String accountId);

    //结算页面，根据id批量删除
    public ShoppingCart batchDeletionById(List<ShoppingCart> list);

    //退单，根据订单号
    public Check chargebackByOrderNumber(String orderNumber);

    //退单，根据订单号查询
    public List<OrderDTO> findByOrderNumber(String orderNumber);
}
