package cn.chzu.conf.util.word;



import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Desc 下载Word工具类
 */
public class DownloadUtil {
    /**
     * @param byteArrayOutputStream 将文件内容写入ByteArrayOutputStream
     * @param response              HttpServletResponse 写入response
     * @param returnName            返回的文件名
     */
    public static void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response,
                                String returnName) throws IOException {
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename=" + returnName);
        response.setContentLength(byteArrayOutputStream.size());
        OutputStream outputstream = response.getOutputStream(); // 取得输出流
        byteArrayOutputStream.writeTo(outputstream); // 写到输出流
        byteArrayOutputStream.close(); // 关闭
        outputstream.flush(); // 刷数据
    }
}

