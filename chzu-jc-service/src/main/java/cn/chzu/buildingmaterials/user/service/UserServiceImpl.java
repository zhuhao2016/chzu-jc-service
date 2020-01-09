package cn.chzu.buildingmaterials.user.service;

import cn.chzu.buildingmaterials.user.dao.UserMapper;
import cn.chzu.buildingmaterials.user.model.User;
import cn.chzu.conf.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

        String id = user.getId();
        if( id == null || id.isEmpty() ){
            user.setId( UUID.getUUID() );
        }

        int i = userMapper.create(user);
        System.out.println(i);

        return user;
    }
}
