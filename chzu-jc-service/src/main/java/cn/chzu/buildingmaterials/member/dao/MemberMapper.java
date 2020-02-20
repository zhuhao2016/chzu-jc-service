package cn.chzu.buildingmaterials.member.dao;


import cn.chzu.buildingmaterials.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/15 13:51
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
public interface MemberMapper {

    // 新增会员
    public int add(Member member);

    //根据vipName查询
    public Member findVipName(String vipName);

    //查询所有，分页展示
    public List<Member> findAll();

    // 根据id修改
    public int updateById(Member member);

    // 删除单个会员
    public int delete(String id);

    //模糊查询
    public List<Member> findByLinks(String links);

    //启用停用会员
    public int updateStatus(Member member);


}
