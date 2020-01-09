package cn.chzu.buildingmaterials.user.dao;

import cn.chzu.buildingmaterials.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 16:55
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface UserMapper {
    // 增加
    public int create( User user);

}
