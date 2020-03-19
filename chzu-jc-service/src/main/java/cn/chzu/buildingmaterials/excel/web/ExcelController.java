package cn.chzu.buildingmaterials.excel.web;

import cn.chzu.buildingmaterials.excel.service.ExcelService;
import cn.chzu.buildingmaterials.manage.model.StoreAnalysis;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/19 13:24
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;


    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws Exception {

        //接收参数
        //String accounttime = request.getParameter("accounttime");
        //查询数据，实际可通过传过来的参数当条件去数据库查询，在此我就用空集合（数据）来替代
        //List<StoreAnalysis> list = new ArrayList<StoreAnalysis>();

        List<StoreAnalysis> list = excelService.exportExcel();
        //创建poi导出数据对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();

        //创建sheet页
        SXSSFSheet sheet = sxssfWorkbook.createSheet("每天销售情况");
        //创建表头
        SXSSFRow headRow = sheet.createRow(0);
        //设置表头信息
        headRow.createCell(0).setCellValue("序号");
        headRow.createCell(1).setCellValue("时间");
        headRow.createCell(2).setCellValue("店铺名称");
        headRow.createCell(3).setCellValue("订单数量");
        headRow.createCell(4).setCellValue("销售金额");
        headRow.createCell(5).setCellValue("销售毛利润");
        headRow.createCell(6).setCellValue("客单价");
        // 遍历上面数据库查到的数据
        for (StoreAnalysis storeAnalysis : list) {
            //序号
            int x = 1;
            //填充数据
            SXSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            //序号
            dataRow.createCell(0).setCellValue(x);
            //看你实体类在进行填充
            dataRow.createCell(1).setCellValue(storeAnalysis.getTime());
            dataRow.createCell(2).setCellValue(storeAnalysis.getStoreName());
            dataRow.createCell(3).setCellValue(storeAnalysis.getOrderNumber());
            dataRow.createCell(4).setCellValue(storeAnalysis.getSalesAmount());
            dataRow.createCell(5).setCellValue(storeAnalysis.getDrossProfit());
            dataRow.createCell(6).setCellValue(storeAnalysis.getCustomerPrice());

            x++;
        }

        // 下载导出
        String filename = "orderReport";
        // 设置头信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        //一定要设置成xlsx格式
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename + ".xlsx", "UTF-8"));
        //创建一个输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //写入数据
        sxssfWorkbook.write(outputStream);

        // 关闭
        outputStream.close();
        sxssfWorkbook.close();
    }


}
