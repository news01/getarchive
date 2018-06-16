package com.nsfy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsfy.dao.SysAjStDao;
import com.nsfy.entity.Stxx;
import com.nsfy.entity.Stxx3;
import com.nsfy.entity.SysAjSt;
import com.nsfy.entity.Zxxxl;
import com.nsfy.service.StxxService;
import com.nsfy.service.SysAjStService;
import com.nsfy.util.HttpClientUtil;
import com.nsfy.util.JSONUtil;
import com.nsfy.util.StringUtil;
import com.nsfy.util.TimeUtil;
import com.nsfy.util.ZxingHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ZxArchive {
	Logger logger = Logger.getLogger(ZxArchive.class);

	/***
	 * 初次进入执行系统，默认为今天的案件
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "zxxx2")
	public String zxx2(){
		return "../zxxx2";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getcasenum",produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String getCaseNum(
			HttpSession session,@RequestParam(value="year",required = true) String year,
			@RequestParam(value="lb",required = true) String lb,@RequestParam(value="num",required = true) String num
			){
		String caseNum = "("+year+")粤0305"+lb +num+"号";
		String caseNum2 = "（"+year+"）粤0305"+lb +num+"号";
		List<Zxxxl> zxxxl = (List<Zxxxl>) session.getAttribute("list");
		List<Zxxxl> list = new ArrayList<>();
		System.out.println(zxxxl.size());
		System.out.println(caseNum);
		for (int i = 0; i < zxxxl.size(); i++) {
			if(zxxxl.get(i).getAh() == caseNum||zxxxl.get(i).getAh().equals(caseNum)||zxxxl.get(i).getAh() == caseNum2||zxxxl.get(i).getAh().equals(caseNum2)){
				list.add(zxxxl.get(i));
			}
			System.out.println(zxxxl.get(i).getAh());
		}
		JSONObject json =new JSONObject();
		json.put("list", JSONArray.fromObject(list));
		logger.info(json.toString());
		return json.toString();
	}
	@RequestMapping(value = "zxxx2",produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String Zxxx(HttpSession session,Model model,@RequestParam(value="year",required = true) String year
			,@RequestParam(value="month",required = true) String month,@RequestParam(value="date",required = true) String date
			) throws JsonParseException, JsonMappingException, IOException {
		if (month.length() == 1) {
			month = "0"+month;
		}
		if (date.length() == 1) {
			date = "0"+date;
		}
		JSONObject json2 = new JSONObject();
		String realDate = year+month+date;
		/***
		 * 获得当天时间：20170831-20170831
		 */
		String rq = realDate+"-"+realDate;
		/***
		 * 组合成URL
		 */
		String url = HttpClientUtil.zxurllist+"?jbfy=2580&lx=SA&rq="+rq;
		/***
		 * 获取接口的数据并转化为JSONObject
		 */
		JSONObject json = JSONObject.fromObject(new HttpClientUtil().Get(url));
		logger.info(json.toString());
		ObjectMapper objMapper = new ObjectMapper();
		/***
		 * 如果返回的code是0的话，说明获取成功，否则获取失败
		 */
		if (Integer.parseInt((String) json.get("code"))==0) {
			Zxxxl zxxx = null;
			List<Zxxxl> zxlist = new ArrayList<>();
			JSONArray data = JSONArray.fromObject(json.get("data"));
			/***
			 * 循环获取data中的内容
			 */
			for (int i = 0; i < data.size(); i++) {
				zxxx = new Zxxxl();
				JSONObject j = JSONObject.fromObject(data.get(i));
				/***
				 * 将data中的内容反序列化
				 */
				zxxx = objMapper.readValue(j.toString(), Zxxxl.class);
				zxlist.add(zxxx);
			}
			for (int i = 0; i < zxlist.size(); i++) {
				JSONObject j = JSONObject.fromObject(zxlist.get(i));
				logger.info(j.toString());
			}
			/***
			 * 如果成功，code为1，返回到前台
			 */
			session.setAttribute("list", zxlist);
			model.addAttribute("list", zxlist);
			model.addAttribute("message", json.get("message"));
			model.addAttribute("year", year);
			model.addAttribute("month", month);
			model.addAttribute("date", date);
			JSONArray ja = JSONArray.fromObject(zxlist);
			json2.put("list", ja);
			json2.put("status", "0");
			json2.put("message", json.get("message"));
			
		}else{
			model.addAttribute("code", "2");
			model.addAttribute("message", json.get("message"));
			json2.put("status", "1");
			json2.put("message", json.get("message"));
		}
		System.out.println(json2.toString());
		
		return json2.toString();
	}
	@RequestMapping(value="getzxxxinfo")
	public String getZxxxInfo(
			@RequestParam(value="ajbs",required = true) String ajbs,Model model,HttpSession session
			) throws JsonParseException, JsonMappingException, IOException{
		String url = HttpClientUtil.zxurlinfo+"?ajbs="+ajbs+"&jbfy="+StringUtil.jbfy;
		JSONObject json = JSONObject.fromObject(new HttpClientUtil().Get(url));
		System.out.println(json.toString());
		ObjectMapper objMapper = new ObjectMapper();
		if (Integer.parseInt((String)json.get("code"))==0) {
			com.nsfy.entity.Zxxx zxxx = new com.nsfy.entity.Zxxx();
			String id = "";
			SysAjStService service = new SysAjStService();
			List<SysAjSt> list = service.findList2(ajbs);
			if (list.size() == 0) {
				id = System.currentTimeMillis() + "";
				SysAjSt ajst = new SysAjSt();
				ajst.setAjid(ajbs);
				ajst.setId(id);
				ajst.setStid("1");
				service.insert(ajst);
			}else{
				id = list.get(0).getId();
			}
			
			String pla = "";
			String defen = "";
			String defen1 = "";
			String defen2 = "";
			
			System.out.println(json.get("data").toString());
			zxxx = objMapper.readValue(json.get("data").toString(), com.nsfy.entity.Zxxx.class);
			if (zxxx.getPla().size()!=0) {
				pla = zxxx.getPla().get(0);
			}
			if (zxxx.getDefen().size()!=0) {
				defen1 = zxxx.getDefen().get(0);
			}
			if (zxxx.getBzxr().size()!=0) {
				defen = zxxx.getBzxr().get(0);
			}
			if (defen != null||!defen.equals("")) {
				defen2 = defen;
			}else if(defen1 != null||!defen1.equals("")){
				defen2 = defen1;
			}
			
			JSONObject QRContent = new JSONUtil().getFileJson(id, zxxx.getCase_num(), zxxx.getCaseCase(), pla, defen2,
					"0", zxxx.getId(), "1", "1", "1","10");
			String imgPath = StringUtil.barpath + QRContent.getString("id") + "bar.png";
			String imgPath2 = StringUtil.qrpath + QRContent.getString("id") + "qr.png";
			
			ZxingHandler.encode(id + "", StringUtil.barwith, StringUtil.barhiegth, imgPath);
			ZxingHandler.encode2(QRContent.toString(), StringUtil.qrwith, StringUtil.qrhiegth, imgPath2);
			String pla2 = new StringUtil().tostr(zxxx.getPla(), 1, zxxx.getPlaRoleName());
			String defen3 = new StringUtil().tostr(zxxx.getBzxr(), 2, zxxx.getBzxrRoleName());
			String defen4 = new StringUtil().tostr(zxxx.getDefen(), 2, zxxx.getDefenRoleName());
			String third = new StringUtil().tostr(zxxx.getThird(), 3, zxxx.getThirdRoleName());
			session.setAttribute("content", QRContent.toString());
			model.addAttribute("content", QRContent.toString());
			model.addAttribute("code", "1");
			model.addAttribute("message", json.get("message"));
			model.addAttribute("zxxx", zxxx);
			model.addAttribute("id", id);
			model.addAttribute("pla", pla2);
			model.addAttribute("defen", defen3);
			model.addAttribute("defen1", defen4);
			model.addAttribute("third", third);
		}
		else{
			model.addAttribute("code", "0");
			model.addAttribute("message", json.get("message"));
		}
		return "../zxxxface";
		
	}
	
	
	
}
