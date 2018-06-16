package com.nsfy.util;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.sf.json.JSONObject;

public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	public static final String url = "http://146.12.2.3/stxxservice/stxxservice";
	public static final String ysurl = "http://146.12.2.3/stxxservice/stxxserviceys";
	public static final String xlurl = "http://146.12.2.3/stxxservice/xlastxxservice?ajid=";
	public static final String zxurllist = "http://146.12.2.3/zxxt/zxxxGetCaseList.do";
	public static final String zxurlinfo = "http://146.12.2.3/zxxt/zxxxGetCaseInfo.do";
	public static final String synSingleUrl = "http://localhost:9180/archive/Sync/single";
	public static final String synXlaUrl = "http://localhost:9180/archive/Sync/xla";
//	public static final String url = "http://127.0.0.1:9180/Demo/one";
public static void main(String[] args) {
	new HttpClientUtil().Get(url);
}
//23929241207962669
	public String Get(String url){
		CloseableHttpClient httpClient = getHttpClient();
		JSONObject resultJson = new JSONObject();
		try{
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(get);
			System.out.println();
			try{
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity);
//					System.out.println(result);
//
//					resultJson = JSONObject.fromObject(result);
//					
//					System.out.println(resultJson);
				}
			}finally {
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				closeHttpClient(httpClient);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}
	private CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}
	public static JSONObject Post(String url, JSONObject jsonParam) {
		return Post(url, jsonParam, false);
	}
	private static JSONObject Post(String url, JSONObject jsonParam, boolean noNeedResponse) {
		// TODO Auto-generated method stub
		// Post请求返回数据

		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("utf-8");
				entity.setContentType("application/json");
				System.out.println(entity.toString());
				method.setEntity(entity);
				System.out.println(method.toString());
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/**
			 * 请求发送成功
			 * 并得到响应
			 */
			int statusCode = result.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			if (statusCode == 200) {
				String str = "";
				try{
					//读取服务器返回的json字符串
					str = EntityUtils.toString(result.getEntity());
					if (noNeedResponse) {
                        return null;
                    }
					//把json字符串转化成json对象
					jsonResult = JSONObject.fromObject(str);
				}catch (Exception e) {
					// TODO: handle exception
					logger.error("post请求提交失败:"+url,e);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("post请求提交失败:"+url,e);
		}
		return jsonResult;
	}

}
