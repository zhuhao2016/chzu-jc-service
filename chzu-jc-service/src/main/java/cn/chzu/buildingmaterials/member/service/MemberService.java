package cn.chzu.buildingmaterials.member.service;


import cn.chzu.buildingmaterials.member.model.Member;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/15 14:14
 * @version: 1.0.0
 * @modified By:
 */
public interface MemberService {

    // 新增会员
    public Member add(Member member);

    //查询所有会员信息
    public List<Member> findAll();

    //根据id修改
    public Member updateById(Member member);

    // 删除单个会员
    public Member delete(String id);

    // 模糊查询
    public List<Member> findByLinks(String links);

    //启用停用会员
    public Member updateStatus(Member member);


}
