package cn.meeler.test.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class HttpClientHelper {
    public static HttpClient getInstance(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient;
    }
    public static void main(String[] args) throws IOException {
        HttpPost httppost=new HttpPost("http://192.168.51.43:53002/tortsearch/addTortSearchByUpload");
        File file = new File("D:/tupian/aaa.jpg");
        FileBody bin = new FileBody(file);
        StringBody appid = new StringBody("f76728f8cda04290860bea75c2c64d01", ContentType.create("text/plain", Consts.UTF_8));
        StringBody pictureId = new StringBody(UUID.randomUUID().toString(), ContentType.create("text/plain", Consts.UTF_8));
        StringBody type = new StringBody("1", ContentType.create("text/plain", Consts.UTF_8));

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", bin)
                // 相当于<input type="text" name="userName" value=userName>
                .addPart("appid", appid)
                .addPart("pictureId", pictureId)
                .addPart("type ",type)
                .build();
        httppost.setEntity(reqEntity);
        httppost.addHeader("appid", "f76728f8cda04290860bea75c2c64d01");
        httppost.addHeader("SecretKey", "MzBhMzljNjM5MDBkNDE4MmFiNjRiNTBkZDQyODYxMWQ=");
        HttpClient httpClient = HttpClientHelper.getInstance();
        HttpResponse response = httpClient.execute(httppost);
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        System.out.println(entity);
        System.out.println(statusCode);
        System.out.println(response);
    }
}
