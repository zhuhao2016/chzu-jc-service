package cn.chzu.buildingmaterials.measure.web;

import cn.chzu.buildingmaterials.measure.model.Measure;
import cn.chzu.buildingmaterials.measure.service.MeasureService;
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
 * @date: Created in 2020/3/16 13:18
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/measure")
public class MeasureController {

    @Autowired
    MeasureService measureService;

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.buildingmaterials.measure.model.Measure>>
     * @Title findAll
     * @description 查询所有计量单位
     * @author zhu_hao
     * @date 2020/3/16 13:38
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<Measure>> findAll(HttpServletRequest request, HttpServletResponse response) {

        List<Measure> all = measureService.findAll();
        return new ResObject<>(null, all);

    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.measure.model.Measure>
     * @Title add
     * @description 新增计量单位
     * @author zhu_hao
     * @date 2020/3/16 13:39
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Measure> add(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Measure> data) {

        Measure object = data.getObject();
        Measure add = measureService.add(object);
        return new ResObject<>(null, add);
    }


    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.measure.model.Measure>
     * @Title delete
     * @description 删除
     * @author zhu_hao
     * @date 2020/3/16 13:39
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Measure> delete(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Measure> data) {

        String id = data.getObject().getId();
        Measure delete = measureService.delete(id);
        return new ResObject<>(null, delete);
    }
}
