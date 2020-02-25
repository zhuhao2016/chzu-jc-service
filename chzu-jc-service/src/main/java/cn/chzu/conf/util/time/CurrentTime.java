package cn.chzu.conf.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 获取当前时间工具类
 * @author: zhu_hao
 * @date: Created in 2020/2/25 14:29
 * @version: 1.0.0
 * @modified By:
 */
public class CurrentTime {

    //获取当前时间("yyyy-MM-dd HH:mm:ss")
    public static String newTime() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        String time = sd.format(new Date());
        //输出当前时间
        System.out.println("当前时间:" + time);
        return time;
    }



}
