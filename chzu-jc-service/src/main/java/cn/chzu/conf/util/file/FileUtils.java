package cn.chzu.conf.util.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @description: 图片上传工具类
 * @author: zhu_hao
 * @date: Created in 2020/2/23 12:02
 * @version: 1.0.0
 * @modified By:
 */
public class FileUtils {

    public static String saveFile(MultipartFile file) {

        if (file.isEmpty()) {
            return null;
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
        //String path = "/Users/lujun/image/";
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
            //上传成功，返回文件名
            return dFileName;
        } catch (IOException e) {
            e.printStackTrace();
            //上传失败，返回空
            return "null";
        }

    }
}
