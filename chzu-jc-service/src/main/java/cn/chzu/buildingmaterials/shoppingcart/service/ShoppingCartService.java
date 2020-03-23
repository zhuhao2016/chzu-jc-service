package cn.chzu.buildingmaterials.shoppingcart.service;


import cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart;
import cn.chzu.buildingmaterials.storegoods.model.StoreVo;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/29 13:51
 * @version: 1.0.0
 * @modified By:
 */
public interface ShoppingCartService {

    //添加商品到购物车
    public ShoppingCart addToCart(ShoppingCart shoppingCart);

    //批量删除购物车商品
    public ShoppingCart batchDelete(List<ShoppingCart> shoppingCartList);

    //查询购物车
    public List<ShoppingCart> findAllByShoppingCartId(String shoppingCartId);

    //首页推荐
    public List<StoreVo> recommend();
}
