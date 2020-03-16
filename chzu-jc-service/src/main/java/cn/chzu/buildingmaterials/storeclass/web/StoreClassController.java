package cn.chzu.buildingmaterials.storeclass.web;

import cn.chzu.buildingmaterials.storeclass.model.StoreClass;
import cn.chzu.buildingmaterials.storeclass.service.StoreClassService;
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
 * @date: Created in 2020/2/24 16:34
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/storeClass")
public class StoreClassController {
    @Autowired
    StoreClassService storeClassService;

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.storeclass.model.StoreClass>>
     * @Title findAll
     * @description 查询商品分类字典所有字段
     * @author zhu_hao
     * @date 2020/2/24 16:37
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<StoreClass>> findAll(HttpServletRequest request, HttpServletResponse response) {

        List<StoreClass> all = storeClassService.findAll();
        return new ResObject<>(null, all);

    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.storeclass.model.StoreClass>
     * @Title add
     * @description 新增字典分类字段
     * @author zhu_hao
     * @date 2020/2/24 16:46
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<StoreClass> add(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<StoreClass> data) {

        StoreClass object = data.getObject();
        StoreClass add = storeClassService.add(object);
        return new ResObject<>(null, add);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.storeclass.model.StoreClass>
     * @Title deleteClassification
     * @description 删除类型
     * @author zhu_hao
     * @date 2020/2/25 13:04
     */
    @RequestMapping(value = "/deleteClassification", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<StoreClass> deleteClassification(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<StoreClass> data) {

        String id = data.getObject().getId();
        StoreClass storeClass = storeClassService.deleteClassification(id);
        return new ResObject<>(null, storeClass);
    }
}
