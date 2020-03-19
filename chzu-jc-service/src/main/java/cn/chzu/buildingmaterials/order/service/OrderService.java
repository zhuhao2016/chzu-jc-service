package cn.chzu.buildingmaterials.order.service;


import cn.chzu.buildingmaterials.order.model.OrderAddressVO;
import cn.chzu.buildingmaterials.order.model.OrderDTO;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/6 13:42
 * @version: 1.0.0
 * @modified By:
 */
public interface OrderService {

    //批量创建订单
    public OrderAddressVO createOrder(OrderAddressVO orderAddressVO);

    //查询个人所有订单
    public List<OrderDTO> findAll(String accountId);

    //查询待发货、已发货接口
    public List<OrderDTO> findToBeDelivered(OrderDTO orderDTO);

    //后台发货OR关闭订单OR取消订单。修改订单状态
    public OrderDTO update(String id, String status);

    //查询所有订单
    public List<OrderDTO> findAllBackstage();

    // 模糊查询
    public List<OrderDTO> findByLink(String links);

}
