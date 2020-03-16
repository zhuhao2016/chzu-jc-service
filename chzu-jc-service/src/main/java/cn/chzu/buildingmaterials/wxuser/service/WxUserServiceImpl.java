package cn.chzu.buildingmaterials.wxuser.service;


import cn.chzu.buildingmaterials.wxuser.dao.WxUserMapper;
import cn.chzu.buildingmaterials.wxuser.model.WxUser;
import cn.chzu.buildingmaterials.wxuser.model.WxUserVO;
import cn.chzu.conf.util.UUID;
import cn.chzu.conf.util.aliyun.CodeVo;
import cn.chzu.conf.util.aliyun.RandomCode;
import cn.chzu.conf.util.aliyun.SmsUtils;
import cn.chzu.conf.util.md5.KEMD5Utils;
import cn.chzu.conf.util.time.CurrentTime;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/25 13:36
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    WxUserMapper wxUserMapper;

    //注册账号
    @Override
    public WxUser registered(WxUser wxUser) {

        String username = wxUser.getUserName();
        String id = wxUser.getId();
        String psw = wxUser.getPassWord();
        String msg = wxUser.getMsg();
        String phone = wxUser.getPhone();
        if (username == null || username.isEmpty()) {
            wxUser.setMsg("请正确填写信息！");
            return wxUser;
        }

        //查询数据库，账号不可以重复
        WxUser wxUserName = wxUserMapper.findWxUserName(username);
        if (wxUserName != null) {
            wxUser.setMsg("该账号已存在，请重新输入！");
            return wxUser;
        }

        if (!msg.equals("true")) {
            wxUser.setMsg("验证码错误，请重新验证！");
            return wxUser;
        }
        //查询数据库，一个手机号只允许绑定一个账号
        WxUser wxUserMapperPhone = wxUserMapper.findPhone(phone);
        if (wxUserMapperPhone != null) {
            wxUser.setMsg("一个手机号只允许绑定一个账号");
            return wxUser;
        }
        //初始化账号唯一id
        if (id == null || id.isEmpty()) {
            wxUser.setId(UUID.getUUID());
        }
        //初始化用户唯一购物车id
        wxUser.setShoppingCartId("shoppingCar" + "." + UUID.getUUID());
        //获取当前时间
        String time = CurrentTime.newTime();
        //设置注册时间
        wxUser.setCreated(time);
        //密码MD5加密
        wxUser.setPassWord(KEMD5Utils.MD5(psw));
        //设置默认使用状态
        wxUser.setStatus("Y");
        wxUser.setIsMobileCheck("1");
        //设置登录方式
        wxUser.setLoginType("0");
        int i = wxUserMapper.registered(wxUser);
        if (i == 1) {
            wxUser.setMsg("注册成功!");
        } else {
            wxUser.setMsg("注册失败，请正确填写信息！");
        }
        return wxUser;
    }

    //用户账号登录
    @Override
    public WxUser login(WxUser wxUser) {

        String psw = wxUser.getPassWord();
        wxUser.setLoginType("0");
        //密码MD5加密后赋值
        wxUser.setPassWord(KEMD5Utils.MD5(psw));
        //设置最后登录时间,调用当前时间工具类获取当前时间
        String time = CurrentTime.newTime();
        wxUser.setLastLoginTime(time);
        //更新最后登录时间
        wxUserMapper.updateLastLoginTime(wxUser);
        WxUser login = wxUserMapper.login(wxUser);
        if (login != null) {
            login.setMsg("登录成功！");
            return login;
        } else {
            wxUser.setMsg("用户名或密码错误！");
            return wxUser;
        }
    }

    //验证码
    @Override
    public CodeVo verification(String phone) {

        CodeVo codeVo = new CodeVo();
        if (phone.length() != 11) {
            codeVo.setMsg("手机号应为11位数");
        } else {
            //手机号码正则表达式
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])| (17[0,1,3,5-8]))\\d{8}$");
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            System.out.println(isMatch);
            if (isMatch) {
                //随机生成六位数数字验证码
                String code = RandomCode.genCode();
                try {
                    codeVo = SmsUtils.sendSms(phone, code);
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            } else {
                codeVo.setMsg("手机号格式错误");
            }
        }
        return codeVo;

    }

    //忘记密码
    @Override
    public WxUser forgetPassword(WxUser wxUser) {

        String msg = wxUser.getMsg();
        if (!msg.equals("true")) {
            wxUser.setMsg("验证码错误!");
        } else {
            //密码MD5加密
            wxUser.setPassWord(KEMD5Utils.MD5(wxUser.getPassWord()));
            //验证后修改密码
            int i = wxUserMapper.forgetPassword(wxUser);
            if (i == 1) {
                wxUser.setMsg("密码修改成功！");
            } else {
                wxUser.setMsg("密码修改失败,请正确输入绑定的手机号码！");
            }
        }
        return wxUser;
    }

    //微信授权登录
    @Override
    public WxUser WeChatAuthorization(WxUserVO wxUserVO) {

        WxUser wxUser = new WxUser();
        String userName = wxUserVO.getNickName();
        String sex;
        if (wxUserVO.getGender().equals("1")) {
            sex = "男";
        } else {
            sex = "女";
        }
        wxUser.setUserName(userName);
        wxUser.setSex(sex);
        wxUser.setStatus("Y");
        wxUser.setLoginType("1");
        //判断是否是第一次授权
        WxUser weChatAuthorization = wxUserMapper.findWeChatAuthorization(wxUser);
        if (weChatAuthorization == null) {
            //首次授权，将基本信息写入数据库
            wxUser.setId(UUID.getUUID());
            //初始化购物车id
            wxUser.setShoppingCartId("shoppingCar" + "." + UUID.getUUID());
            //获取当前时间
            String time = CurrentTime.newTime();
            //设置首次授权时间
            wxUser.setCreated(time);
            //设置最后登录时间
            wxUser.setLastLoginTime(time);
            wxUserMapper.registered(wxUser);
            wxUser.setMsg("微信首次授权！");
        } else {
            //设置最后登录时间
            String time = CurrentTime.newTime();
            wxUser.setLastLoginTime(time);
            //更新最后登录时间
            wxUserMapper.updateLastLoginTime(wxUser);
            wxUser.setMsg("微信最后登录时间：" + wxUser.getLastLoginTime());
        }
        //获取用户基本信息
        WxUser w = wxUserMapper.findWeChatAuthorization(wxUser);
        w.setMsg(wxUser.getMsg());
        return w;
    }
}
