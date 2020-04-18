package cn.chzu.buildingmaterials.user.service;

import cn.chzu.buildingmaterials.user.model.User;

import java.util.List;

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

    //查询所有后台用户信息
    public List<User> findAllUser();

    //根据id修改
    public User updateById(User user);

    // 模糊查询
    public List<User> findByLinks(String links);

    // 删除
    public int delete(String id);
}
