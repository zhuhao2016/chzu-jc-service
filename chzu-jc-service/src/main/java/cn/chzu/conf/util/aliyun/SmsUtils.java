package cn.chzu.conf.util.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 */
public class SmsUtils {

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)，非常重要。不要泄露给其他人
    static final String accessKeyId = "LTAI4FjcHSePKZXGrrMhvhVo";
    static final String accessKeySecret = "c5PUaFHT0w39dIs6IhRnp3Ou9IQAmG";

    /**
     * 发送手机短信的方法
     *
     * @param phone 手机号
     * @param code  验证码
     * @return
     * @throws ClientException
     */
    public static CodeVo sendSms(String phone, String code) throws ClientException {

        CodeVo codeVo = new CodeVo();

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        // ========================需要大家填写的内容开始======================================

        //必填:待发送手机号
        request.setPhoneNumbers(phone);

        //必填:短信签名-可在短信控制台中找到
        request.setSignName("太平建材市场系统");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_184220532");

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // ========================需要大家填写的内容结束======================================

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        // 创建Map集合，封装数据
        Map<String, Object> map = new HashMap();
        map.put("code", sendSmsResponse.getCode());
        map.put("message", sendSmsResponse.getMessage());
        map.put("requestId", sendSmsResponse.getRequestId());
        map.put("bizId", sendSmsResponse.getBizId());
        // 遍历map
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
        //将生成的验证码返回给前端
        codeVo.setCode(code);
        codeVo.setPhone(phone);
        if (sendSmsResponse.getCode().equals("OK")) {
            codeVo.setMsg("短信发送成功");
        } else {
            codeVo.setMsg("短信发送失败");
        }
        return codeVo;

    }

}
