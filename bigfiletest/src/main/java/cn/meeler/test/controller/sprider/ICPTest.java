package cn.meeler.test.controller.sprider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ICPTest {

    private HttpClient httpClient = HttpClients.createDefault();
    List<String> domains = new ArrayList<String>(Arrays.asList(new String[]{"aliyun.com", "mozheanquan.com", "jd.com", "taobao.com", "dangdang.com"}));

    @Test
    public void Test1() throws IOException {
        String host = "icp.chinaz.com";
        HttpGet httpGet = new HttpGet("http://" + host + "/" + domains.get((int) (Math.random() * (domains.size() - 1))));
        httpGetToInsertHeader(httpGet, host);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String context = EntityUtils.toString(entity);
        Document document = Jsoup.parse(context);
        Elements elements = document.select("ul[class='bor-t1s IcpMain01'] p");
        System.out.println(elements.toString());
        String gongsiName = elements.get(0).ownText();
        String type = elements.get(1).select("strong").text();
        String beianhao = elements.get(2).select("font").text();
        String wangzhanmingcheng = elements.get(3).ownText();
        String webHome = elements.get(4).ownText();

        System.out.println(gongsiName);
        System.out.println(type);
        System.out.println(beianhao);
        System.out.println(wangzhanmingcheng);
        System.out.println(webHome);
    }

    private void httpGetToInsertHeader(HttpGet httpGet, String host) {
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate");
        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.addHeader("Cache-Control", "no-cache");
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpGet.addHeader("Host", host);
        httpGet.addHeader("Upgrade-Insecure-Requests", "1");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
    }

    @Test
    public void Test2() throws IOException {
        String host = "whois.chinaz.com";
        HttpGet httpGet = new HttpGet("http://" + host + "/" + domains.get((int) (Math.random() * (domains.size() - 1))));
        httpGetToInsertHeader(httpGet, host);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String context = EntityUtils.toString(entity);
        Document document = Jsoup.parse(context);
        Elements elements = document.select("div[class='fr WhLeList-right']");
        Elements elements2 = document.select("li[class='clearfix bor-b1s ']");
        System.out.println(elements.toString());
        System.out.println(elements2.toString());
    }

    @Test
    public void Test3() throws IOException {
        String host = "www.alexa.cn";
        String domain = domains.get((int) (Math.random() * (domains.size() - 1)));
        HttpGet httpGet = new HttpGet("http://" + host + "/" + domain);
        httpGetToInsertHeader(httpGet, host);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String context = EntityUtils.toString(entity);
        Document document = Jsoup.parse(context);
        Elements elements = document.select("script");
        Element element = elements.get(3);
        String s = element.toString();
        String[] split = s.split(";");
        List<String> strings = Arrays.asList(split);
        List<String> strings1 = strings.subList(1, 5);
        List<String> tokens = new ArrayList<String>(2);
        for (int i = 0; i < strings1.size(); i++) {
            if (i == 0 || i == 2) {
                continue;
            }
            String[] split1 = strings1.get(i).split(":");
            String s2 = split1[1];
            String s3 = s2.substring(2, s2.length() - 10);
            System.out.println(s3);
            tokens.add(s3);
        }
        testWhois(tokens.remove(0), domain, host);
        testICP(tokens.remove(0), domain, host);

    }

    private void testWhois(String whoisToken, String domain, String host) throws IOException {
        String url = "http://www.alexa.cn/api/who_is/get?token=" + whoisToken + "&url=" + domain + "&force_update=0";
        HttpGet httpGet = new HttpGet(url);
        httpGetToInsertHeader(httpGet, host);
        httpGet.addHeader("Referer", "http://" + host + "/" + domain);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        JSONObject parse = (JSONObject) JSON.parse(EntityUtils.toString(entity));
        JSONObject data = (JSONObject) parse.get("data");
        System.out.println(data);

    }

    private void testICP(String icpToken, String domain, String host) throws IOException {
        String url = "http://www.alexa.cn/api/icp/info?token=" + icpToken + "&url=" + domain + "&host=&vcode=";
        HttpGet httpGet = new HttpGet(url);
        httpGetToInsertHeader(httpGet, host);
        httpGet.addHeader("Referer", "http://" + host + "/" + domain);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        JSONObject parse = (JSONObject) JSON.parse(EntityUtils.toString(entity));
        System.out.println(parse);
    }

}