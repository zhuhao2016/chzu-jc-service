package cn.chzu.buildingmaterials.user.service;

import cn.chzu.buildingmaterials.user.model.User;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 17:05
 * @version: 1.0.0
 * @modified By:
 */
public interface UserService {

    public User create(User user);

    //后台用户登录
    public User login(User user);
}
