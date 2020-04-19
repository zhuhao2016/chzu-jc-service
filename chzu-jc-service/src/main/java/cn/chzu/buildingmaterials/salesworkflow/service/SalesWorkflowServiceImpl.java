package cn.chzu.buildingmaterials.salesworkflow.service;

import cn.chzu.buildingmaterials.order.dao.OrderMapper;
import cn.chzu.buildingmaterials.order.model.OrderDTO;
import cn.chzu.buildingmaterials.salesworkflow.model.Check;
import cn.chzu.buildingmaterials.salesworkflow.model.CheckVO;
import cn.chzu.buildingmaterials.shoppingcart.dao.ShoppingCartMapper;
import cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart;
import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.buildingmaterials.storegoods.model.StoreVo;
import cn.chzu.conf.util.UUID;
import cn.chzu.conf.util.time.CurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 线下销售模块
 * @author: zhu_hao
 * @date: Created in 2020/3/23 14:46
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class SalesWorkflowServiceImpl implements SalesWorkflowService {

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;


    //员工查询所有商品接口，便于下单
    @Override
    public List<StoreVo> findAllStore() {

        //查询所有商品
        List<Store> all = storeMapper.findAll("太平建材市场");
        ArrayList<StoreVo> list = new ArrayList<>();
        for (Store str : all) {
            StoreVo storeVo = new StoreVo();
            storeVo.setId(str.getId());
            storeVo.setImg(str.getImage());
            storeVo.setGoodsName(str.getGoodsName());
            storeVo.setSalesPrice(str.getSalesPrice());
            storeVo.setStoreName(str.getStoreName());
            storeVo.setSalesNumber(str.getSalesNumber());
            if (str.getSalesNumber().equals("0")) {
                storeVo.setMsg("库存不足！");
            }
            list.add(storeVo);
        }
        return list;
    }

    //线下员工结单操作已确认支付
    @Override
    public CheckVO checkEmployee(List<Check> checkList) {

        CheckVO checkVO = new CheckVO();
        ArrayList<Check> list = new ArrayList<>();
        double sum = 0;
        //生成结算单号
        String accountId = UUID.getUUID();
        //遍历list集合
        for (Check str : checkList) {
            Check check = new Check();
            Store store = new Store();
            String id = str.getId();
            //查询计量单位
            Store byId = storeMapper.findById(id);
            String measure = byId.getMeasure();
            check.setId(id);
            check.setGoodsName(str.getGoodsName());
            check.setSalesPrice(str.getSalesPrice());
            check.setMeasure(measure);
            check.setCount(str.getCount());
            check.setSalesPriceSum(str.getSalesPriceSum());
            sum = sum + Double.parseDouble(str.getSalesPriceSum());

            //减少对应商品库存
            int num = Integer.parseInt(byId.getCont()) - Integer.parseInt(str.getCount());
            store.setCont(String.valueOf(num));
            store.setId(id);
            storeMapper.updateSalesNumber(store);
            //生成对应订单
            OrderDTO orderDTO = new OrderDTO();
            //生成订单时间
            String time = CurrentTime.newTime();
            orderDTO.setId(UUID.getUUID());
            orderDTO.setAccountId(accountId);
            orderDTO.setGoodsName(str.getGoodsName());
            orderDTO.setSalesPrice(str.getSalesPrice());
            orderDTO.setTotalPrice(str.getSalesPriceSum());
            orderDTO.setCounts(str.getCount());
            orderDTO.setGoodsId(str.getId());
            orderDTO.setStoreName("太平建材市场");
            orderDTO.setCreateTime(time);
            orderDTO.setStatus("线下售卖");
            orderMapper.createOrder(orderDTO);
            list.add(check);
        }
        checkVO.setCheckId(accountId);
        checkVO.setSalesSum(String.valueOf(sum));
        checkVO.setCheck(list);
        checkVO.setMsg("结算成功，请打印销售凭据！");
        return checkVO;
    }

    //消单-->取消订单、增加库存、退还金额
    @Override
    public CheckVO cancellation(CheckVO checkVO) {

        String checkId = checkVO.getCheckId();
        //将对应商品库存增加
        List<OrderDTO> list = orderMapper.findAll(checkId);
        for (OrderDTO str : list) {
            //获取商品的id
            String goodsId = str.getGoodsId();
            //获取购买的商品数量
            String counts = str.getCounts();
            //类型转换
            Integer i = Integer.valueOf(counts);
            Store store = new Store();
            store.setId(goodsId);
            Store salesNumber = storeMapper.findSalesNumber(store);
            String number = salesNumber.getSalesNumber();
            Integer s = Integer.valueOf(number);
            Integer num = i + s;
            //转换成string类型
            String s1 = String.valueOf(num);
            //根据商品id修改库存
            Store store1 = new Store();
            store1.setSalesNumber(s1);
            store1.setId(goodsId);
            storeMapper.updateSalesNumber(store1);
        }
        //根据结算单号删除订单
        orderMapper.deleteByCheckId(checkId);
        //退还金额（模拟）
        checkVO.setMsg("退款将在1-2小时内原路返还");
        return checkVO;
    }

    //根据商品id退货
    @Override
    public Check returns(String id, String count) {


        return null;
    }

    //商品加入购物车
    @Override
    public Check addCar(Check check) {

        //获取商品id，判断库存
        String goodsId = check.getGoodsId();
        Store store = storeMapper.findById(goodsId);
        if (store == null) {
            check.setMsg("该商品已下架");
            return check;
        } else {
            String salesNumber = store.getSalesNumber();
            if (salesNumber.equals("0")) {
                check.setMsg("库存不足");
                return check;
            }
        }
        //查询数据库，看该商品是否已存在购物车
        ShoppingCart byGoodsName = shoppingCartMapper.findByGoodsName(check.getAccountId(), goodsId);
        ShoppingCart shoppingCart = new ShoppingCart();
        if (byGoodsName == null) {
            shoppingCart.setId(UUID.getUUID());
            shoppingCart.setShoppingCartId(check.getAccountId());
            shoppingCart.setImg(check.getImg());
            shoppingCart.setGoodsName(check.getGoodsName());
            shoppingCart.setGoodsId(check.getGoodsId());
            shoppingCart.setSalesPrice(check.getSalesPrice());
            shoppingCart.setCounts(check.getCount());
            shoppingCart.setStoreName("太平建材市场");
            int i = shoppingCartMapper.addToCart(shoppingCart);
            if (i == 1) {
                check.setMsg("添加成功!");
            } else {
                check.setMsg("添加失败!");
            }
        } else {
            //该商品已存在购物车中，修改购物车中商品数量
            Integer count = Integer.valueOf(byGoodsName.getCounts());
            //每次点击count数量加一
            Integer num = count + 1;
            ShoppingCart cart = new ShoppingCart();
            cart.setCounts(String.valueOf(num));
            cart.setGoodsId(check.getGoodsId());
            int i = shoppingCartMapper.updateAddCount(cart);
            if (i == 1) {
                check.setMsg("添加成功！");
            } else {
                check.setMsg("添加失败！");
            }
        }

        return check;
    }

    //结算页面列表渲染,根据员工的账号id查询
    @Override
    public List<ShoppingCart> settlementListRendering(String accountId) {

        List<ShoppingCart> list = shoppingCartMapper.findAllByShoppingCartId(accountId);
        return list;
    }

    //结算页面，根据id批量删除
    @Override
    public ShoppingCart batchDeletionById(List<ShoppingCart> list) {
        ShoppingCart shoppingCart = new ShoppingCart();
        int i = shoppingCartMapper.batchDelete(list);
        if (i == 1) {
            shoppingCart.setMsg("删除成功！");
        } else {
            shoppingCart.setMsg("删除失败，您选择的商品已不在购物车！");
        }
        return shoppingCart;
    }
}
