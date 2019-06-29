package cn.meeler.test.http;

import cn.meeler.test.controller.qcc.Md5Utils;
import cn.meeler.test.utils.DateFormatUtils;
import cn.meeler.test.utils.HttpClientHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GZYtest {

    @Test
    public void getReasons() throws IOException {
        HttpGet httpGet = new HttpGet("http://192.168.51.43:53002/forensics/certification/reasons");
        httpGet.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpGet.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        String res = toGetRequest(httpGet);
        System.out.println(res);
    }

    /**
     * 企业注册
     */
    @Test
    public void companyRegister() throws IOException {
        HttpPost httpPost = new HttpPost("http://192.168.51.43:53002/forensics/realName/company/create");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        httpPost.setHeader("Content-Type","application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","深圳市墨者安全科技有限公司");
        jsonObject.put("cardType","126");
        jsonObject.put("businessLicenseId","91440300MA5EYKXC1L");
        jsonObject.put("licenseFileType","jpg");
        jsonObject.put("licenseFileBase64",ImageUtil.fileToString("D:/tupian/yyzz.jpg"));
        jsonObject.put("legalPersonName","舒镇");
        jsonObject.put("legalPersonCardNo","210211197812265815");
        jsonObject.put("linkerName","张中全");
        jsonObject.put("linkerPhone","18675528764");
        jsonObject.put("contactsName","耿忠尧");
        jsonObject.put("contactsPhone","13510330150");
        jsonObject.put("contactsCardNo","522425198408160016");
        jsonObject.put("contactsCardType","111");
        jsonObject.put("frontCardFileBase64",ImageUtil.fileToString("D:/tupian/yao_z.jpg"));
        jsonObject.put("frontCardFileType","jpg");
        jsonObject.put("reverseCardFileBase64",ImageUtil.fileToString("D:/tupian/yao_f.jpg"));
        jsonObject.put("reverseCardFileType","jpg");
        String re = toRequest(httpPost, jsonObject);
        System.out.println(re);
    }

    /**
     * 个人注册
     * @throws IOException
     */
    @Test
    public void ownerRegister() throws IOException {
        HttpPost httpPost = new HttpPost("http://192.168.51.43:53002/forensics/realName/self/create");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        httpPost.setHeader("Content-Type","application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","廖承勇");
        jsonObject.put("cardType","111");
        jsonObject.put("cardNo","330781198509079751");
        jsonObject.put("moblie","18518027080");
        jsonObject.put("frontCardFileBase64",ImageUtil.fileToString("D:/tupian/cy_z.jpg"));
        jsonObject.put("frontCardFileType","jpg");
        jsonObject.put("reverseCardFileBase64",ImageUtil.fileToString("D:/tupian/cy_f.jpg"));
        jsonObject.put("reverseCardFileType","jpg");
        String result = toRequest(httpPost, jsonObject);
        System.out.println(result);
    }

    @Test
    public void setNotifyUrl() throws IOException {
        HttpPost httpPost=new HttpPost("http://localhost:51111/result/mohzeUrl");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("url","http://paas.mozheanquan.cn:60050/forensics/result/notify"));
//        form.add(new BasicNameValuePair("url","http://paas.mozheanquan.cn:53002/forensics/result/notify"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        httpPost.setEntity(entity);
        HttpClient httpClient = HttpClientHelper.getInstance();
        HttpResponse response = httpClient.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity());
        System.out.println(string);
    }


    @Test
    public void createEvidence() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/evidence/create");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("comment","mozhe_ceshi0614==="));
        form.add(new BasicNameValuePair("url","www.baidu.com"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost,entity);
        System.out.println(result);
    }

    @Test
    public void comfirmEvidence() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/evidence/comfirm");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("evidId","ea46fe9da54e4f48bbc75edd584e96e3"));
        form.add(new BasicNameValuePair("evidName","hhh"));
        form.add(new BasicNameValuePair("orderNum","196204035471179776"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost,entity);
        System.out.println(result);
    }

    @Test
    public void downloadEvidence() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/evidence/download");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("evidId","79500a64378643daab113d0d94afb7e9"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost,entity);
        System.out.println(result);
    }

    @Test
    public void downloadGurading() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/evidence/guardian/download");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("storageNo","20190613190416104322930"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost,entity);
        System.out.println(result);
    }

    @Test
    public void applyCertification() throws IOException {
        HttpPost httpPost = new HttpPost("http://192.168.51.43:53002/forensics/certification/apply/paper");
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId","ff8080816b1ca674016b2140f4990188");
        jsonObject.put("evidIds","5614852b9f3d4abba79b28d3e9a71039");
        jsonObject.put("address","524414419@qq.com");
        jsonObject.put("comments","哈哈哈哈哈");
        jsonObject.put("orderType","0");
        jsonObject.put("categoryId","4");
        jsonObject.put("categoryName","维权");
        JSONArray array = new JSONArray();
        JSONObject a = new JSONObject();
        a.put("reasonId",10);
        a.put("reasonName","著作权");
        array.add(a);
        jsonObject.put("reasonList",array);
        String result = toRequest(httpPost, jsonObject);
        System.out.println(result);
    }

    @Test
    public void upload() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/certification/upload");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        FileBody fileBody = new FileBody(new File("D:/aaa/1212.png"));
        multipartEntityBuilder.addPart("file", fileBody);
        multipartEntityBuilder.addPart("type", new StringBody("31100", ContentType.create("text/plain", Consts.UTF_8)));
        multipartEntityBuilder.addPart("orderNum", new StringBody("190626104752001", ContentType.create("text/plain", Consts.UTF_8)));
        multipartEntityBuilder.addPart("md5", new StringBody(Md5Utils.fileMd5(new File("D:/aaa/1212.png")), ContentType.create("text/plain", Consts.UTF_8)));
        multipartEntityBuilder.addPart("userId", new StringBody("ff8080816b8d48b6016b91983adf07fe", ContentType.create("text/plain", Consts.UTF_8)));
        HttpEntity httpEntity = multipartEntityBuilder.build();
        String result = toFormRequst(httpPost, httpEntity);
        System.out.println(result);
    }

    @Test
    public void cancel() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/certification/cancel");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("orderNum","190628102744001"));
        form.add(new BasicNameValuePair("userId","ff8080816b1ca674016b2140f4990188"));
        form.add(new BasicNameValuePair("cancelReason","hahahhaha"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost, entity);
        System.out.println(result);
    }
    @Test
    public void stastic() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.2.65:51111/stastics/evidence/day");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("userId","158279518937677824"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost, entity);
        System.out.println(result);
    }

    /**
     *     private String signTime;
     *     private String sign;
     *     private String appId;
     *     private String version;
     *     private String eventType;
     *     private String orderNum;
     *     //申办状态 eventType = 60;
     *     private String orderStatus;
     *     private String updateTime;
     *     private String rejectTime;
     *     private String stopTime;
     *     private JSONArray notarialDeedInfo;
     *     private String charge;
     *     private String originalCharge;
     *     private String copies;
     *     private String copyCharge;
     *     private String firstCopySurcharge;
     */
    @Test
    public void notifymohze() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/result/notify");
        JSONObject jsonObject = new JSONObject();
        String signTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        jsonObject.put("signTime", signTime);
        String md5 = Md5Utils.encodeMd5("2883ff3c18ad38dab12cc4450b3a401a" + signTime).toLowerCase();
        jsonObject.put("sign",md5);
        jsonObject.put("appId","20190516@mozhes");
        jsonObject.put("version","v2.0");
        jsonObject.put("eventType","60");
        jsonObject.put("orderNum","190616203449001");
        jsonObject.put("orderStatus","1");
        jsonObject.put("updateTime",DateFormatUtils.format(new Date(),"yyyyMMddHHmmss"));
        JSONArray notarialDeedInfos = new JSONArray();
        JSONObject notarialDeedInfo = new JSONObject();
        notarialDeedInfo.put("notarialDeedNo","fsdfsfdasfas234242342");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("sdfsadsfsadfasfas");
        jsonArray.add("dfsadfasfasfsadfas");
        notarialDeedInfo.put("evidIdList",jsonArray);
        notarialDeedInfo.put("notaryTime",DateFormatUtils.format(new Date(),"yyyyMMddHHmmss"));
        notarialDeedInfos.add(notarialDeedInfo);
        jsonObject.put("notarialDeedInfo",notarialDeedInfos);
        jsonObject.put("charge","999.99");
        String result = toRequest(httpPost, jsonObject);
        System.out.println(result);
    }

    @Test
    public void status() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/certification/status");
        httpPost.setHeader("appid","a07ba5b8a16e441b9ba47bda13828c1c");
        httpPost.setHeader("secretKey","Mzc1OWJlNzE2NjRlNDFiNGIyNjU2OGY0ZjU0MDQ1YTM=");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("userId","ff8080816b1ca674016b2140f4990188"));
        form.add(new BasicNameValuePair("orderNum","190628091407001"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost, entity);
        System.out.println(result);
    }

    @Test
    public void export() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/forensics/certification/export");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("userID","1"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost, entity);
        System.out.println(result);
    }



    private String toFormRequst(HttpPost httpPost, HttpEntity entity) throws IOException {
        httpPost.setEntity(entity);
        HttpClient httpClient = HttpClientHelper.getInstance();
        HttpResponse response = httpClient.execute(httpPost);
        String string = EntityUtils.toString(response.getEntity());
        return string;
    }


    private String toRequest(HttpPost httpPost,JSONObject jsonObject) throws IOException {
        HttpClient httpClient = HttpClientHelper.getInstance();
        httpPost.setEntity(new StringEntity(JSON.toJSONString(jsonObject),ContentType.APPLICATION_JSON));
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity);
        return result;
    }
    private String toGetRequest(HttpGet httpGet) throws IOException {
        HttpClient httpClient = HttpClientHelper.getInstance();
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity);
        return result;
    }

    @Test
    public void qcc() throws IOException {
        HttpPost httpPost=new HttpPost("http://localhost:51110/icpQuery");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("domian","aliyun.com"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost, entity);
        System.out.println(result);
    }

    @Test
    public void opertions() throws IOException {
        HttpPost httpPost=new HttpPost("http://192.168.51.43:53002/operations/gzy/callback/url");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("url","http://paas.mozheanquan.cn:53002/forensics/result/notify"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
        String result = toFormRequst(httpPost, entity);
        System.out.println(result);
    }

    @Test
    public void meelerTest(){
        boolean b = 0.0f <= 0;
        System.out.println(b);
    }


}
