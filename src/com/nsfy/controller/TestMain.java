package com.nsfy.controller;

import net.sf.json.JSONObject;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject json1 = new JSONObject();
		json1.put("errCode", "0");
		json1.put("errMsg", "用户未关注微信号");
		JSONObject json2 = new JSONObject();
		json2.put("unionId", "1");
		json2.put("openId", "2");
//		json1.put("data", json2.toString());
		System.out.println(json1.toString());
	}

}
