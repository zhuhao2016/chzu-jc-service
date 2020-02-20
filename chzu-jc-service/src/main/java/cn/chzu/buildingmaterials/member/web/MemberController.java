package cn.chzu.buildingmaterials.member.web;


import cn.chzu.buildingmaterials.member.model.Member;
import cn.chzu.buildingmaterials.member.service.MemberService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/2/15 14:15
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.member.model.Member>
     * @Title add
     * @description 新增会员
     * @author zhu_hao
     * @date 2020/2/15 14:51
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Member> add(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Member> data) {

        Member member = data.getObject();
        Member add = memberService.add(member);
        return new ResObject<>(null, add);
    }

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.member.model.Member>>
     * @Title findAllUser
     * @description 查询所有会员信息，分页展示
     * @author zhu_hao
     * @date 2020/2/15 15:05
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<Member>> findAll(HttpServletRequest request, HttpServletResponse response) {

        List<Member> allUser = memberService.findAll();
        return new ResObject<>(null, allUser);

    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.member.model.Member>
     * @Title updateById
     * @description 根据id修改会员信息
     * @author zhu_hao
     * @date 2020/2/15 15:18
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Member> updateById(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Member> data) {

        Member member = data.getObject();
        Member update = memberService.updateById(member);
        return new ResObject<>(null, update);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.member.model.Member>
     * @Title delete
     * @description 根据唯一id单个删除会员
     * @author zhu_hao
     * @date 2020/2/15 15:33
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Member> delete(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Member> data) {

        String id = data.getObject().getId();
        Member delete = memberService.delete(id);
        return new ResObject<>(null, delete);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.clothing.member.model.Member>>
     * @Title findByLinks
     * @description 根据会员名称模糊查询
     * @author zhu_hao
     * @date 2020/2/16 14:02
     */
    @RequestMapping(value = "/findByLinks", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<Member>> findByLinks(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Member> data) {

        String links = data.getObject().getLinks();
        List<Member> byLinks = memberService.findByLinks(links);
        return new ResObject<>(null, byLinks);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.member.model.Member>
     * @Title disableEnable
     * @description 启用停用会员
     * @author zhu_hao
     * @date 2020/2/20 12:44
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Member> updateStatus(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<Member> data) {

        Member member = data.getObject();
        Member updateStatus = memberService.updateStatus(member);
        return new ResObject<>(null, updateStatus);
    }
}
