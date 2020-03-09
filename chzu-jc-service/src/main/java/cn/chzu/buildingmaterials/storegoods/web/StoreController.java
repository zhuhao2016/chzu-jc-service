package cn.chzu.buildingmaterials.storegoods.web;


import cn.chzu.buildingmaterials.storeclass.model.StoreClass;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.buildingmaterials.storegoods.model.StoreVo;
import cn.chzu.buildingmaterials.storegoods.service.StoreService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description: 商品管理
 * @author: zhu_hao
 * @date: Created in 2020/1/31 13:01
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/storegoods")
public class StoreController {

    @Autowired
    StoreService storeService;

    /**
     * @param request
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.storegoods.model.Store>
     * @Title add
     * @description 新增商品
     * @author zhu_hao
     * @date 2020/1/31 13:14
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Store> add(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Store> data) {

        Store dataObject = data.getObject();
        Store store = storeService.add(dataObject);
        return new ResObject<>(null, store);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.storegoods.model.Store>>
     * @Title findAll
     * @description 查询所有，分页展示
     * @author zhu_hao
     * @date 2020/2/5 17:33
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<Store>> findAll(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Store> data) {

        String storeName = data.getObject().getStoreName();
        List<Store> all = storeService.findAll(storeName);
        return new ResObject<>(null, all);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.storegoods.model.Store>>
     * @Title findByGoodsName
     * @description 商品名模糊查询
     * @author zhu_hao
     * @date 2020/2/6 17:27
     */
    @RequestMapping(value = "/findByGoodsName", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<Store>> findByGoodsName(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Store> data) {

        String goodsName = data.getObject().getGoodsName();
        List<Store> byGoodsName = storeService.findByGoodsName(goodsName);
        return new ResObject<>(null, byGoodsName);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.storegoods.model.Store>
     * @Title delete
     * @description 删除单个商品
     * @author zhu_hao
     * @date 2020/2/7 11:44
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Store> delete(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Store> data) {

        String id = data.getObject().getId();
        Store delete = storeService.delete(id);
        return new ResObject<>(null, delete);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.storegoods.model.Store>
     * @Title updateById
     * @description 根据id修改
     * @author zhu_hao
     * @date 2020/2/7 13:09
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Store> updateById(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Store> data) {

        Store dataObject = data.getObject();
        Store store = storeService.updateById(dataObject);
        return new ResObject<>(null, store);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.storegoods.model.StoreVo>>
     * @Title findAllKind
     * @description 根据分类类型查询该类型所有信息
     * @author zhu_hao
     * @date 2020/2/24 17:33
     */
    @RequestMapping(value = "/findAllKind", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<StoreVo>> findAllKind(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<StoreClass> data) {

        String kind = data.getObject().getClassification();
        List<StoreVo> allKind = storeService.findAllKind(kind);
        return new ResObject<>(null, allKind);
    }

    /**
     * @param file
     * @return cn.chzu.conf.common.ResObject<java.lang.String>
     * @Title imgFile
     * @description 图片上传接口
     * @author zhu_hao
     * @date 2020/3/9 10:33
     */
    @RequestMapping(value = "/imgFile", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<String> imgFile(@RequestParam("imgFile") MultipartFile file) {

        storeService.imgFile(file);
        return new ResObject<>(null, "成功调用了接口");
    }
}
