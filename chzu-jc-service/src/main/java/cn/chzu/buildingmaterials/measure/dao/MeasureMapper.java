package cn.chzu.buildingmaterials.measure.dao;

import cn.chzu.buildingmaterials.measure.model.Measure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/16 13:17
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface MeasureMapper {

    //查询所有字段
    public List<Measure> findAll();

    // 新增
    public int add(Measure measure);

    //删除种类
    public int delete(String id);

    //根据unit查询
    public Measure findByUnit(String unit);
}
