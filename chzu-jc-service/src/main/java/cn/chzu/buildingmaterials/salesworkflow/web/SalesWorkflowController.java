package cn.chzu.buildingmaterials.salesworkflow.web;

import cn.chzu.buildingmaterials.salesworkflow.model.Check;
import cn.chzu.buildingmaterials.salesworkflow.model.CheckVO;
import cn.chzu.buildingmaterials.salesworkflow.service.SalesWorkflowService;
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
     * @Title findAllStore
     * @description 员工查询所有商品接口，便于下单
     * @author zhu_hao
     * @date 2020/3/23 14:57
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List<cn.chzu.buildingmaterials.storegoods.model.StoreVo>>
     */
    @RequestMapping(value = "/findAllStore", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<StoreVo>> findAllStore(HttpServletRequest request, HttpServletResponse response) {

        List<StoreVo> allStore = salesWorkflowService.findAllStore();
        return new ResObject<>(null, allStore);
    }

    /**
     * @Title checkEmployee
     * @description 线下员工结单操作,已确认支付//操作流程，点击结算，放出二维码，支付成功点击确认 调用接口
     * @author zhu_hao
     * @date 2020/3/23 15:27
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.salesworkflow.model.Check>
     */
    @RequestMapping(value = "/checkEmployee", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<CheckVO> checkEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<List<Check>> data) {

        List<Check> list = data.getObject();
        CheckVO checkVO = salesWorkflowService.checkEmployee(list);
        return new ResObject<>(null, checkVO);
    }
}
