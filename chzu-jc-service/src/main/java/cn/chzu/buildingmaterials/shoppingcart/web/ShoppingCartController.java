package cn.chzu.buildingmaterials.shoppingcart.web;


import cn.chzu.buildingmaterials.shoppingcart.model.ShoppingCart;
import cn.chzu.buildingmaterials.shoppingcart.service.ShoppingCartService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/29 13:52
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.shoppingcart.model.ShoppingCart>
     * @Title addToCart
     * @description 添加商品到购物车
     * @author zhu_hao
     * @date 2020/2/29 14:35
     */
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<ShoppingCart> addToCart(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<ShoppingCart> data) {

        ShoppingCart dataObject = data.getObject();
        ShoppingCart shoppingCart = shoppingCartService.addToCart(dataObject);
        return new ResObject<>(null, shoppingCart);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.shoppingcart.model.ShoppingCart>>
     * @Title batchDelete
     * @description 根据id批量删除购物车商品
     * @author zhu_hao
     * @date 2020/2/29 19:07
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<ShoppingCart> batchDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<List<ShoppingCart>> data) {

        List<ShoppingCart> dataObject = data.getObject();
        ShoppingCart shoppingCart = shoppingCartService.batchDelete(dataObject);
        return new ResObject<>(null, shoppingCart);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.shoppingcart.model.ShoppingCart>>
     * @Title findAllByShoppingCartId
     * @description 查询购物车列表
     * @author zhu_hao
     * @date 2020/2/29 20:08
     */
    @RequestMapping(value = "/findAllByShoppingCartId", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<ShoppingCart>> findAllByShoppingCartId(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<ShoppingCart> data) {

        String shoppingCartId = data.getObject().getShoppingCartId();
        List<ShoppingCart> all = shoppingCartService.findAllByShoppingCartId(shoppingCartId);
        return new ResObject<>(null, all);
    }
}
