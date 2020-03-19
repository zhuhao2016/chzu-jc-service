package cn.chzu.buildingmaterials.order.service;


import cn.chzu.buildingmaterials.order.dao.OrderMapper;
import cn.chzu.buildingmaterials.order.model.Order;
import cn.chzu.buildingmaterials.order.model.OrderAddressVO;
import cn.chzu.buildingmaterials.order.model.OrderDTO;
import cn.chzu.buildingmaterials.shoppingcart.dao.ShoppingCartMapper;
import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.conf.util.UUID;
import cn.chzu.conf.util.time.CurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/6 13:43
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    //批量创建订单
    @Override
    public OrderAddressVO createOrder(OrderAddressVO orderAddressVO) {

        List<Order> order = orderAddressVO.getOrder();
        //查询对应商品库存，看商品是否够
        for (Order str : order) {
            Store store = new Store();
            OrderAddressVO vo = new OrderAddressVO();
            store.setId(str.getGoodsId());
            Integer counts = Integer.valueOf(str.getCounts());
            Store salesNumber = storeMapper.findSalesNumber(store);
            Integer number = Integer.valueOf(salesNumber.getSalesNumber());
            String goodsName = salesNumber.getGoodsName();
            String storeName = salesNumber.getStoreName();
            if (counts > number) {
                vo.setMsg(goodsName + "库存不足! 剩余库存： " + number);
                return vo;
            }
        }
        for (Order str : order) {
            Store stores = new Store();

            OrderDTO orderDTO = new OrderDTO();
            //生成订单时间
            String time = CurrentTime.newTime();
            orderDTO.setId(UUID.getUUID());
            orderDTO.setAccountId(orderAddressVO.getAccountId());
            orderDTO.setShoppingId(str.getShoppingId());
            orderDTO.setImg(str.getImg());
            orderDTO.setGoodsName(str.getGoodsName());
            orderDTO.setSalesPrice(str.getSalesPrice());
            orderDTO.setTotalPrice(str.getTotalPrice());
            orderDTO.setCounts(str.getCounts());
            orderDTO.setGoodsId(str.getGoodsId());
            orderDTO.setStoreName(str.getStoreName());
            orderDTO.setCreateTime(time);
            orderDTO.setLeaveMessage(str.getLeaveMessage());
            orderDTO.setStatus("待发货");
            orderDTO.setUserName(orderAddressVO.getUserName());
            orderDTO.setTelNumber(orderAddressVO.getTelNumber());
            orderDTO.setAddress(orderAddressVO.getAll());
            orderDTO.setPostalCode(orderAddressVO.getPostalCode());
            //将生成的订单存入数据库
            orderMapper.createOrder(orderDTO);
            //减少对应库存
            stores.setId(str.getGoodsId());
            Integer counts = Integer.valueOf(str.getCounts());
            Store salesNumber = storeMapper.findSalesNumber(stores);
            Integer number = Integer.valueOf(salesNumber.getSalesNumber());
            String i = String.valueOf(number - counts);
            stores.setSalesNumber(i);
            storeMapper.updateSalesNumber(stores);
            //根据购物车每个商品信息的id删除对应购物车商品
            shoppingCartMapper.delete(str.getShoppingId());
        }
        orderAddressVO.setMsg("已生成订单！");
        return orderAddressVO;
    }

    //查询个人所有订单
    @Override
    public List<OrderDTO> findAll(String accountId) {

        List<OrderDTO> all = orderMapper.findAll(accountId);
        return all;
    }

    //查询待发货、已发货接口
    @Override
    public List<OrderDTO> findToBeDelivered(OrderDTO orderDTO) {

        List<OrderDTO> toBeDelivered = orderMapper.findToBeDelivered(orderDTO);
        return toBeDelivered;
    }

    //后台发货OR关闭订单OR取消订单。修改订单状态
    @Override
    public OrderDTO update(String id, String status) {

        OrderDTO orderDTO = new OrderDTO();
        int i = orderMapper.update(id, status);
        if (i == 1) {
            orderDTO.setMsg("操作结果：" + status);
        } else {
            orderDTO.setMsg("操作失败，请重试！");
        }
        return orderDTO;
    }

    //后台查询所有订单
    @Override
    public List<OrderDTO> findAllBackstage() {

        List<OrderDTO> allBackstage = orderMapper.findAllBackstage();
        return allBackstage;
    }

    // 模糊查询
    @Override
    public List<OrderDTO> findByLink(String links) {

        List<OrderDTO> byLinks = orderMapper.findByLink(links);
        return byLinks;
    }
}
