package cn.chzu.buildingmaterials.wxuser.dao;


import cn.chzu.buildingmaterials.wxuser.model.WxUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/25 13:21
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface WxUserMapper {

    //注册账号
    public int registered(WxUser wxUser);

    //查询账号是否已存在
    public WxUser findWxUserName(String userName);

    //根据id查询用户信息
    public WxUser findAccountId(String id);

    //查询手机号是否已存在
    public WxUser findPhone(String phone);

    //用户账号登录
    public WxUser login(WxUser wxUser);

    // 根据userName修改最后登录时间
    public int updateLastLoginTime(WxUser wxUser);

    //忘记密码
    public int forgetPassword(WxUser wxUser);

    //查询微信授权账号是否已存在
    public WxUser findWeChatAuthorization(WxUser wxUser);

    //查询所有用户信息
    public List<WxUser> findAllWxUser();

}
