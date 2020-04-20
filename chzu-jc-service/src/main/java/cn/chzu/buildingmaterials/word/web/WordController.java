package cn.chzu.buildingmaterials.word.web;


import cn.chzu.buildingmaterials.order.dao.OrderMapper;
import cn.chzu.buildingmaterials.order.model.OrderDTO;
import cn.chzu.buildingmaterials.salesworkflow.model.Check;
import cn.chzu.buildingmaterials.shoppingcart.dao.ShoppingCartMapper;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.util.time.CurrentTime;
import cn.chzu.conf.util.word.DownloadUtil;
import cn.chzu.conf.util.word.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/4/20 15:05
 * @version: 1.0.0
 * @modified By:
 */

@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/word")
public class WordController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @RequestMapping("/getWord")
    public void returnWord(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Check> data) throws Exception {

        //获取订单号
        String orderNumber = data.getObject().getOrderNumber();
        //获取员工id,及购物车id
        String accountId = data.getObject().getAccountId();
        Integer total = 0;
        //生成订单时间
        String time = CurrentTime.newTime();
        Map<String, Object> map = new HashMap<>();
        map.put("orderNumber", orderNumber);
        map.put("date", time);

        //获取所有该订单号的订单
        List<OrderDTO> listInfo = orderMapper.findAll(orderNumber);
        //计算总价
        for (OrderDTO str : listInfo) {
            Integer totalPrice = Integer.valueOf(str.getTotalPrice());
            total = total + totalPrice;
        }
        map.put("total", total);
        map.put("listInfo", listInfo);
        String ftlName = "certificate.ftl";
        //将参数写入模板文件中
        ByteArrayOutputStream outputStream = WordUtil.process(map, ftlName);
        //清空员工购物车所有商品
        shoppingCartMapper.deleteByAccountId(accountId);
        //返回doc文档，并下载。
        DownloadUtil.download(outputStream, response, "1.doc");
    }
}
