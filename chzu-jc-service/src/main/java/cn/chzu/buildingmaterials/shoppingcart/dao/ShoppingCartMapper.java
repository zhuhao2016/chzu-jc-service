package cn.chzu.buildingmaterials.shoppingcart.dao;


import cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/29 13:51
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface ShoppingCartMapper {

    //添加商品到购物车
    public int addToCart(ShoppingCart shoppingCart);

    //该商品已存在购物车中，添加修改购物车中商品数量，每次数量加一
    public int updateAddCount(ShoppingCart shoppingCart);

    //查询数据库，看该商品是否已存在购物车（根据购物车id和商品id查询）
    public ShoppingCart findByGoodsName(String shoppingCartId, String goodsId);

    //批量删除购物车商品
    public int batchDelete(List<ShoppingCart> shoppingCartList);

    //查询购物车
    public List<ShoppingCart> findAllByShoppingCartId(String shoppingCartId);

    //结算订单时删除单个购物车商品
    public int delete(String id);
}
