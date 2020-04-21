package cn.chzu.buildingmaterials.manage.web;


import cn.chzu.buildingmaterials.manage.model.Business;
import cn.chzu.buildingmaterials.manage.model.Manage;
import cn.chzu.buildingmaterials.manage.model.StoreAnalysis;
import cn.chzu.buildingmaterials.manage.service.ManageService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description: 经营概括
 * @author: zhu_hao
 * @date: Created in 2020/3/9 13:37
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    ManageService manageService;

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.manage.model.Manage>
     * @Title informationStatistics
     * @description 经营概括
     * @author zhu_hao
     * @date 2020/3/9 13:52
     */
    @RequestMapping(value = "/informationStatistics", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Manage> informationStatistics(HttpServletRequest request, HttpServletResponse response) {

        Manage manage = manageService.informationStatistics();
        return new ResObject<>(null, manage);
    }

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.manage.model.StoreAnalysis>>
     * @Title storeAnalysis
     * @description 店铺订单总分析
     * @author zhu_hao
     * @date 2020/3/9 16:11
     */
    @RequestMapping(value = "/storeAnalysis", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<StoreAnalysis> storeAnalysis(HttpServletRequest request, HttpServletResponse response) {

        StoreAnalysis storeAnalysis = manageService.storeAnalysis();
        return new ResObject<>(null, storeAnalysis);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.manage.model.StoreAnalysis>>
     * @Title salesAnalysis
     * @description 各个店铺每天销售额、毛利润、销售笔数、客单价分析
     * @author zhu_hao
     * @date 2020/3/14 15:46
     */
    @RequestMapping(value = "/salesAnalysis", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<StoreAnalysis>> salesAnalysis(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<List<StoreAnalysis>> data) {

        List<StoreAnalysis> object = data.getObject();
        List<StoreAnalysis> storeAnalyses = manageService.salesAnalysis(object);
        return new ResObject<>(null, storeAnalyses);
    }

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.manage.model.Business>
     * @Title pendingTransaction
     * @description 待处理事务
     * @author zhu_hao
     * @date 2020/4/21 10:38
     */
    @RequestMapping(value = "/pendingTransaction", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Business> pendingTransaction(HttpServletRequest request, HttpServletResponse response) {

        Business business = manageService.pendingTransaction();
        return new ResObject<>(null, business);
    }
}
