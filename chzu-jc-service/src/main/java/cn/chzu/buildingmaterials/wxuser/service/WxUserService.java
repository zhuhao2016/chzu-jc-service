package cn.chzu.buildingmaterials.wxuser.service;


import cn.chzu.buildingmaterials.wxuser.model.WxUser;
import cn.chzu.buildingmaterials.wxuser.model.WxUserVO;
import cn.chzu.conf.util.aliyun.CodeVo;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/25 13:34
 * @version: 1.0.0
 * @modified By:
 */
public interface WxUserService {

    //注册账号
    public WxUser registered(WxUser wxUser);

    //用户账号登录
    public WxUser login(WxUser wxUser);

    //验证手机号码
    public CodeVo verification(String phone);

    //忘记密码
    public WxUser forgetPassword(WxUser wxUser);

    //微信授权登录
    public WxUser WeChatAuthorization(WxUserVO wxUserVO);
}
