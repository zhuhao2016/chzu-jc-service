package cn.chzu.buildingmaterials.user.web;

import cn.chzu.buildingmaterials.user.model.User;
import cn.chzu.buildingmaterials.user.service.UserService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 16:58
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.user.model.User>
     * @Title create
     * @description 新建后台管理用户
     * @author zhu_hao
     * @date 2020/2/9 18:16
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<User> create(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<User> data) {

        User dataObject = data.getObject();
        User user = userService.create(dataObject);
        return new ResObject<>(null, user);

    }

    /**
     * @param request
     * @param response
     * @param session
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.user.model.User>
     * @Title login
     * @description 后台用户登录
     * @author zhu_hao
     * @date 2020/2/8 17:46
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<User> login(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody ReqObject<User> data) {

        User dataObject = data.getObject();
        User user = userService.login(dataObject);
        session.setAttribute("id", user.getId());
        session.setAttribute("username", user.getUsername());
        return new ResObject<>(null, user);

    }


}
