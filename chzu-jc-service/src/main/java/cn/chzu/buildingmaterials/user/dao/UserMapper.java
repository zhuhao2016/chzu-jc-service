package cn.chzu.buildingmaterials.user.dao;


import cn.chzu.buildingmaterials.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 16:55
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface UserMapper {
    // 创建用户
    public int create(User user);

    //后台用户登录
    public User login(User user);

    //根据用户名查询
    public User findUserName(String goodsName);

    //查询所有后台用户信息
    public List<User> findAllUser();


}
