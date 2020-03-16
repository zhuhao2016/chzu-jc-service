package cn.chzu.buildingmaterials.measure.service;

import cn.chzu.buildingmaterials.measure.model.Measure;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/16 13:18
 * @version: 1.0.0
 * @modified By:
 */
public interface MeasureService {

    //查询所有计量单位
    public List<Measure> findAll();

    //新增计量单位
    public Measure add(Measure measure);

    //删除
    public Measure delete(String id);
}
