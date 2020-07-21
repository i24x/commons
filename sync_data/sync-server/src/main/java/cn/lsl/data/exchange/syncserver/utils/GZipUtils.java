package cn.lsl.data.exchange.syncserver.utils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;


/**
 * 压缩，解压类
 */
public class GZipUtils {

	private static Logger logger = LoggerFactory.getLogger(GZipUtils.class);

	private static String UTF8 = "UTF-8";
    /**
     * 压缩指定的字符串
     *
     * @param str
     * @return
     * @throws IOException
     */
    public static byte[] compress(String str) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes(UTF8));
        gzip.close();
        return out.toByteArray();
    }

    /**
     * 解压缩字节数组
     *
     * @param b
     * @return
     * @throws IOException
     */
    public static byte[] uncompress(byte[] b) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(b);
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    public static String byte2String(ResponseEntity<byte[]> response){
    	GZIPInputStream gzip = null;
    	BufferedReader reader = null;
    	StringWriter writer = null;
    	String result = null;
		try {
			gzip = new GZIPInputStream(new ByteArrayInputStream(response.getBody()));
			reader = new BufferedReader(new InputStreamReader(gzip));
			writer = new StringWriter();
			String line;
			while ((line = reader.readLine()) != null) {
			    writer.write(line);
			}
			result = writer.toString();
			logger.info("数据流转换成功");
			return result;
		} catch (IOException e) {
			logger.info("数据流转换失败");
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				gzip.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
    }
    
    // 测试方法
    public static void main(String[] args) throws IOException {

    }

}