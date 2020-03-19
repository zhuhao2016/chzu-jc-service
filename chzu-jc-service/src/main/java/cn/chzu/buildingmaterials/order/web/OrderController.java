package cn.chzu.buildingmaterials.order.web;


import cn.chzu.buildingmaterials.order.model.OrderAddressVO;
import cn.chzu.buildingmaterials.order.model.OrderDTO;
import cn.chzu.buildingmaterials.order.service.OrderService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/6 13:43
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.order.model.OrderAddressVO>
     * @Title createOrder
     * @description 确认下单，批量创建订单
     * @author zhu_hao
     * @date 2020/3/6 15:36
     */
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public ResObject<OrderAddressVO> createOrder(HttpServletRequest request, HttpServletResponse response,
                                                 @RequestBody ReqObject<OrderAddressVO> data) {


        OrderAddressVO object = data.getObject();
        OrderAddressVO order = orderService.createOrder(object);
        return new ResObject<>(null, order);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.order.model.OrderAddressVO>>
     * @Title findAll
     * @description 查询个人全部订单
     * @author zhu_hao
     * @date 2020/3/8 9:57
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<OrderDTO>> findAll(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody ReqObject<OrderAddressVO> data) {


        String accountId = data.getObject().getAccountId();
        List<OrderDTO> all = orderService.findAll(accountId);
        return new ResObject<>(null, all);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.order.model.OrderDTO>
     * @Title update
     * @description 后台发货OR关闭订单OR取消订单。修改订单状态
     * @author zhu_hao
     * @date 2020/3/8 12:23
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<OrderDTO> update(HttpServletRequest request, HttpServletResponse response,
                                      @RequestBody ReqObject<OrderDTO> data) {


        String id = data.getObject().getId();
        String status = data.getObject().getStatus();
        OrderDTO update = orderService.update(id, status);
        return new ResObject<>(null, update);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.order.model.OrderDTO>
     * @Title findToBeDelivered
     * @description 个人查询待发货、已发货接口
     * @author zhu_hao
     * @date 2020/3/8 12:49
     */
    @RequestMapping(value = "/findToBeDelivered", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<OrderDTO>> findToBeDelivered(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestBody ReqObject<OrderDTO> data) {


        OrderDTO object = data.getObject();
        List<OrderDTO> toBeDelivered = orderService.findToBeDelivered(object);
        return new ResObject<>(null, toBeDelivered);
    }

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.order.model.OrderDTO>>
     * @Title findAllBackstage
     * @description 后台查询所有订单，分页展示，根据状态排序
     * @author zhu_hao
     * @date 2020/3/8 19:02
     */
    @RequestMapping(value = "/findAllBackstage", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<OrderDTO>> findAllBackstage(HttpServletRequest request, HttpServletResponse response) {

        List<OrderDTO> allBackstage = orderService.findAllBackstage();
        return new ResObject<>(null, allBackstage);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.order.model.OrderDTO>>
     * @Title findByLink
     * @description 模糊查询
     * @author zhu_hao
     * @date 2020/3/9 13:23
     */
    @RequestMapping(value = "/findByLink", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<OrderDTO>> findByLink(HttpServletRequest request, HttpServletResponse response,
                                                @RequestBody ReqObject<OrderDTO> data) {

        String links = data.getObject().getLinks();
        List<OrderDTO> byLink = orderService.findByLink(links);
        return new ResObject<>(null, byLink);
    }
}
