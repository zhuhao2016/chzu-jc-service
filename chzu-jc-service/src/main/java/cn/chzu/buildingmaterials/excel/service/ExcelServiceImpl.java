package cn.chzu.buildingmaterials.excel.service;

import cn.chzu.buildingmaterials.manage.model.StoreAnalysis;
import cn.chzu.buildingmaterials.order.dao.OrderMapper;
import cn.chzu.buildingmaterials.order.model.OrderDTO;
import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.buildingmaterials.user.dao.UserMapper;
import cn.chzu.buildingmaterials.wxuser.dao.WxUserMapper;
import cn.chzu.conf.util.time.CurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/19 13:37
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    WxUserMapper wxUserMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StoreMapper storeMapper;

    //导出excel表格
    @Override
    public List<StoreAnalysis> exportExcel() throws ParseException {

        List<StoreAnalysis> list = new ArrayList<>();
       // StoreAnalysis storeAnalysis = new StoreAnalysis();
        //创建一个TreeSet集合来保存订单的日期
        //TreeSet<StoreAnalysis> set = new TreeSet<>();
        String endDate = "2020-03-18";
        List<StoreAnalysis> listTime = new ArrayList<>();
        for (; ; ) {
            StoreAnalysis sto = new StoreAnalysis();
           /* //要实现日期+1 需要String转成Date类型
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date sDate = sdf.parse(endDate);
            Format f = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sDate);
            //利用Calendar 实现 Date日期+1天
            c.add(Calendar.DAY_OF_MONTH, 1);
            sDate = c.getTime();
            //将日期转成String类型 方便进入数据库比较
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            endDate = sdf1.format(sDate);
            System.out.println("Date类型转String类型  " + endDate);*/

            //页面传递到后台的时间 为String类型
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date sDate = sdf.parse(endDate);

            //要实现日期+1 需要String转成Date类型

            Format f = new SimpleDateFormat("yyyy-MM-dd");

            Calendar c = Calendar.getInstance();
            c.setTime(sDate);
            //利用Calendar 实现 Date日期+1天
            c.add(Calendar.DAY_OF_MONTH, 1);

            sDate = c.getTime();
            //打印Date日期,显示成功+1天

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            endDate = sdf1.format(sDate);
           //将日期转成String类型 方便进入数据库比较
            sto.setTime(endDate);
            listTime.add(sto);
            //获取当前时间
            String time = CurrentTime.newTime();
            //截取字符串,只保留年月日
            String substring = time.substring(0, 10);
            if (substring.equals(endDate)){
                break;
            }
        }

        //查询所有订单
        List<OrderDTO> allBackstage = orderMapper.findAllBackstage();
       /* for (OrderDTO str : allBackstage) {
            String times = str.getCreateTime();
            //截取字符串,只保留年月日
            String substring = times.substring(0, 10);
            storeAnalysis.setTime(substring);
            set.add(storeAnalysis);
        }*/

        //遍历时间集合
        for (StoreAnalysis str3 : listTime) {

            StoreAnalysis storeAnalysis1 = new StoreAnalysis();
            //创建一个HashSet集合，来统计客户人数
            HashSet<String> hashSet = new HashSet<>();

            //设置查询的时间
            storeAnalysis1.setTime(str3.getTime());
            //
            String time = str3.getTime();
            //设置店铺名
            storeAnalysis1.setStoreName("太平建材市场");
            int orderNumber = 0;
            double salesAmount = 0;
            double drossProfit = 0;
            double customerPrice = 0;
            //所有信息处理
            for (OrderDTO str2 : allBackstage) {
                String times = str2.getCreateTime();
                //截取字符串
                String substring = times.substring(0, 10);
                if (time.equals(substring)) {
                    orderNumber++;
                    //销售金额统计
                    salesAmount = salesAmount + Double.parseDouble(str2.getTotalPrice());
                    //获取商品id,查询该商品的进价
                    String goodsId = str2.getGoodsId();
                    Store byId = storeMapper.findById(goodsId);
                    if (byId == null) {
                        continue;
                    }
                    //获取进价
                    double price = Double.parseDouble(byId.getPrice());
                    //获取数量
                    int counts = Integer.parseInt(str2.getCounts());
                    //每个订单总价
                    double num = Double.parseDouble(str2.getTotalPrice());
                    //保留两位精度
                    BigDecimal bd = new BigDecimal(num - price * counts);
                    Double d = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    //计算总毛利润
                    drossProfit = drossProfit + (d);
                    //将订单的客户名添加到HashSet集合，以便于统计客户人数
                    hashSet.add(str2.getUserName());
                }
            }
            //获取下单人数
            int size = hashSet.size();
            //计算客单价(销售总额/下单人数)
            if (size != 0) {
                customerPrice = salesAmount / size;
            } else {
                customerPrice = 0;
            }
            storeAnalysis1.setOrderNumber(orderNumber);
            storeAnalysis1.setSalesAmount(salesAmount);
            storeAnalysis1.setDrossProfit(drossProfit);
            storeAnalysis1.setCustomerPrice(customerPrice);
            list.add(storeAnalysis1);
        }
        return list;
    }


}
