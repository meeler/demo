package cn.meeler.test.http;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificationApiTest {
    private static final Logger logger = LoggerFactory.getLogger(CertificationApiTest.class);

    @Test
    public void testUpload() {
        try {
            File materialFile = new File("D:/aaa/yw_f.jpg");

            Map<String, String> params = new HashMap<>();
            params.put("appId", "20190516@mozhes");
            params.put("signTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            params.put("signType", "MD5");
            params.put("appType", "402");
            params.put("deviceType", "4");
            params.put("version", "v2.0");
            params.put("userId", "ff8080816b1ca674016b2140f4990188");
            params.put("orderNum", "190604171143001");
            params.put("fileName", materialFile.getName());
            params.put("fileLength", String.valueOf(materialFile.length()));
            params.put("materialCode", "31100");
            params.put("fileMd5", getFileMd5(materialFile));
            String sign = sign(params, "2883ff3c18ad38dab12cc4450b3a401a", StandardCharsets.UTF_8);
            params.put("sign", sign);

            HttpClient httpClient = HttpClients.createDefault();
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                multipartEntityBuilder = multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue());
            }
            multipartEntityBuilder = multipartEntityBuilder.addBinaryBody("fileContent", materialFile).setMode(HttpMultipartMode.RFC6532);
            HttpPost httpPost = new HttpPost("http://218.98.113.88:42014/third-party-api/api/certification/upload.do");
            httpPost.setHeader("Content-Type", "multipart/form-data");
            httpPost.setEntity(multipartEntityBuilder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileMd5(File file) throws IOException {
        return DigestUtils.md5Hex(FileUtils.readFileToByteArray(file));
    }

    public static String sign(Map<String, String> params, String key, Charset inputCharset) {
        String text = createLinkString(params);
        return sign(text, key, inputCharset);
    }

    /**
     * 把数组所有元素排序，并按照“参数名=参数值”的模式用“&”字符拼接成字符串<br>
     * 其中sign、空值（<code>""</code>/<code>null</code>）不加入签名
     * @param params 需要排序并参与字符拼接的参数Map
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        if (null == params) {
            throw new IllegalArgumentException("参数Map不能为空");
        }
        List<String> keys = new ArrayList<>(params.keySet());
        // 排序
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (key.equalsIgnoreCase("sign")) {
                continue;
            }
            String value = params.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            prestr.append(key).append("=").append(value);
            prestr.append("&");
        }
        prestr.deleteCharAt(prestr.length() - 1);
        return prestr.toString();
    }

    /**
     * 计算签名
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param inputCharset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, Charset inputCharset) {
        logger.info("text: " + text);
        logger.info("key: " + key);
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("待签名的字符串不能为空");
        }
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("秘钥[key]不能为空");
        }
        String toSignStr = text + key;
        logger.info("toSignStr: " + toSignStr);
        return DigestUtils.md5Hex(toSignStr.getBytes(inputCharset));
    }
}
