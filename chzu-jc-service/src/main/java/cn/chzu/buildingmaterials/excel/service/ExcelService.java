package cn.chzu.buildingmaterials.excel.service;

import cn.chzu.buildingmaterials.manage.model.StoreAnalysis;

import java.text.ParseException;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/19 13:37
 * @version: 1.0.0
 * @modified By:
 */
public interface ExcelService {

    //导出excel表格
    public List<StoreAnalysis> exportExcel() throws ParseException;
}
