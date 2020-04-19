package cn.chzu.buildingmaterials.salesworkflow.web;

import cn.chzu.buildingmaterials.salesworkflow.model.Check;
import cn.chzu.buildingmaterials.salesworkflow.model.CheckVO;
import cn.chzu.buildingmaterials.salesworkflow.service.SalesWorkflowService;
import cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart;
import cn.chzu.buildingmaterials.storegoods.model.StoreVo;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description: 线下销售模块
 * @author: zhu_hao
 * @date: Created in 2020/3/23 14:45
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/salesWork")
public class SalesWorkflowController {

    @Autowired
    SalesWorkflowService salesWorkflowService;

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.buildingmaterials.storegoods.model.StoreVo>>
     * @Title findAllStore
     * @description 员工查询所有商品接口，便于下单
     * @author zhu_hao
     * @date 2020/3/23 14:57
     */
    @RequestMapping(value = "/findAllStore", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<StoreVo>> findAllStore(HttpServletRequest request, HttpServletResponse response) {

        List<StoreVo> allStore = salesWorkflowService.findAllStore();
        return new ResObject<>(null, allStore);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.salesworkflow.model.Check>
     * @Title checkEmployee
     * @description 线下员工结单操作, 已确认支付//操作流程，点击结算，放出二维码，支付成功点击确认 调用接口
     * @author zhu_hao
     * @date 2020/3/23 15:27
     */
    @RequestMapping(value = "/checkEmployee", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<CheckVO> checkEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<List<Check>> data) {

        List<Check> list = data.getObject();
        CheckVO checkVO = salesWorkflowService.checkEmployee(list);
        return new ResObject<>(null, checkVO);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.salesworkflow.model.CheckVO>
     * @Title cancellation
     * @description 消单-->取消订单、增加库存、退还金额
     * @author zhu_hao
     * @date 2020/3/31 13:46
     */
    @RequestMapping(value = "/cancellation", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<CheckVO> cancellation(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<CheckVO> data) {

        CheckVO object = data.getObject();
        CheckVO cancellation = salesWorkflowService.cancellation(object);
        return new ResObject<>(null, cancellation);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.salesworkflow.model.Check>
     * @Title addCar
     * @description 商品加入购物车
     * @author zhu_hao
     * @date 2020/4/19 13:31
     */
    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Check> addCar(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Check> data) {

        Check object = data.getObject();
        Check check = salesWorkflowService.addCar(object);
        return new ResObject<>(null, check);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart>>
     * @Title settlementListRendering
     * @description 结算页面列表渲染, 根据员工的账号id查询
     * @author zhu_hao
     * @date 2020/4/19 13:56
     */
    @RequestMapping(value = "/settlementListRendering", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<ShoppingCart>> settlementListRendering(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Check> data) {

        String accountId = data.getObject().getAccountId();
        List<ShoppingCart> list = salesWorkflowService.settlementListRendering(accountId);
        return new ResObject<>(null, list);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart>
     * @Title batchDeletionById
     * @description 结算页面，根据id批量删除
     * @author zhu_hao
     * @date 2020/4/19 14:04
     */
    @RequestMapping(value = "/batchDeletionById", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<ShoppingCart> batchDeletionById(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<List<ShoppingCart>> data) {

        List<ShoppingCart> object = data.getObject();
        ShoppingCart shoppingCart = salesWorkflowService.batchDeletionById(object);
        return new ResObject<>(null, shoppingCart);
    }
}
