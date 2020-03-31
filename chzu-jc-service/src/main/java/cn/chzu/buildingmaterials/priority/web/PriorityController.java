package cn.chzu.buildingmaterials.priority.web;

import cn.chzu.buildingmaterials.priority.model.Priority;
import cn.chzu.buildingmaterials.priority.service.PriorityService;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 微信小程序商品推广管理
 * @author: zhu_hao
 * @date: Created in 2020/3/31 14:21
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    PriorityService priorityService;

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.priority.model.Priority>
     * @Title updatePriority
     * @description 首页优先级推广管理
     * @author zhu_hao
     * @date 2020/3/31 14:47
     */
    @RequestMapping(value = "/updatePriority", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Priority> updatePriority(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Store> data) {


        String id = data.getObject().getId();
        String priority = data.getObject().getPriority();
        Priority updatePriority = priorityService.updatePriority(id, priority);
        return new ResObject<>(null, updatePriority);
    }
}
