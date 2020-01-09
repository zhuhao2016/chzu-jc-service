package cn.chzu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhu_hao
 * @Title
 * @description 服务启动类
 * @date 2020/1/8 15:39
 * @return
 */
@SpringBootApplication
public class AppService {

    public static void main(String[] args) {
        SpringApplication.run(AppService.class, args);
        System.out.println("***********项目启动成功**************");
    }

}
