package com.nsfy.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nsfy.entity.Stxx;
import com.nsfy.entity.Stxx2;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	public JSONObject getFileJson(String id,String caseNum,String caseCase,String pla,String defen,String pageNum,String ajid,String stid,String bookNum,String allBooks,String spcx){
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("case_num", caseNum);
		json.put("case_case", caseCase);
		json.put("pla", pla);
		json.put("defen", defen);
		json.put("ajid", ajid);
		json.put("stid", stid);
		json.put("page_num", pageNum);
		json.put("book_num", bookNum);
		json.put("all_books", allBooks);
		json.put("spcx", "10");
		System.out.println(json.toString());
		return json;
	}
	public Stxx stxxpass(){
		Stxx stxx = new Stxx();
		Date date = new Date();
		stxx.setAddDate(date);
		stxx.setAllBooks("2");
		stxx.setBookNum("1");
		stxx.setCase_num("测试");
		stxx.setCaseCase("测试");
		List<String> defen = new ArrayList<>();
		defen.add("张三");
		stxx.setDefen(defen);
		stxx.setDefenRoleName("被告");
		stxx.setInDate(date);
		stxx.setPageNum("1");
		List<String> pla = new ArrayList<>();
		pla.add("李四");
		stxx.setPla(pla);
		stxx.setPlaRoleName("原告");
		stxx.setSfbq("是");
		stxx.setSjg("王五");
		stxx.setSjyp("88888888");
		stxx.setSlf("44444");
		stxx.setSsbd("4444");
		stxx.setSycx("简易程序");
		List<String> th = new ArrayList<>();
		th.add("测试");
		stxx.setThird(th);
		stxx.setThirdRoleName("第三人");
		stxx.setZsfg("赵六");
		return stxx;
	}
	public Stxx2 stxxpass2(){
		Stxx2 stxx = new Stxx2();
		Date date = new Date();
		stxx.setAjid("111111");
		stxx.setAddDate(date);
		stxx.setAllBooks("2");
		stxx.setBookNum("1");
		stxx.setCase_num("测试");
		stxx.setCaseCase("测试");
		List<String> defen = new ArrayList<>();
		defen.add("张三");
		stxx.setDefen(defen);
		stxx.setDefenRoleName("被告");
		stxx.setInDate(date);
		stxx.setPageNum("1");
		List<String> pla = new ArrayList<>();
		pla.add("李四");
		stxx.setPla(pla);
		stxx.setPlaRoleName("原告");
		stxx.setSfbq("是");
		stxx.setSjg("王五");
		stxx.setSjyp("88888888");
		stxx.setSlf("44444");
		stxx.setSsbd("4444");
		stxx.setSycx("简易程序");
		List<String> th = new ArrayList<>();
		th.add("测试");
		stxx.setThird(th);
		stxx.setThirdRoleName("第三人");
		stxx.setZsfg("赵六");
		return stxx;
	}
	public String xilie(){
		Stxx2 stxx = this.stxxpass2();
		
		List<Stxx2> sts = new ArrayList<>();
		sts.add(stxx);
		sts.add(stxx);
		sts.add(stxx);
		sts.add(stxx);
		JSONArray list = JSONArray.fromObject(sts);
		JSONObject json = new JSONObject();
		json.put("reqstate", 1);
		json.put("cases", list);
		return json.toString();
	}
	
}
