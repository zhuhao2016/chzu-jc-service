package cn.chzu.buildingmaterials.shoppingcart.service;


import cn.chzu.buildingmaterials.shoppingcart.dao.ShoppingCartMapper;
import cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart;
import cn.chzu.buildingmaterials.storegoods.dao.StoreMapper;
import cn.chzu.buildingmaterials.storegoods.model.Store;
import cn.chzu.buildingmaterials.storegoods.model.StoreVo;
import cn.chzu.buildingmaterials.wxuser.dao.WxUserMapper;
import cn.chzu.buildingmaterials.wxuser.model.WxUser;
import cn.chzu.conf.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/29 13:52
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    WxUserMapper wxUserMapper;

    @Autowired
    StoreMapper storeMapper;

    //添加商品到购物车
    @Override
    public ShoppingCart addToCart(ShoppingCart shoppingCart) {

        String goodsId = shoppingCart.getGoodsId();
        String accountId = shoppingCart.getAccountId();
        String id = shoppingCart.getId();
        //根据用户id查询该用户名信息
        WxUser wxUserName = wxUserMapper.findAccountId(accountId);
        //获取用户唯一购物车id
        String shoppingCartId = wxUserName.getShoppingCartId();

        //设置每个用户唯一购物车id
        shoppingCart.setShoppingCartId(shoppingCartId);
        //查询数据库，看该商品是否已存在购物车
        ShoppingCart byGoodsName = shoppingCartMapper.findByGoodsName(shoppingCartId, goodsId);
        if (byGoodsName == null) {

            //设置唯一id
            if (id == null || id.isEmpty()) {
                shoppingCart.setId(UUID.getUUID());
            }
            //添加商品到购物车
            shoppingCart.setCounts("1");
            int cartVO = shoppingCartMapper.addToCart(shoppingCart);
            if (cartVO == 1) {
                shoppingCart.setMsg("添加成功！");
            } else {
                shoppingCart.setMsg("添加失败！");
            }
        } else {
            //该商品已存在购物车中，修改购物车中商品数量
            Integer count = Integer.valueOf(byGoodsName.getCounts());
            //每次点击count数量加一
            Integer num = count + 1;
            shoppingCart.setCounts(String.valueOf(num));
            int i = shoppingCartMapper.updateAddCount(shoppingCart);
            if (i == 1) {
                shoppingCart.setMsg("添加成功！");
            } else {
                shoppingCart.setMsg("添加失败！");
            }
        }
        return shoppingCart;
    }

    //批量删除购物车商品
    @Override
    public ShoppingCart batchDelete(List<ShoppingCart> shoppingCartList) {

        ShoppingCart shoppingCart = new ShoppingCart();
        int i = shoppingCartMapper.batchDelete(shoppingCartList);
        if (i == 1) {
            shoppingCart.setMsg("删除成功！");
        } else {
            shoppingCart.setMsg("删除失败，您选择的商品已不在购物车！");
        }
        return shoppingCart;
    }

    //查询购物车
    @Override
    public List<ShoppingCart> findAllByShoppingCartId(String shoppingCartId) {

        List<ShoppingCart> all = shoppingCartMapper.findAllByShoppingCartId(shoppingCartId);
        return all;
    }

    //微信小程序首页推荐
    @Override
    public List<StoreVo> recommend() {


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
            list.add(storeVo);
        }
        return list;
    }
}
