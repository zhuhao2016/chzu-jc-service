package cn.chzu.buildingmaterials.user.web;

import cn.chzu.buildingmaterials.user.model.User;
import cn.chzu.buildingmaterials.user.service.UserService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 16:58
 * @version: 1.0.0
 * @modified By:
 */
@RestController
@RequestMapping("/buildingmaterials_user")
public class UserController {

    @Autowired
    UserService userService;
    // 增加
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<User> updateFlowForm(HttpServletRequest request, @RequestBody ReqObject<User> data) {

        User dataObject = data.getObject();
        User user = userService.create(dataObject);
        return new ResObject<>(null,user );

    }

}
