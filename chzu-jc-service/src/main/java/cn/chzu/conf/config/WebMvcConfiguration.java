package cn.chzu.conf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 配置类
 * @author: zhu_hao
 * @date: Created in 2020/2/13 19:00
 * @version: 1.0.0
 * @modified By:
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:D://image/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
