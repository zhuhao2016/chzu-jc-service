package cn.chzu.buildingmaterials.user.service;

import cn.chzu.buildingmaterials.user.dao.UserMapper;
import cn.chzu.buildingmaterials.user.model.User;
import cn.chzu.conf.util.UUID;
import cn.chzu.conf.util.md5.KEMD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 17:10
 * @version: 1.0.0
 * @modified By:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User create(User user) {

        String username = user.getUsername();
        String id = user.getId();
        String psw = user.getPassword();
        if (username == null || username.isEmpty()) {
            user.setMsg("用户创建失败，请正确填写信息！");
            return user;
        }

        //查询数据库，不可以用户名重复
        User userName = userMapper.findUserName(username);
        if (userName != null) {
            user.setMsg("该用户已存在，请重新输入！");
            return user;
        }
        if (id == null || id.isEmpty()) {
            user.setId(UUID.getUUID());
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        String time = sd.format(new Date());
        //输出当前时间
        System.out.println("输出当前时间:" + time);
        user.setCreateTime(time);
        user.setRoleId("管理员");
        //密码MD5加密
        user.setPassword(KEMD5Utils.MD5(psw));
        //设置默认用户头像
        user.setAvatar("1.jpg");
        int i = userMapper.create(user);
        if (i == 1) {
            user.setMsg("用户创建成功!");
        } else {
            user.setMsg("用户创建失败，请正确填写信息！");
        }

        return user;
    }

    //后台用户登录
    @Override
    public User login(User user) {

        String psw = user.getPassword();
        //密码MD5加密后赋值
        user.setPassword(KEMD5Utils.MD5(psw));
        User login = userMapper.login(user);

        if (login != null) {
            login.setMsg("登录成功！");
            return login;
        } else {
            user.setMsg("用户名或密码错误！");
            return user;
        }
    }


}
