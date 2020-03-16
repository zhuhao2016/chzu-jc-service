package cn.chzu.buildingmaterials.wxuser.web;


import cn.chzu.buildingmaterials.wxuser.model.WxUser;
import cn.chzu.buildingmaterials.wxuser.model.WxUserVO;
import cn.chzu.buildingmaterials.wxuser.service.WxUserService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import cn.chzu.conf.util.aliyun.CodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/25 13:38
 * @version: 1.o.o
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/wxuser")
public class WxUserController {

    @Autowired
    WxUserService wxUserService;

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.wxuser.model.WxUser>
     * @Title registered
     * @description 注册账号
     * @author zhu_hao
     * @date 2020/2/25 13:40
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<WxUser> registered(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<WxUser> data) {

        WxUser object = data.getObject();
        WxUser registered = wxUserService.registered(object);
        return new ResObject<>(null, registered);

    }

    /**
     * @param request
     * @param response
     * @param session
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.wxuser.model.WxUser>
     * @Title login
     * @description 用户账号登录
     * @author zhu_hao
     * @date 2020/2/25 14:25
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<WxUser> login(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody ReqObject<WxUser> data) {

        WxUser object = data.getObject();
        WxUser login = wxUserService.login(object);
        session.setAttribute("id", login.getId());
        session.setAttribute("userName", login.getUserName());
        session.setAttribute("shoppingCartId", login.getShoppingCartId());
        session.setAttribute("memberId", login.getMemberId());

        return new ResObject<>(null, login);

    }

    /**
     * @param request
     * @param response
     * @param session
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.conf.util.aliyun.CodeVo>
     * @Title verification
     * @description 获取验证码
     * @author zhu_hao
     * @date 2020/2/29 12:39
     */
    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<CodeVo> verification(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody ReqObject<WxUser> data) {

        String phone = data.getObject().getPhone();
        CodeVo verification = wxUserService.verification(phone);
        return new ResObject<>(null, verification);

    }

    /**
     * @param request
     * @param response
     * @param session
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.wxuser.model.WxUser>
     * @Title forgetPassword
     * @description 忘记密码
     * @author zhu_hao
     * @date 2020/2/29 12:59
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<WxUser> forgetPassword(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody ReqObject<WxUser> data) {

        WxUser dataObject = data.getObject();
        WxUser wxUser = wxUserService.forgetPassword(dataObject);
        return new ResObject<>(null, wxUser);

    }

    /**
     * @param request
     * @param response
     * @param session
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.wxuser.model.WxUser>
     * @Title WeChatAuthorization
     * @description 微信授权登录
     * @author zhu_hao
     * @date 2020/3/6 16:43
     */
    @RequestMapping(value = "/WeChatAuthorization", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<WxUser> WeChatAuthorization(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody ReqObject<WxUserVO> data) {

        WxUserVO object = data.getObject();
        WxUser wxUser = wxUserService.WeChatAuthorization(object);
        return new ResObject<>(null, wxUser);

    }

}
