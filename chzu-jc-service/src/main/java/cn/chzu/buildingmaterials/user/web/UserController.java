package cn.chzu.buildingmaterials.user.web;

import cn.chzu.buildingmaterials.user.model.User;
import cn.chzu.buildingmaterials.user.service.UserService;
import cn.chzu.conf.common.ReqObject;
import cn.chzu.conf.common.ResObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 16:58
 * @version: 1.0.0
 * @modified By:
 */
@RestController
//解决跨域
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.user.model.User>
     * @Title create
     * @description 新建后台管理用户
     * @author zhu_hao
     * @date 2020/2/9 18:16
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<User> create(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<User> data) {

        User dataObject = data.getObject();
        User user = userService.create(dataObject);
        return new ResObject<>(null, user);

    }

    /**
     * @param request
     * @param response
     * @param session
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.clothing.user.model.User>
     * @Title login
     * @description 后台用户登录
     * @author zhu_hao
     * @date 2020/2/8 17:46
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<User> login(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody ReqObject<User> data) {

        User dataObject = data.getObject();
        User user = userService.login(dataObject);
        session.setAttribute("id", user.getId());
        session.setAttribute("username", user.getUsername());
        return new ResObject<>(null, user);

    }

    /**
     * @param file
     * @param request
     * @return java.lang.String
     * @Title saveFile
     * @description 图片上传/修改头像
     * @author zhu_hao
     * @date 2020/2/13 15:44
     */
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    @ResponseBody
    public String saveFile(@RequestParam("imgFile") MultipartFile file, HttpServletRequest request) {

        if (file.isEmpty()) {
            return "file is empty";
        }
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
       /* if (substring != ".jpg" || substring != ".png" || substring != ".gif") {
            return "上传文件格式错误！";
        }*/
        //保存的文件名
        String dFileName = UUID.randomUUID() + substring;
        System.out.println(dFileName);
        //windows保存路径
        String path = "D:/image/";
        //Mac保存路径
        //String path = "D:/image/";
        //生成保存文件
        File uploadFile = new File(path + dFileName);
        System.out.println(uploadFile);
        // 检测是否存在目录
        if (!uploadFile.getParentFile().exists()) {
            // 新建文件夹
            uploadFile.getParentFile().mkdirs();
        }
        //将上传文件保存到路径
        try {
            file.transferTo(uploadFile);
            return "上传成功！";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败！";
        }

    }

    /**
     * @param request
     * @param response
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.buildingmaterials.user.model.User>>
     * @Title findAllUser
     * @description 查询所有后台用户信息
     * @author zhu_hao
     * @date 2020/2/14 19:22
     */
    @RequestMapping(value = "/findAllUser", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<User>> findAllUser(HttpServletRequest request, HttpServletResponse response) {

        List<User> allUser = userService.findAllUser();
        //如果需要头像，使用增强for循环赋值
        return new ResObject<>(null, allUser);

    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<cn.chzu.buildingmaterials.user.model.User>
     * @Title updateById
     * @description 根据id修改密码，电话号码，性别
     * @author zhu_hao
     * @date 2020/3/16 15:41
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<User> updateById(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<User> data) {

        User object = data.getObject();
        User user = userService.updateById(object);
        return new ResObject<>(null, user);
    }

    /**
     * @param request
     * @param response
     * @param data
     * @return cn.chzu.conf.common.ResObject<java.util.List < cn.chzu.buildingmaterials.user.model.User>>
     * @Title findByLinks
     * @description 后台用户名模糊查询
     * @author zhu_hao
     * @date 2020/3/16 15:57
     */
    @RequestMapping(value = "/findByLinks", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<List<User>> findByLinks(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<User> data) {

        String links = data.getObject().getLinks();
        List<User> byLinks = userService.findByLinks(links);
        return new ResObject<>(null, byLinks);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResObject<Integer> delete(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqObject<User> data) {

        String id = data.getObject().getId();
        int i = userService.delete(id);
        return new ResObject<>(null, i);
    }
}
