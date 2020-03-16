package cn.chzu.buildingmaterials.user.service;

import cn.chzu.buildingmaterials.user.dao.UserMapper;
import cn.chzu.buildingmaterials.user.model.User;
import cn.chzu.conf.util.UUID;
import cn.chzu.conf.util.md5.KEMD5Utils;
import cn.chzu.conf.util.time.CurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 17:10
 * @version: 1.0.0
 * @modified By:
 */

@Service
public class UserServiceImpl implements UserService {

    @Value("${com.image.path}")
    private String pathURL;

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
        //获取当前时间
        String time = CurrentTime.newTime();
        user.setCreateTime(time);
        //密码MD5加密
        user.setPassword(KEMD5Utils.MD5(psw));
        //设置默认用户头像
        user.setAvatar("1.jpg");
        user.setClassification("0");
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
            //设置头像访问路径
            login.setAvatar(pathURL + login.getAvatar());
            login.setMsg("登录成功！");

            return login;
        } else {
            user.setMsg("用户名或密码错误！");
            return user;
        }
    }

    //查询所有后台用户信息
    @Override
    public List<User> findAllUser() {

        return userMapper.findAllUser();
    }

    //根据id修改信息
    @Override
    public User updateById(User user) {

        String psw = user.getPassword();
        //密码MD5加密后赋值
        user.setPassword(KEMD5Utils.MD5(psw));
        int i = userMapper.updateById(user);
        if (i == 1) {
            user.setMsg("修改成功！");
        } else {
            user.setMsg("修改失败！");
        }

        return user;
    }

    //模糊查询
    @Override
    public List<User> findByLinks(String links) {

        return userMapper.findByLinks(links);
    }


}
