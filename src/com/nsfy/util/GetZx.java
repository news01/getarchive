package com.nsfy.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsfy.entity.Stxx3;
import com.nsfy.entity.Zxxx;

import net.sf.json.JSONObject;

public class GetZx {
	public Zxxx getZxxx(String ajbs){
		String url = HttpClientUtil.zxurlinfo+"?ajbs="+ajbs+"&jbfy="+StringUtil.jbfy;
		JSONObject json = JSONObject.fromObject(new HttpClientUtil().Get(url));
		System.out.println(json.toString());
		ObjectMapper objMapper = new ObjectMapper();
		if (Integer.parseInt((String)json.get("code"))==0) {
			Zxxx zxxx = new Zxxx();
//			String id = System.currentTimeMillis() + "";
			try {
				zxxx = objMapper.readValue(json.get("data").toString(), Zxxx.class);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return zxxx;
		}else{
			return null;
		}
	}
	
	public Stxx3 zx2st(Zxxx zxxx){
		Stxx3 stxx = new Stxx3();
		
		return stxx;
		
	}
}
