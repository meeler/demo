package cn.meeler.test.controller.qcc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 *调用qcc接口查询的工具类
 * @author mozhe
 * 这个命名时企查查官网搜索命名
 */
public class QccUtils {

	private static String BaseURL = "http://api.qichacha.com/ECIV4/GetDetailsByName?";

	//获取用户的Apikey
	public static String KEY = "f7b0196e62034a6aabf81c29195222ee";
	
	//获取用户的私钥
	public static String SECCRET_KEY ="2E493D958AB554372D4034A6C5E97EBC";
	
	//获取时间戳
	public static String timespan = String.valueOf(System.currentTimeMillis()/1000);
	
	//拼接Token 
	public static String token = Md5Utils.encodeMd5(KEY+timespan+SECCRET_KEY);
	
	/**
	 * 
	 * @param CompanyInfo 可以传公司名Name、注册号No或社会统一信用代码CreditCode
	 * @return
	 */
	public static String SearchCompany(String CompanyInfo) {
		//获取企业的url
		String CompanyUrl = BaseURL +"key="+KEY+"&keyword="+CompanyInfo;
		return getConn(CompanyUrl);
	}
	
	/**
	 * 提交get请求
	 * @param url
	 */
	private static String getConn(String url) {
		//提交请求
		String result = get(url, token,timespan);
		return result;
	}

	/**
	 * 构造通用参数timestamp、sig和respDataType返回值类型
	 * @return
	 */
	public static String get(String url,String token,String timespan) {
		BufferedReader bufferedReader = null;
		String result = "";
		StringBuilder stringBuilder = null;
		try {
			URL BaseUrl = new URL(url);
			URLConnection conn = BaseUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			//在请求头中拼接token和TIMESPAN做身份的验证
			conn.setRequestProperty("Token", token);
			conn.setRequestProperty("Timespan", timespan);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			InputStream inputStream = conn.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			stringBuilder = new StringBuilder();
			String line ;
			while ((line=bufferedReader.readLine())!=null) {
				stringBuilder.append(line);
			}
			result = stringBuilder.toString();
		} catch (Exception e) {
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Test
	public void test(){
		String result = SearchCompany("北京京东叁佰陆拾度电子商务有限公司");
		System.out.println(result);
		JSONObject parse = (JSONObject) JSON.parse(result);
		JSONObject result1 =  parse.getJSONObject("Result");
		System.out.println(result1);
	}
}
