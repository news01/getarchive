package com.nsfy.util;

import net.sf.json.JSONObject;

public class TestMain {
	public static void main(String[] args) {
		String ajbs = "258020171003001469";
		String url = HttpClientUtil.zxurlinfo+"?ajbs="+ajbs+"&jbfy="+StringUtil.jbfy;
		JSONObject json = JSONObject.fromObject(new HttpClientUtil().Get(url));
		System.out.println(json.toString());
	}
}
