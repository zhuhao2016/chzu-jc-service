package cn.chzu.conf.util.aliyun;

/**
 * @param
 * @author zhu_hao
 * @Title 验证码生成共工具类
 * @description 用于生成手机6位数字的验证码
 * @date 2020/2/25 16:42
 * @return
 */

public class RandomCode {

    /**
     * 生成验证码的方法
     *
     * @return
     */
    public static String genCode() {
        // 199999  nums = [0,9]
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        return code + "";
    }

    public static void main(String[] args) {
        System.out.println(RandomCode.genCode());
    }
}
