package cn.chzu.buildingmaterials.manage.service;


import cn.chzu.buildingmaterials.manage.model.Manage;
import cn.chzu.buildingmaterials.manage.model.StoreAnalysis;
import cn.chzu.buildingmaterials.order.dao.OrderMapper;
import cn.chzu.buildingmaterials.order.model.OrderDTO;
import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.buildingmaterials.user.dao.UserMapper;
import cn.chzu.buildingmaterials.wxuser.dao.WxUserMapper;
import cn.chzu.buildingmaterials.wxuser.model.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/9 13:47
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    WxUserMapper wxUserMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StoreMapper storeMapper;

    //经营概括
    @Override
    public Manage informationStatistics() {

        Manage manage = new Manage();
        int clientNumber = 0;
        int orderNumber = 0;
        int orderTotalAmount = 0;
        //获取所有客户信息
        List<WxUser> allWxUser = wxUserMapper.findAllWxUser();
        for (WxUser str : allWxUser) {
            clientNumber = clientNumber + 1;
        }
        //获取所有订单信息
        List<OrderDTO> allBackstage = orderMapper.findAllBackstage();
        for (OrderDTO str : allBackstage) {
            orderNumber = orderNumber + 1;
            Integer salesPrice = Integer.valueOf(str.getSalesPrice());
            orderTotalAmount = orderTotalAmount + salesPrice;
        }
        manage.setClientNumber(clientNumber);
        manage.setOrderNumber(orderNumber);
        manage.setOrderTotalAmount(orderTotalAmount);
        return manage;
    }

    //店铺订单总分析
    @Override
    public StoreAnalysis storeAnalysis() {

        StoreAnalysis storeAnalysis = new StoreAnalysis();
        int orderNumber = 0;
        double salesAmount = 0;
        double drossProfit = 0;
        double customerPrice = 0;
        //查询所有订单
        List<OrderDTO> allBackstage = orderMapper.findAllBackstage();
        //创建一个HashSet集合，来统计客户人数
        HashSet<String> hashSet = new HashSet<>();
        for (OrderDTO str : allBackstage) {
            orderNumber = orderNumber + 1;
            //销售金额统计
            salesAmount = salesAmount + Double.parseDouble(str.getTotalPrice());
            //获取商品id,查询该商品的进价
            String goodsId = str.getGoodsId();
            Store byId = storeMapper.findById(goodsId);
            //获取进价
            double price = Double.parseDouble(byId.getPrice());
            //获取数量
            int counts = Integer.parseInt(str.getCounts());
            //每个订单总价
            double num = Double.parseDouble(str.getTotalPrice());
            //保留两位精度
            BigDecimal bd = new BigDecimal(num - price * counts);
            Double d = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            //计算总毛利润
            drossProfit = drossProfit + (d);
            //将订单的客户名添加到HashSet集合，以便于统计客户人数
            hashSet.add(str.getUserName());
        }
        //获取下单人数
        int size = hashSet.size();
        //计算客单价(销售总额/下单人数)
        customerPrice = salesAmount / size;

        storeAnalysis.setOrderNumber(orderNumber);
        storeAnalysis.setSalesAmount(salesAmount);
        storeAnalysis.setDrossProfit(drossProfit);
        storeAnalysis.setCustomerPrice(customerPrice);
        storeAnalysis.setStoreName("太平建材市场");
        return storeAnalysis;
    }


    //店铺每天销售额、毛利润、销售笔数、客单价分析
    @Override
    public List<StoreAnalysis> salesAnalysis(List<StoreAnalysis> analysis) {

        StoreAnalysis storeAnalysis = new StoreAnalysis();

        List<StoreAnalysis> list = new ArrayList<>();
        //遍历时间集合
        for (StoreAnalysis str3 : analysis) {
            //创建一个HashSet集合，来统计客户人数
            HashSet<String> hashSet = new HashSet<>();
            //查询所有订单
            List<OrderDTO> allByStoreName = orderMapper.findAllBackstage();
            String time = str3.getTime();
            //设置查询的时间
            storeAnalysis.setTime(time);
            //设置店铺名
            storeAnalysis.setStoreName("太平建材市场");
            int orderNumber = 0;
            double salesAmount = 0;
            double drossProfit = 0;
            double customerPrice = 0;
            //所有信息处理
            for (OrderDTO str2 : allByStoreName) {
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
            storeAnalysis.setOrderNumber(orderNumber);
            storeAnalysis.setSalesAmount(salesAmount);
            storeAnalysis.setDrossProfit(drossProfit);
            storeAnalysis.setCustomerPrice(customerPrice);
            list.add(storeAnalysis);
        }
        return list;
    }
}
