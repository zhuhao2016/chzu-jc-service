package cn.chzu.buildingmaterials.order.dao;

import cn.chzu.buildingmaterials.order.model.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/6 13:42
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface OrderMapper {

    //批量创建订单
    public int createOrder(OrderDTO orderDTO);

    //查询个人所有订单
    public List<OrderDTO> findAll(String accountId);

    //查询待发货、已发货接口
    public List<OrderDTO> findToBeDelivered(OrderDTO orderDTO);

    //后台发货OR关闭订单OR取消订单。修改订单状态
    public int update(String id, String status);

    //后台查询所有订单，分页展示，根据状态排序
    public List<OrderDTO> findAllBackstage();

    //模糊查询
    public List<OrderDTO> findByLink(String links);

    //根据店铺名查询所有订单
    public List<OrderDTO> findAllByStoreName(String storeName);
}
