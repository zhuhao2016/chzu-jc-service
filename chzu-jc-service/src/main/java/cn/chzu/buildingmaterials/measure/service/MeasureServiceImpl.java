package cn.chzu.buildingmaterials.measure.service;

import cn.chzu.buildingmaterials.measure.dao.MeasureMapper;
import cn.chzu.buildingmaterials.measure.model.Measure;
import cn.chzu.conf.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/16 13:18
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class MeasureServiceImpl implements MeasureService {

    @Autowired
    MeasureMapper measureMapper;

    //查询所有计量单位
    @Override
    public List<Measure> findAll() {

        return measureMapper.findAll();
    }

    //新增计量单位
    @Override
    public Measure add(Measure measure) {

        String id = measure.getId();
        String unit = measure.getUnit();
        //不能为空
        if (unit == null || unit.isEmpty()) {
            measure.setMsg("计量单位不能为空!");
            return measure;

        }

        //根据unit查询数据库，看是否已有
        Measure byUnit = measureMapper.findByUnit(unit);
        if (byUnit != null) {
            measure.setMsg("该计量单位已存在，请重新输入！");

        } else {
            if (id == null || id.isEmpty()) {
                measure.setId(UUID.getUUID());
            }

            measureMapper.add(measure);
            measure.setMsg("成功新增计量单位: " + unit);
        }
        return measure;
    }

    //删除
    @Override
    public Measure delete(String id) {

        Measure measure = new Measure();
        int i = measureMapper.delete(id);
        if (i == 1) {
            measure.setMsg("删除成功");
        } else {
            measure.setMsg("删除失败");
        }
        return measure;
    }
}
