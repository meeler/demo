package cn.meeler.test.controller.icp;

import cn.meeler.test.controller.Meeler;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainWhoisInfoRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainWhoisInfoResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baidu.aip.ocr.AipOcr;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryICPByDomain {

    private static IAcsClient client = null;
    static {
        String regionId = "cn-hangzhou"; //必填固定值，必须为“cn-hanghou”
        String accessKeyId = "LTAI0IsJeZZFN6m8"; // your accessKey
        String accessKeySecret = "xYfL1G6sKJ4vEwfnqtwwTWsX2ojUSW";// your accessSecret
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }


    /**
     *   HttpPost httppost = new HttpPost("http://192.168.51.43:53002/tortsearch/addTortSearchByUpload");
     *         FileBody fb = new FileBody(file);
     *         StringBody appid = new StringBody("f76728f8cda04290860bea75c2c64d01", ContentType.create("text/plain", Consts.UTF_8));
     *         StringBody pictureId = new StringBody(UUID.randomUUID().toString(), ContentType.create("text/plain", Consts.UTF_8));
     *         StringBody type = new StringBody("1", ContentType.create("text/plain", Consts.UTF_8));
     *         HttpEntity reqEntity = MultipartEntityBuilder.create()
     *                 .addPart("file", fb)
     *                 .addPart("appid", appid)
     *                 .addPart("pictureId", pictureId)
     *                 .addPart("type ", type)
     *                 .build();
     *         httppost.setEntity(reqEntity);
     *         httppost.addHeader("appid", "f76728f8cda04290860bea75c2c64d01");
     *         httppost.addHeader("SecretKey", "MzBhMzljNjM5MDBkNDE4MmFiNjRiNTBkZDQyODYxMWQ=");
     *         try {
     *             HttpResponse response = httpClient.execute(httppost);
     *             int statusCode = response.getStatusLine().getStatusCode();
     *             if (statusCode != 200) {
     *                 throw new RuntimeException("侵权检测异常");
     *             }
     *         } catch (Throwable e) {
     *             throw new RuntimeException("远程侵权接口调用异常", e);
     *         }
     *         now api
     */
    @Test
    public void Test1() throws IOException {
        long start = System.currentTimeMillis();
        HttpGet httpGet = new HttpGet("http://api.k780.com/?app=domain.beian&domain=mozheanquan.cn&appkey=41918&sign=cfea509ed2ffe4693db1cf1de9700906&format=json");
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        JSONObject parse = (JSONObject) JSONObject.parse(s);
        System.out.println(parse);
        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.println(l);
    }

    /**
     * 聚合网
     * @throws IOException
     */
    @Test
    public void Test2() throws IOException {
        long start = System.currentTimeMillis();
        HttpGet httpGet = new HttpGet("http://api.juheapi.com/japi/beian?key=64573df82c4328b2aacc53363178e8c4&type=1&keyword=mozheanquan.cn&v=1.0");
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        JSONObject jsonObj = (JSONObject) JSONObject.parse(s);
        System.out.println(jsonObj);
        long end = System.currentTimeMillis();
        System.out.println(start - end);
    }

    @Test
    public void Test3() throws IOException {
        long start = System.currentTimeMillis();

        HttpGet httpGet = new HttpGet("http://alidns.aliyuncs.com/?Action=DescribeDomains&PageNumber=1&PageSize=20&KeyWord=com&GroupId=2223");
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        JSONObject jsonObj = (JSONObject) JSONObject.parse(s);
        System.out.println(jsonObj);
        long end = System.currentTimeMillis();
        System.out.println(start - end);
    }

    @Test
    public void test4(){
        DescribeDomainWhoisInfoRequest request = new DescribeDomainWhoisInfoRequest();
        request.setDomainName("baidu.com");
        DescribeDomainWhoisInfoResponse response ;
         try {
            response = client.getAcsResponse(request);
//            client.doAction()
            String expirationDate = response.getExpirationDate();
            System.out.println(expirationDate);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() throws IOException {
        long start = System.currentTimeMillis();
        String url = "http://apidata.chinaz.com/CallAPI/Domain?key=3572aa448739452a89e8a5bad5269e39&domainName=jd.com";
        JSONObject jsonObject =toRequest(url);
        long end = System.currentTimeMillis();
        System.out.println(end -start);
        System.out.println(jsonObject);
    }

    private JSONObject toRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        JSONObject jsonObj = (JSONObject) JSONObject.parse(s);
        return jsonObj;
    }

    @Test
    public void test6(){
        Meeler meeler = new Meeler();
        meeler.setName("meeler");
    }

//    @Test
//    public void test7() {
//        File imageFile = new File("C:/Users/mozhe/Desktop/code2.jpg");
//        Tesseract tessreact = new Tesseract();
//        tessreact.setDatapath("D:/resource/tesseract/tessdata");
//        tessreact.setLanguage("normal");
//        try {
//            String result = tessreact.doOCR(imageFile);
//            System.out.println(result);
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }
//    }

    @Test
    public void test8(){
        AipOcr client = new AipOcr("16109800", "kV17ODXXx0MZZVFGCAG4sLkG", "qVAVQsUShhAH5djLi4Z6wG6SxF85K5Wm");
        org.json.JSONObject jsonObject = client.basicGeneral("D:/PycharmProjects/Meeler/test/origin_imgs/code1.jpg", new HashMap<String, String>());
        System.out.println(jsonObject);
    }

    @Test
    public void test9() throws IOException {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("id","1"));
        form.add(new BasicNameValuePair("name","meeler"));
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/eee/test1");
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity res = response.getEntity();
        String s = EntityUtils.toString(res);
        System.out.println(s);
    }
}
