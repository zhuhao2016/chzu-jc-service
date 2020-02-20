package cn.chzu.buildingmaterials.member.service;


import cn.chzu.buildingmaterials.member.dao.MemberMapper;
import cn.chzu.buildingmaterials.member.model.Member;
import cn.chzu.conf.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/15 14:15
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    // 新增会员
    @Override
    public Member add(Member member) {

        String id = member.getId();
        String vipName = member.getVipName();

        //会员名称不能为空
        if (vipName == null || vipName.isEmpty()) {
            member.setMsg("会员名称不能为空!");
            System.out.println(member.getMsg());
            return member;

        }

        //根据会员名称查询数据库，看是否已有该商品
        Member name = memberMapper.findVipName(vipName);
        if (name != null) {
            member.setMsg("该会员名称已存在，请重新输入！");

        } else {
            if (id == null || id.isEmpty()) {
                member.setId(UUID.getUUID());
            }
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取当前时间
            String time = sd.format(new Date());
            //输出当前时间
            System.out.println("输出当前时间:" + time);
            member.setCreateAt(time);
            //设置会员默认启动
            member.setStatus("1");
            //设置店铺
            member.setShopStore("太平建材市场");
            memberMapper.add(member);
            member.setMsg("成功新增会员: " + vipName);
        }
        System.out.println(member.getMsg());
        return member;
    }

    //查询所有会员
    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    //修改会员信息
    @Override
    public Member updateById(Member member) {

        int i = memberMapper.updateById(member);
        if (i == 1) {
            member.setMsg("修改成功！");
        } else {
            member.setMsg("修改失败！");
        }

        return member;
    }

    // 删除单个会员
    @Override
    public Member delete(String id) {
        Member member = new Member();
        int delete = memberMapper.delete(id);
        if (delete == 1) {
            member.setMsg("删除成功！");
        } else {
            member.setMsg("删除失败！");
        }

        return member;
    }

    //模糊查询
    @Override
    public List<Member> findByLinks(String links) {

        return memberMapper.findByLinks(links);
    }

    //启用停用会员
    @Override
    public Member updateStatus(Member member) {

        Member member1 = memberMapper.updateStatus(member);
        String status = member1.getStatus();
        String s;
        if (status.equals("1")) {
            s = "启用";
        } else {
            s = "停用";
        }
        if (member1 != null) {
            member.setMsg("会员状态修改成功！且当前状态为：" + s);
        } else {
            member.setMsg("会员状态修改失败！");
        }
        return member;
    }
}
